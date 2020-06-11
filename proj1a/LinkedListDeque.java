public class LinkedListDeque<T> {

    private class TNode {
        private TNode prev;
        private T item;
        private TNode next;

        public TNode(TNode prev, T item, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private TNode sentinel;

    /* create an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    public void addFirst(T item) {
        this.sentinel.next = new TNode(this.sentinel, item, this.sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        this.size += 1;
    }

    public void addLast(T item) {
        this.sentinel.prev = new TNode(this.sentinel.prev, item, this.sentinel);
        this.sentinel.prev.prev.next = this.sentinel.prev;
        this.size += 1;
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return  null;
        }
        T rtItem = this.sentinel.next.item;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        this.size -= 1;
        return rtItem;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return  null;
        }
        T rtItem = this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size -= 1;
        return rtItem;
    }

    public T get(int i) {
        TNode pt = this.sentinel.next;
        while (i > 0) {
            i--;
            pt = pt.next;
        }
        return pt.item;
    }

    private T getRecursiveNode(int i, TNode n) {
        if (i == 0) {
            return n.item;
        } else {
            return getRecursiveNode(i - 1, n.next);
        }
    }

    public T getRecursive(int i) {
        return getRecursiveNode(i, this.sentinel.next);
    }

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

    public void printDeque() {
        TNode head = this.sentinel.next;
        for (int i = 0; i < this.size; i++) {
            System.out.println(head.item);
            head = head.next;
        }
    }
}
