import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    Trie test;

    @BeforeEach
    void setup() {
        test = new Trie();
        test.insert("a");
        test.insert("aaaaaa");
        test.insert("bbbbbb");
        test.insert("A");
    }

    @Test
    void insert() {
        assertTrue(test.insert("a"));
        assertTrue(test.insert("aa"));
        assertTrue(test.insert("aa"));
        assertFalse(test.insert(""));
        assertFalse(test.insert(" "));
        assertFalse(test.insert(null));
    }

    @Test
    void remove() {
        assertTrue(test.remove("a"));
        assertTrue(test.remove("aaaaaa"));
        assertTrue(test.remove("bbbbbb"));
        assertTrue(test.remove("A"));
        assertFalse(test.remove("A"));
        assertFalse(test.remove("NQN RQ"));
        assertFalse(test.remove(" "));
        assertFalse(test.remove(null));
        assertEquals(new Trie(), test); //Удаление всех слов из дерева
    }

    @Test
    void search() {
        assertTrue(test.search("a"));
        assertTrue(test.search("aaaaaa"));
        assertTrue(test.search("A"));
        assertFalse(test.search("AA"));
        assertFalse(test.search("A A"));
        assertFalse(test.search(" "));
        assertFalse(test.search(null));
    }

    @Test
    void startsWithPrefix() {
        ArrayList<String> expected= new ArrayList<String>();

        assertEquals(expected, test.startsWithPrefix(""));

        expected.add("a");
        expected.add("aaaaaa");

        assertEquals(expected, test.startsWithPrefix("a"));

        expected.clear();
        expected.add("A");

        assertEquals(expected, test.startsWithPrefix("A"));

        assertNotEquals(expected, test.startsWithPrefix("b"));

        expected.clear();
        expected.add("bbbbbb");

        assertEquals(expected, test.startsWithPrefix("b"));
    }
}