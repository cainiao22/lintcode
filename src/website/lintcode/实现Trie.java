package website.lintcode;

/**
 * 
 * @author yanpf
 * @date 2017年9月28日 下午6:27:59
 * @description 实现一个 Trie，包含 insert, search, 和 startsWith 这三个方法
 * 				你可以假设所有的输入都是小写字母a-z
 * @example
 *
 * @Solution
 */
public class 实现Trie {
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("lintcode");
		System.out.println(trie.search("code")); // return false
		System.out.println(trie.startsWith("lint")); // return true
		System.out.println(trie.startsWith("linterror")); // return false
		trie.insert("linterror");
		System.out.println(trie.search("lintcode")); // return true
		System.out.println(trie.startsWith("linterror")); // return true
	}

}

class TrieNode {
    // Initialize your data structure here.
	public TrieNode[] children;
	public char val;
	public boolean hasWord = false;
    public TrieNode() {
        children = new TrieNode[26];
    }
    
    public TrieNode(char val) {
    	this();
    	this.val = val;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        // do intialization if necessary
    	root = new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        // write your code here
    	TrieNode current = root;
    	for(int i=0; i<word.length(); i++) {
    		int index = word.charAt(i) - 'a';
    		if(current.children[index] == null) {
    			current.children[index] = new TrieNode(word.charAt(i));
    		}
    		current = current.children[index];
    	}
    	//不是所有的单词都在叶子节点上面
		current.hasWord = true;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        // write your code here
    	TrieNode current = root;
    	for(int i=0; i<word.length(); i++) {
    		int index = word.charAt(i) - 'a';
    		if(current.children[index] == null) {
    			return false;
    		}
    		current = current.children[index];
    	}
    	return current.hasWord == true;
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // write your code here
    	TrieNode current = root;
    	for(int i=0; i<prefix.length(); i++) {
    		int index = prefix.charAt(i) - 'a';
    		if(current.children[index] == null) {
    			return false;
    		}
    		current = current.children[index];
    	}
    	return true;
    }
}


/**
 *  九章算法
 **/

class TrieNode2 {
    private TrieNode2[] children;
    public boolean hasWord;
    
    // Initialize your data structure here.
    public TrieNode2() {
        children = new TrieNode2[26];
        hasWord = false;
    }
    
    public void insert(String word, int index) {
        if (index == word.length()) {
            this.hasWord = true;
            return;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null) {
            children[pos] = new TrieNode2();
        }
        children[pos].insert(word, index + 1);
    }
    
    public TrieNode2 find(String word, int index) {
        if (index == word.length()) {
            return this;
        }
        
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null) {
            return null;
        }
        return children[pos].find(word, index + 1);
    }
}

class Solution {
    private TrieNode2 root;

    public Solution() {
        root = new TrieNode2();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        root.insert(word, 0);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode2 node = root.find(word, 0);
        return (node != null && node.hasWord);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode2 node = root.find(prefix, 0);
        return node != null;
    }
}

//todo hashMap实现方式 见九章算法

