//first define a TrieNode class where each node holds an array of 26 children (for 'a' to 'z') and a boolean isEnd to mark the end of a word. 
//When we insert a word, we start from the root and move through each character—if a node doesn’t exist, we create it; finally, we mark the last node as isEnd = true. 
//For searching or prefix checking, we follow the same traversal logic through the character array; for search, we ensure the last node has isEnd = true, and for startsWith, we simply return true if the prefix path exists.
//Time complexity: O(n)
//Space complexity: O(1)

class Trie {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
    private TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
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
    
    public boolean search(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }
        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if(curr.children[ch - 'a'] == null) return false;
            curr = curr.children[ch - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */