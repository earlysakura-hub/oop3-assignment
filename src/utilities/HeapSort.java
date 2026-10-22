package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Sorts {@link Product} arrays into descending order using heap sort.
 * <p>
 * The array is first rearranged into a min-heap (the smallest element, per the
 * comparator, at the root). The root is then repeatedly swapped to the end of
 * the unsorted region, which leaves the array ordered largest to smallest.
 * Time complexity is O(n log n) in all cases; the sort is in place but not
 * stable.
 */
public class HeapSort {

	/**
     * Sorts the array in place from largest to smallest according to the
     * comparator.
     *
     * @param array      the array to sort; modified in place
     * @param comparator the comparison rule; elements that compare as larger
     *                   are placed earlier in the array
     * @throws NullPointerException if {@code array} or {@code comparator} is
     *         {@code null}, or if the array contains {@code null} elements
     */
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

    /**
     * Restores the min-heap property for the subtree rooted at {@code root},
     * assuming both child subtrees are already valid heaps. The element at
     * {@code root} is sifted down recursively until the property holds.
     *
     * @param array      the array representing the heap
     * @param size       the number of elements of the array that form the heap
     * @param root       the index of the subtree root to fix
     * @param comparator the comparison rule; the element comparing smallest
     *                   is kept closest to the root
     */
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
