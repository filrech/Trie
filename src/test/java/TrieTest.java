import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test
    public void trieTest() {
        Trie test = new Trie();
        test.trieInsert("a");
        test.trieInsert("aaaaaaaa");
        test.trieInsert("ab");
        test.trieInsert("abc");
        test.trieInsert("ad");
        test.trieInsert("bbbbb");
        test.trieInsert("bbbbbba");
        test.trieInsert("ccccc");
        test.trieInsert("A");
        test.trieInsert("Add");
        test.trieInsert("Addition");
        test.trieInsert("Hello");

        test.trieStartsWithPrefix("a");
        System.out.println("==========");
        test.trieStartsWithPrefix("Ad");

        assertTrue(test.trieSearch("Addition"));
        assertTrue(test.trieSearch("Add"));
        assertTrue(test.trieSearch("Hello"));
        assertFalse(test.trieSearch("Additionn"));
        assertFalse(test.trieSearch("additionn"));
        assertFalse(test.trieSearch("Additio"));

        test.trieRemove("Addition");

        assertFalse(test.trieSearch("Addition"));
        assertFalse(test.trieSearch("addition"));
        assertFalse(test.trieSearch("Additio"));

        assertFalse(test.startsWithPrefix("Addition"));
        assertFalse(test.startsWithPrefix("Addi"));

        assertTrue(test.trieSearch("Add"));
        assertTrue(test.trieSearch("A"));

        test.trieRemove("Hello");

        assertFalse(test.trieSearch("Hello"));
        assertFalse(test.startsWithPrefix("Hello"));
        assertFalse(test.startsWithPrefix("Hell"));
        assertFalse(test.startsWithPrefix("Hel"));
        assertFalse(test.startsWithPrefix("He"));
        assertFalse(test.startsWithPrefix("H"));
    }
}