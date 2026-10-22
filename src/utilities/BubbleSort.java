package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Sorts {@link Product} arrays into descending order using bubble sort.
 * <p>
 * Adjacent elements are repeatedly compared and swapped so that the largest
 * remaining element "bubbles" to the front of the unsorted region. The sort
 * stops early if a full pass makes no swaps. Time complexity is O(n&sup2;) in
 * the worst and average cases and O(n) for already sorted input; space
 * complexity is O(1). The sort is stable.
 */
public class BubbleSort {

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
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) < 0) {
                    Product temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
