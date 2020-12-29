package sort;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import static sort.MStest.randomArray;

@Fork(value = 1)
@Warmup(iterations = 20)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class BMtest {

    private static final int SIZE = 500_000;

    public static void main(final String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void benchSequential() {
        final int[] array = randomArray(SIZE);
        MergeSort.mergesort(array,0,array.length-1);
    }

    @Benchmark
    public void benchParallel() {
        final int[] array = randomArray(SIZE);
        ParallelMergeSort ms = new ParallelMergeSort();
        ms.mergeSort(array, Runtime.getRuntime().availableProcessors());
    }

}
