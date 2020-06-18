public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();
        if (word.length() == 0) {
            return dq;
        } else {
            final Deque<Character> dq1 = dq;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                dq1.addLast(c);
            }
            return dq1;
        }
    }

    public boolean isSameChar(char a, char b) {
        return a == b;
    }

    public boolean isListPalindrome(Deque<Character> charlist) {
        if (charlist.size() == 0 || charlist.size() == 1) {
            return true;
        } else {
            char f = charlist.removeFirst();
            char l = charlist.removeLast();
            if (isSameChar(f, l)) {
                return isListPalindrome(charlist);
            } else {
                return false;
            }
        }
    }
    public boolean isPalindrome(String word) {
        Deque<Character> charList = wordToDeque(word.toLowerCase());
        return isListPalindrome(charList);
    }

}
