package sort;

class ParallelMergeSort {

    public void mergeSort(int[] array, int ThreadSize) {
        int low = 0;
        int high = array.length-1;
        parallelMergeSort(array, low, high, ThreadSize);
    }

    private void parallelMergeSort(int[] array, int low, int high, int threadsNum){
        if (threadsNum < 2) {
            MergeSort.mergesort(array,low,high);
            return;
        }
        int mid = (low + high) / 2;
        Thread thread1 = new Thread(() -> parallelMergeSort(array, low, mid, threadsNum /2));
        Thread thread2 = new Thread(() -> parallelMergeSort(array,mid + 1, high, threadsNum /2));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MergeSort.merge(array, low, mid, high);
    }
}
