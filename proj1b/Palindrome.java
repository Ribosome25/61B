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

    private boolean isSameChar(char a, char b) {
        return a == b;
    }

    private boolean isListPalindrome(Deque<Character> charlist) {
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

    private boolean isListPalindrome(Deque<Character> charlist, CharacterComparator cc) {
        if (charlist.size() == 0 || charlist.size() == 1) {
            return true;
        } else {
            char f = charlist.removeFirst();
            char l = charlist.removeLast();
            if (cc.equalChars(f, l)) {
                return isListPalindrome(charlist, cc);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> charList = wordToDeque(word);
        return isListPalindrome(charList);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> charList = wordToDeque(word);
        return isListPalindrome(charList, cc);
    }

}
