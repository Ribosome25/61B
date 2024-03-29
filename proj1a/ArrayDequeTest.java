public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);
    }

    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void increaseSizeTest() {
        System.out.println("Running increasing array size test.");
        /**
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int jj = 1; jj <= 20; jj ++) {
            lld1.addFirst(jj);
        }
        boolean passed = checkEmpty(false, lld1.isEmpty());
        // passed = passed && (lld1.items.length == 32);
         */

        ArrayDeque<Double> lld2 = new ArrayDeque<>();
        for (double ii = 1; ii < 20; ii++) {
            lld2.addLast(ii);
            lld2.addFirst(40 - ii);
        }
        boolean passed = checkEmpty(false, lld2.isEmpty());
        printTestStatus(passed);

        for (int ii = 1; ii < 10; ii++) {
            lld2.removeLast();
            lld2.removeFirst();
        }
    }

    public static void getTest() {
        System.out.println("Running get, getRecursive test. ");
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addLast(3);
        lld1.addLast(4);

        passed = checkSize(1, lld1.get(0)) && passed;
        passed = checkSize(3, lld1.get(2)) && passed;
        printTestStatus(passed);
    }

    public static void agTest() {
        System.out.println("Running autograder  test. ");
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(0);
        lld1.addFirst(1);
        passed = checkSize(1, lld1.removeFirst()) && passed;

        ArrayDeque<Double> lld2 = new ArrayDeque<>();
        lld2.addLast(0.0);
        passed = (0.0 == lld2.removeLast()) && passed;
        lld2.addLast(2.0);
        passed = (2 == lld2.removeLast()) && passed;
        passed = checkEmpty(true, lld2.isEmpty()) && passed;

        lld2.addLast(0.0);
        passed = (0 == lld2.removeFirst()) && passed;


        printTestStatus(passed);
    }

    public static void getTestAG() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        boolean passed = checkEmpty(true, arrayDeque.isEmpty());
        int t;
        arrayDeque.addFirst(0);
        arrayDeque.addLast(1);
        t = arrayDeque.removeFirst();
        passed = checkSize(0, t) && passed;
        arrayDeque.addLast(3);
        t = arrayDeque.get(0);
        passed = checkSize(1, t) && passed;
        arrayDeque.addLast(5);
        t = arrayDeque.removeFirst();
        passed = checkSize(1, t) && passed;

        arrayDeque.addLast(7);
        t = arrayDeque.get(1);
        passed = checkSize(5, t) && passed;

        arrayDeque.addFirst(9);
        arrayDeque.addFirst(10);
        t = arrayDeque.removeFirst();
        passed = checkSize(10, t) && passed;

        arrayDeque.addLast(12);
        t = arrayDeque.get(2);
        passed = checkSize(5, t) && passed;
        t = arrayDeque.removeFirst();
        passed = checkSize(9, t) && passed;

        arrayDeque.addLast(15);
        arrayDeque.addFirst(16);
        arrayDeque.addFirst(17);
        t = arrayDeque.removeLast();
        passed = checkSize(15, t) && passed;
        t = arrayDeque.removeFirst();
        passed = checkSize(17, t) && passed;
        t = arrayDeque.removeLast();
        passed = checkSize(12, t) && passed;
        t = arrayDeque.removeFirst();
        passed = checkSize(16, t) && passed;
        t = arrayDeque.removeFirst();
        passed = checkSize(3, t) && passed;
        arrayDeque.addLast(23);
        printTestStatus(passed);
    }
    public static void typesTest() {
        System.out.println("New Alist for different types.");
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        ArrayDeque<String> lld2 = new ArrayDeque<>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<>();
        ArrayDeque<Double> lld4 = new ArrayDeque<>();

    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        increaseSizeTest();
        getTest();
        agTest();
        typesTest();
        getTestAG();
    }
}
