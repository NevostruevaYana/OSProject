package sort;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import static sort.MStest.randomArray;

@Fork(value = 2)
@Warmup(iterations = 30)
@Measurement(iterations = 20)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class BMtest {

    private static final int SIZE = 900_000;
    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();

    public static void main(final String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void benchParallelx10() {
        testing(availableProcessors * 10);
    }

    @Benchmark
    public void benchParallel16() {
        testing(availableProcessors * 2);
    }

    @Benchmark
    public void benchParallel8() {
        testing(availableProcessors);
    }

    @Benchmark
    public void benchParallel4() {
        testing(availableProcessors / 2);
    }

    @Benchmark
    public void benchParallel2() {
        testing(availableProcessors / 4);
    }

    @Benchmark
    public void benchParallel0() {
        testing(0);
    }

    private void testing (int threadsNum) {
        final int[] array = randomArray(SIZE);
        ParallelMergeSort ms = new ParallelMergeSort();
        ms.mergeSort(array, threadsNum);
    }

}
