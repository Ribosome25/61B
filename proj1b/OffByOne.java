public class OffByOne extends Palindrome implements CharacterComparator  {

    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return diff == 1;
    }

    @Override
    public boolean isSameChar (char a, char b){
        return equalChars(a, b);
    }

}
