package sort;

public class MergeSort {

    protected static void mergesort(int[] array, int low, int high) {
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

