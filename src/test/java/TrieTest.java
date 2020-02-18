import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TrieTest {
    @Test
    public void trieTest() {
        Trie test = new Trie();
        test.trieInsert("I");
        test.trieInsert("Have");
        test.trieInsert("No");
        test.trieInsert("Idea");
        //how to test a trie
        test.trieInsert("WTF");
        test.trieInsert("Id");

        test.trieInsert("Remove");
        assertTrue(test.trieSearch("Remove"));
        test.trieRemove("Remove");
        assertFalse(test.trieSearch("Remove"));

        assertTrue(test.trieSearch("I"));
        assertTrue(test.trieSearch("Idea"));
        assertTrue(test.trieSearch("WTF"));
        assertTrue(test.trieSearch("No"));
        assertTrue(test.trieSearch("Id")); //isWord check
        assertFalse(test.trieSearch("jq"));
        assertFalse(test.trieSearch("i"));

        assertFalse(test.startsWithPrefix("Remov"));
        assertFalse(test.trieSearch("Remove"));
        assertFalse(test.trieSearch("R"));
        //assertFalse(test.startsWithPrefix("R"));

        test.trieRemove("Idea");
        assertFalse(test.trieSearch("Idea"));
        assertFalse(test.startsWithPrefix("Idea"));
        assertTrue(test.startsWithPrefix("Id"));
        assertTrue(test.trieSearch("Id"));
    }


}