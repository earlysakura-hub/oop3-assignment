package utilities;

import java.util.Comparator;

public class HeapSort {

    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparator);
        }

        for (int i = n - 1; i > 0; i--) {
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0, comparator);
        }
    }

    private static <T> void heapify(
            T[] array,
            int size,
            int root,
            Comparator<? super T> comparator) {

        int smallest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < size
                && comparator.compare(array[left], array[smallest]) < 0) {
            smallest = left;
        }

        if (right < size
                && comparator.compare(array[right], array[smallest]) < 0) {
            smallest = right;
        }

        if (smallest != root) {
            T temp = array[root];
            array[root] = array[smallest];
            array[smallest] = temp;

            heapify(array, size, smallest, comparator);
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sort(array, Comparator.naturalOrder());
    }
}