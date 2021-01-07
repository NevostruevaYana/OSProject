package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

class ParallelMergeSort {

    private final ConcurrentHashMap<Long, Double> syncMap = new ConcurrentHashMap<>();

    public void mergeSort(int[] array, int ThreadSize) {
        int low = 0;
        int high = array.length - 1;
        parallelMergeSort(array, low, high, ThreadSize);

        try {
            //File file = new File("C://Users//nevos//Desktop//test.txt");
            File file = new File("test");
            PrintWriter pw = new PrintWriter(file);
            syncMap.forEach((k, v) -> pw.write("tid: " + k + "; average time: " + v + "\n"));
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parallelMergeSort(int[] array, int low, int high, int threadsNum){
        if (threadsNum < 2) {
            mergesort(array,low,high);
            return;
        }
        int mid = (low + high) / 2;

        Thread thread1 = new Thread(() -> parallelMergeSort(array, low, mid, threadsNum /2));
        thread1.start();
        long t1 = System.currentTimeMillis();
        long tid1 = thread1.getId();

        Thread thread2 = new Thread(() -> parallelMergeSort(array,mid + 1, high, threadsNum /2));
        thread2.start();
        long t2 = System.currentTimeMillis();
        long tid2 = thread2.getId();

        try {
            thread1.join();
            syncMap.put(tid1, (double) (System.currentTimeMillis() - t1) / (mid - low));
            thread2.join();
            syncMap.put(tid2, (double) (System.currentTimeMillis() - t2) / (high - mid - 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(array, low, mid, high);
    }

    private void mergesort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergesort(array, low, mid);
            mergesort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    protected static void merge(int[] array, int low, int mid, int high) {
        int[] left = new int[mid - low + 1];
        int[] right = new int[high - mid];
        for (int index = 0; index < mid - low + 1; index++) {
            left[index] = array[index + low];
        }
        for (int index = 0; index < high - mid; index++) {
            right[index] = array[index + mid + 1];
        }
        int a_low = low;
        int l_low = low;
        int r_low = mid + 1;
        while (l_low <= mid && r_low <= high) {
            if (left[l_low - low] <= right[r_low - mid -1]) {
                array[a_low] = left[l_low - low];
                l_low++;
            } else {
                array[a_low] = right[r_low - mid - 1];
                r_low++;
            }
            a_low++;
        }

        while (l_low <= mid) {
            array[a_low] =left[l_low - low];
            l_low++;
            a_low++;
        }
        while (r_low <= high) {
            array[a_low] = right[r_low - mid - 1];
            r_low++;
            a_low++;
        }
    }
}