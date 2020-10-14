package lab9tester;
import lab9.BSTMap;

public class visualizeBSTMap {
    public static void main(String array[]) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
    }
}
