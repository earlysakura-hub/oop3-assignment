package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Sorts {@link Product} arrays into descending order using insertion sort.
 * <p>
 * Each element is taken in turn and inserted into its correct position within
 * the already sorted prefix of the array. Time complexity is O(n&sup2;) in the
 * worst and average cases and O(n) for already sorted input; space complexity
 * is O(1). The sort is stable.
 */
public class InsertionSort {

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
        for (int i = 1; i < array.length; i++) {
            Product key = array[i];
            int j = i - 1;

            while (j >= 0 && comparator.compare(array[j], key) < 0) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }
}
