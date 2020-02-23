import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test
    public void trieTest() {
        Trie test = new Trie();
        test.insert("a");
        test.insert("aaaaaaaa");
        test.insert("ab");
        test.insert("abc");
        test.insert("ad");
        test.insert("bbbbb");
        test.insert("bbbbbba");
        test.insert("ccccc");
        test.insert("A");
        test.insert("Add");
        test.insert("Addition");
        test.insert("Hello");

        test.startsWithPrefix("a");
        System.out.println("==========");
        test.startsWithPrefix("Ad");
        System.out.println("==========");
        test.startsWithPrefix("");

        assertTrue(test.search("Addition"));
        assertTrue(test.search("Add"));
        assertTrue(test.search("Hello"));
        assertFalse(test.search("Additionn"));
        assertFalse(test.search("additionn"));
        assertFalse(test.search("Additio"));

        test.remove("Addition");

        assertFalse(test.search("Addition"));
        assertFalse(test.search("addition"));
        assertFalse(test.search("Additio"));

        assertFalse(test.booleanStartsWithPrefix("Addition"));
        assertFalse(test.booleanStartsWithPrefix("Addi"));

        assertTrue(test.search("Add"));
        assertTrue(test.search("A"));

        test.remove("Hello");

        assertFalse(test.search("Hello"));
        assertFalse(test.booleanStartsWithPrefix("Hello"));
        assertFalse(test.booleanStartsWithPrefix("Hell"));
        assertFalse(test.booleanStartsWithPrefix("Hel"));
        assertFalse(test.booleanStartsWithPrefix("He"));
        assertFalse(test.booleanStartsWithPrefix("H"));
    }
}