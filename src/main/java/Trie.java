import java.util.*;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void trieInsert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode();
                children.put(c, t);
            }

            children = t.children;

            if (i == word.length() - 1) {
                t.isWord = true;
            }
        }
    }

    public void trieRemove(String word) {
        int size = word.length() - 1;
        for (int i = 0; i < size; i++) {
            if (searchSize(word) != word.length()) break;
            TrieNode t = searchNode(word.substring(0, word.length() - 1));
            t.children.remove(word.charAt(word.length() - 1));
            word = word.substring(0, word.length() - 1);
            if (t.isWord) break;
        }
        if (word.length() == 1) {
            root.children.remove(word.charAt(0));
        }
    }

    public boolean trieSearch(String word) {
        TrieNode t = searchNode(word);

        return t != null && t.isWord;
    }

    public void trieStartsWithPrefix(String word) {
        TrieNode t = searchNode(word);
        if (t.isWord) {
            System.out.println(word);
        }
        for (Character s: t.children.keySet()) {
            String wordNext = word + s.toString();
            trieStartsWithPrefix(wordNext);
        }
    }

    public TrieNode searchNode(String s) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (children.containsKey(ch)) {
                t = children.get(ch);
                children = t.children;
            } else {
                return null;
            }
        }
        return t;
    }

    public int searchSize(String s) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (children.containsKey(ch)) {
                t = children.get(ch);
                children = t.children;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public boolean startsWithPrefix(String prefix) {
        return searchNode(prefix) != null;
    }
}

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isWord;
}