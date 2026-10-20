package utilities;

import java.util.Comparator;
import java.util.Objects;

/**
 * Sorts arrays directly into descending order using selection sort.
 * Comparisons retain the normal Comparable and Comparator contracts.
 */
public final class SelectionSort {
    private SelectionSort() {
    }

    /**
     * Sorts an array from largest to smallest using its natural ordering.
     *
     * @param <T> the comparable element type
     * @param array the array to sort in place; elements must be non-null
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sort(array, Comparator.naturalOrder());
    }

    /**
     * Sorts an array from largest to smallest according to the comparator.
     *
     * @param <T> the element type
     * @param array the array to sort in place
     * @param comparator the comparison rule; a positive result means larger
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        Objects.requireNonNull(array, "Array must not be null");
        Objects.requireNonNull(comparator, "Comparator must not be null");
        for (int start = 0; start < array.length - 1; start++) {
            int largest = start;
            for (int index = start + 1; index < array.length; index++) {
                if (comparator.compare(array[index], array[largest]) > 0) {
                    largest = index;
                }
            }
            if (largest != start) {
                T temp = array[start];
                array[start] = array[largest];
                array[largest] = temp;
            }
        }
    }

}