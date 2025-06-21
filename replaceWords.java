//first insert all dictionary words into a Trie by creating child nodes for each character and marking word ends. Then, for each word in the sentence, traverse the Trie character by character from the root, building a replacement string until it hits a null or an isEnd flag, and append either the replacement or original word to the result.
//Time complexity: O(N + M × K)
//Space complexity: O(N + M × K)
class Solution {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    private TrieNode root;
    public void insert(String word) {
        TrieNode curr = root;  //this TrieNode is used for traversal
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEnd = true;
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        StringBuilder result = new StringBuilder();
        for(String word: dictionary) {
            insert(word);
        }
        String [] strArr = sentence.split(" ");
        for(String word: strArr) {
            //search for replacement
            StringBuilder replacement = new StringBuilder();
            TrieNode curr = root;
            for(int i = 0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if(curr.children[ch - 'a'] == null || curr.isEnd) {
                    break;
                }
                replacement.append(ch);
                curr = curr.children[ch - 'a'];
            }
            if(curr.isEnd) {
                result.append(replacement.toString());
            } else {
                result.append(word);
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}