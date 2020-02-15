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

        assertTrue(test.trieSearch("I"));
        assertTrue(test.trieSearch("Idea"));
        assertTrue(test.trieSearch("WTF"));
        assertTrue(test.trieSearch("No"));
        assertFalse(test.trieSearch("jq"));
        assertFalse(test.trieSearch("Id"));
        assertFalse(test.trieSearch("i"));
    }


}