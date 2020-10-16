package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        ArrayList<Integer> bucket = new ArrayList<>();
        for (int ii = 0; ii < M; ii++) {
            bucket.add(0);
        }
        for (int i = 0; i < N; i++) {
            Oomage o = oomages.get(i);
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            int g = bucket.get(bucketNum);
            bucket.set(bucketNum, g + 1);
        }
        for (int ii = 0; ii < M; ii++) {
            int t = bucket.get(ii);
            if (t < N/50 || t > N/2.5) {
                return false;
            }
        }
        return true;
    }
}
