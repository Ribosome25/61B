public class ArrayDeque<T> {


    private T[] items;
    private int size;
    private double loadFactor = 1;
    /* next index update:
        if increase, just (i+1)%len;
        if decrease, should consider
         the case that it went below 0.
        (i+len-1)%len should work.
     */
    private int nextFirst;
    private int nextLast;

    private int increaseFactor = 2;

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    private void increaseSize() {
        // Linear time: length multiply with a factor.
        int len1 = this.items.length;
        T[] expandItems = (T[]) new Object[len1 * increaseFactor];
        int len2 = expandItems.length;
        // nextLast is always on the left, nextFirst is always on the right.
        // Left side: from 0 to 0, length is the id of nextLast.
        // The id of next Last does not change.
        System.arraycopy(this.items, 0,
                expandItems, 0, this.nextLast);
        // right side: From First+1, (some times may from 0),
        // copy the rest. The id of next First change to len2-len1+F
        System.arraycopy(this.items,
                (this.nextFirst + 1) % this.items.length, expandItems,
                len2 - len1 + 1 + this.nextFirst,
                len1 -1 - this.nextFirst);
        this.nextFirst = len2 - len1 + this.nextFirst;
        this.items = expandItems;
    }

    private void decreaseSize() {
        int len1 = this.items.length;
        int len2 = len1 / increaseFactor;
        T[] shrinkedItems = (T[]) new Object[len2];
        // Array copy is a mess. Use pointer and Mod.
        int i = this.nextFirst;
        while (i != this.nextLast) {
            int j = (i + 1) % len2;
            i = (i + 1) % len1;
            shrinkedItems[j] = this.items[i];
        }
        this.nextFirst = (this.nextFirst) % len2;
        this.items = shrinkedItems;
    }

    private void checkArrayFull() {
        if (this.nextLast == this.nextFirst) {
            increaseSize();
        }
    }

    private void checkArrayEmpty() {
        loadFactor = (float) this.size / (float) this.items.length;
        if ((loadFactor) < 0.25) {
            decreaseSize();
        }
    }

    public void addFirst(T item) {
        this.items[this.nextFirst] = item;
        this.nextFirst = (this.nextFirst - 1 + items.length) % items.length;
        this.size += 1;
        checkArrayFull();
    }

    public void addLast(T item) {
        this.items[this.nextLast] = item;
        this.nextLast = (this.nextLast + 1) % items.length;
        this.size += 1;
        checkArrayFull();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        int pt = (this.nextFirst + 1) % items.length;
        this.nextFirst = pt;
        this.size -= 1;
        T rt = this.items[pt];
        checkArrayEmpty();
        return rt;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return  null;
        }
        int pt = (this.nextLast - 1 + items.length) % items.length;
        this.nextLast = pt;
        this.size -= 1;
        T rt = this.items[pt];
        checkArrayEmpty();
        return rt;
    }

    public T get(int i) {
        int pt = (this.nextFirst + i + 1) % this.items.length;
        return this.items[pt];
    }

    public ArrayDeque() {
        int startPosition = 3;
        this.items = (T[]) new Object[8];
        this.nextFirst = startPosition - 1;
        this.nextLast = startPosition;
        this.size = 0;
    }

    public void printDeque() {
        int pt = (this.nextFirst + 1) % items.length;
        while (pt != this.nextLast) {
            System.out.println(this.items[pt]);
            pt = (pt + 1) % this.items.length;
        }
        return;
    }

}
