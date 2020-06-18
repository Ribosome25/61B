import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalidrome() {
        System.out.println("Test palindrome 1.");
        String a = "";
        assertTrue(palindrome.isPalindrome(a));
        String b = "a";
        assertTrue(palindrome.isPalindrome(b));
        String c = "Aa";
        assertFalse(palindrome.isPalindrome(c));
        String d = "ba";
        assertFalse(palindrome.isPalindrome(d));
        String e = "Aea";
        assertFalse(palindrome.isPalindrome(e));
        String f = "abba";
        assertTrue(palindrome.isPalindrome(f));
    }

    @Test
    public void testOffByOne() {
        System.out.println("Test Off by One.");
        CharacterComparator ofo = new OffByOne();
        String a = "";
        assertTrue(palindrome.isPalindrome(a, ofo));
        String b = "a";
        assertTrue(palindrome.isPalindrome(b, ofo));
        String c = "Aa";
        assertFalse(palindrome.isPalindrome(c, ofo));
        String d = "Ba";
        assertFalse(palindrome.isPalindrome(d, ofo));
        String e = "AeB";
        assertTrue(palindrome.isPalindrome(e, ofo));
        String f = "BbaA";
        assertTrue(palindrome.isPalindrome(f, ofo));
        String g = "GffgF";
        assertTrue(palindrome.isPalindrome(g, ofo));
        String h = "DqwvrC";
        assertTrue(palindrome.isPalindrome(h, ofo));
        String i = "Dqwvrc";
        assertFalse(palindrome.isPalindrome(i, ofo));
    }
}
