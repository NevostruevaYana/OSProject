package sort;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.jupiter.api.Assertions.fail;

public class MStest{

    @Test
    public void testParallel() {
        final int[] array = randomArray(2000);
        ParallelMergeSort pm = new ParallelMergeSort();
        pm.mergeSort(array,4);
        sortingCheck(array);
    }

    private static void sortingCheck(final int[] array) {
        int last = MIN_VALUE;
        for (int j : array) {
            if (j < last)
                fail();
            last = j;
        }
    }

    static int[] randomArray(final int n) {
        final int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Random().nextInt(n);
        }
        return a;
    }
}

