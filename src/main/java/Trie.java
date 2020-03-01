import java.util.*;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     *  Если word соответствует формату возвращает true
     */
    public boolean insert(String word) {
        Map<Character, TrieNode> children = root.children;
        if (word == null || word.length() == 0 || word.contains(" ")) {
            return false;
        }
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
        return true;
    }

    /**
     *  Если в структуре нет заданого слова, то возвращается false
     *  В случае если длина word равна 1, проверяется root
     *  Если слово не является концом ветки, то слово не удаляется (меняется значение isWord)
     */
    public boolean remove(String word) {
        TrieNode t = searchNode(word);
        if (t == null) {
            return false;
        }
        if (word.length() == 1) {
            if (booleanEndOfWord(word)) {
                root.children.remove(word.charAt(0));
            }
            if (root.children.keySet().isEmpty()) {
                root = new TrieNode();
                return true;
            }
            t.isWord = false;
            return true;
        }
        if (!booleanEndOfWord(word)) {
            t.isWord = false;
            return true;
        }
        String previousWord = word.substring(0, word.length() - 1);
        t = searchNode(previousWord);
        assert t != null;
        t.children.remove(word.charAt(word.length() - 1));
        if (!t.isWord) {
            remove(previousWord);
        }
        return true;
    }

    /**
     *  Возвращается true в случае, если было изначально введено в структуру (isWord == true)
     *  Проверка на isWord нужна чтобы сохранять консистенцию
     *  Пример без проверки:
     *  trie.insert("Idea")
     *  trie.search("Id") // true
     *  trie.startsWithPrefix("I") // [Idea]
     *  trie.remove("Idea")
     *  trie.search("Id") // false
     *  Идея в том чтобы обращаться только к словам которые были введены
     */
    public boolean search(String word) {
        TrieNode t = searchNode(word);
        return t != null && t.isWord;
    }

    /**
     *  Проходится по структуре и выдает лист со всеми словами у которых isWord = true
     */
    public List<String> startsWithPrefix(String word) {
        List<String> wordList = new ArrayList<String>();
        return getStrings(word, wordList);
    }

    private void startsWithPrefix(String word, List<String> wordList) {
        getStrings(word, wordList);
    }

    private List<String> getStrings(String word, List<String> wordList) {
        if (word == null || word.length() == 0) {
            return wordList;
        }
        TrieNode t = searchNode(word);
        if (t == null) {
            return wordList;
        }
        if (t.isWord) {
            wordList.add(word);
        }
        for (Character s: t.children.keySet()) {
            String wordNext = word + s.toString();
            startsWithPrefix(wordNext, wordList);
        }
        return wordList;
    }

    /**
     *  Нужен для получения всех слов в структуре
     *  Используется в equals() и hashCode()
     */
    private List<String> getAllWords() {
        List<String> allWordList = new ArrayList<String>();
        for (Character c: root.children.keySet()) {
            String word = c.toString();
            List<String> wordList = startsWithPrefix(word);
            allWordList.addAll(wordList);
        }
        return allWordList;
    }

    private TrieNode searchNode(String s) {
        if (s == null) {
            return null;
        }
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

    /**
     *   Используется в методе remove()
     *   Выдает true в случае если у ноды нет потомков
     */
    private boolean booleanEndOfWord(String s) {
        TrieNode t = searchNode(s);
        return t.children.isEmpty();
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isWord;
    }

    /**
     *  Деревья равны, если равны списки их слов
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } 
        if (!(obj instanceof Trie)) {
            return false;
        }
        return (this.getAllWords().equals(((Trie) obj).getAllWords()));
    }

    @Override
    public int hashCode() {
        return this.getAllWords().hashCode();
    }
}