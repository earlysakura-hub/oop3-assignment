package utilities;

import java.util.Comparator;
import java.util.Objects;

import products.Product;

/**
 * Sorts arrays directly into descending order using merge sort.
 * Uses Product arrays and a non-parameterized Comparator for this assignment.
 * Comparisons retain the normal Comparable and Comparator contracts.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class MergeSort {
    private MergeSort() {
    }

    /**
     * Sorts an array from largest to smallest using its natural ordering.
     *
     * @param array the array to sort in place; elements must be non-null
     * @throws NullPointerException if the array is null
     */
    public static void sort(Product[] array) {
        sort(array, new Comparator() {
            @Override
            public int compare(Object first, Object second) {
                return ((Product) first).compareTo((Product) second);
            }
        });
    }

    /**
     * Sorts an array from largest to smallest according to the comparator.
     *
     * @param array the array to sort in place
     * @param comparator the comparison rule; a positive result means larger
     * @throws NullPointerException if the array or comparator is null
     */
    public static void sort(Product[] array, Comparator comparator) {
        Objects.requireNonNull(array, "Array must not be null");
        Objects.requireNonNull(comparator, "Comparator must not be null");
        if (array.length < 2) {
            return;
        }
        Product[] buffer = array.clone();
        mergeSort(array, buffer, 0, array.length - 1, comparator);
    }

    /**
     * Recursively sorts both halves of an inclusive range, then merges them.
     *
     * @param array the working array
     * @param buffer reusable temporary storage
     * @param low first index of the range
     * @param high last index of the range
     * @param comparator the comparison rule
     */
    private static void mergeSort(Product[] array, Product[] buffer, int low, int high,
            Comparator comparator) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        mergeSort(array, buffer, low, middle, comparator);
        mergeSort(array, buffer, middle + 1, high, comparator);
        System.arraycopy(array, low, buffer, low, high - low + 1);

        int left = low;
        int right = middle + 1;
        for (int destination = low; destination <= high; destination++) {
            if (left > middle) {
                array[destination] = buffer[right++];
            } else if (right > high) {
                array[destination] = buffer[left++];
            } else if (comparator.compare(buffer[left], buffer[right]) >= 0) {
                // Taking the left item on a tie keeps merge sort stable.
                array[destination] = buffer[left++];
            } else {
                array[destination] = buffer[right++];
            }
        }
    }

}