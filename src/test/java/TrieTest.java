import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    Trie test;
    ArrayList<String> expectedAPrefix = new ArrayList<String>();
    ArrayList<String> expectedBPrefix = new ArrayList<String>();
    ArrayList<String> expectedCPrifix = new ArrayList<String>();

    @BeforeEach
    void setup() {
        test = new Trie();
        test.insert("a");
        test.insert("b");
        test.insert("bb");
        test.insert("bbb");
        test.insert("c");

        expectedAPrefix.add("a");
        expectedAPrefix.add("aa");
        expectedAPrefix.add("aaa");
        expectedAPrefix.add("aaaa");
        expectedAPrefix.add("aaaaa");
        expectedAPrefix.add("aaaaaa");

        expectedBPrefix.add("b");
        expectedBPrefix.add("bb");

        expectedCPrifix.add("c");
    }

    @Test
    void insert() {
        assertTrue(test.insert("a"));
        assertTrue(test.insert("aa"));
        assertTrue(test.insert("aaa"));
        assertTrue(test.insert("aaaa"));
        assertTrue(test.insert("aaaaa"));
        assertTrue(test.insert("aaaaaa"));
        assertFalse(test.insert(""));
        assertFalse(test.insert(" "));
        assertFalse(test.insert(null));

        assertEquals(expectedAPrefix, test.startsWithPrefix("a")); //были добавлены слова из expected списка
    }

    @Test
    void remove() {
        assertTrue(test.remove("a"));
        assertTrue(test.remove("bbb"));
        assertFalse(test.remove("bbb"));
        assertFalse(test.remove("NQN RQ"));
        assertFalse(test.remove(" "));
        assertFalse(test.remove(null));

        assertEquals(expectedBPrefix, test.startsWithPrefix("b")); //Удаление одного слова из 3х

        assertTrue(test.remove("b"));
        assertTrue(test.remove("bb"));
        assertTrue(test.remove("c"));

        assertEquals(new Trie(), test); //Удаление всех слов из дерева
    }

    @Test
    void search() {
        assertTrue(test.search("a"));
        assertTrue(test.search("bbb"));
        assertFalse(test.search("AA"));
        assertFalse(test.search("A A"));
        assertFalse(test.search(" "));
        assertFalse(test.search(null));
    }

    @Test
    void startsWithPrefix() {
        assertEquals(expectedCPrifix, test.startsWithPrefix("c"));

        assertNotEquals(expectedBPrefix, test.startsWithPrefix("a"));
        test.remove("bbb");
        assertEquals(expectedBPrefix, test.startsWithPrefix("b"));

        assertNotEquals(expectedAPrefix, test.startsWithPrefix("a"));
        test.insert("aa");
        test.insert("aaa");
        test.insert("aaaa");
        assertNotEquals(expectedAPrefix, test.startsWithPrefix("a"));
        test.insert("aaaaa");
        test.insert("aaaaaa");
        assertEquals(expectedAPrefix, test.startsWithPrefix("a"));
    }

}