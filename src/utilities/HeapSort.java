package utilities;

import java.util.Comparator;
import products.Product;

public class HeapSort {

    public static void sort(Product[] array, Comparator<Product> comparator) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparator);
        }

        for (int i = n - 1; i > 0; i--) {
            Product temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0, comparator);
        }
    }

    private static void heapify(
            Product[] array,
            int size,
            int root,
            Comparator<Product> comparator) {

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
            Product temp = array[root];
            array[root] = array[smallest];
            array[smallest] = temp;

            heapify(array, size, smallest, comparator);
        }
    }
}
