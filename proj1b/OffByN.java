public class OffByN implements CharacterComparator {

    private int offsite;

    public OffByN(int x) {
        offsite = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return diff == offsite;
    }

}
