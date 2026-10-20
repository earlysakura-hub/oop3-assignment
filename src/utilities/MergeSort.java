package utilities;

import java.util.Comparator;
import java.util.Objects;

/**
 * Sorts arrays directly into descending order using merge sort.
 * Comparisons retain the normal Comparable and Comparator contracts.
 */
public final class MergeSort {
    private MergeSort() {
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
        if (array.length < 2) {
            return;
        }
        T[] buffer = array.clone();
        mergeSort(array, buffer, 0, array.length - 1, comparator);
    }

    /**
     * Recursively sorts both halves of an inclusive range, then merges them.
     *
     * @param <T> the element type
     * @param array the working array
     * @param buffer reusable temporary storage
     * @param low first index of the range
     * @param high last index of the range
     * @param comparator the comparison rule
     */
    private static <T> void mergeSort(T[] array, T[] buffer, int low, int high,
            Comparator<? super T> comparator) {
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