package utilities;

import java.util.Comparator;
import java.util.Objects;

/**
 * Sorts arrays directly into descending order using quicksort with a median-of-three pivot.
 * Comparisons retain the normal Comparable and Comparator contracts.
 */
public final class QuickSort {
    private QuickSort() {
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
        quickSort(array, 0, array.length - 1, comparator);
    }

    /**
     * Partitions an inclusive range in descending order. Recursing only on
     * the smaller partition limits stack depth; the larger one uses a loop.
     *
     * @param <T> the element type
     * @param array the working array
     * @param low first index of the range
     * @param high last index of the range
     * @param comparator the comparison rule
     */
    private static <T> void quickSort(T[] array, int low, int high,
            Comparator<? super T> comparator) {
        while (low < high) {
            int middle = low + (high - low) / 2;
            T pivot = medianOfThree(array[low], array[middle], array[high], comparator);
            int left = low;
            int right = high;
            while (left <= right) {
                while (left <= right && comparator.compare(array[left], pivot) > 0) {
                    left++;
                }
                while (left <= right && comparator.compare(array[right], pivot) < 0) {
                    right--;
                }
                if (left <= right) {
                    T temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                    left++;
                    right--;
                }
            }
            if (right - low < high - left) {
                quickSort(array, low, right, comparator);
                low = left;
            } else {
                quickSort(array, left, high, comparator);
                high = right;
            }
        }
    }

    /**
     * Returns the median of the first, middle, and last values in a range.
     *
     * @param <T> the element type
     * @param first the first value
     * @param middle the middle value
     * @param last the last value
     * @param comparator the comparison rule
     * @return the median value according to the comparator
     */
    private static <T> T medianOfThree(T first, T middle, T last,
            Comparator<? super T> comparator) {
        if (comparator.compare(first, middle) > 0) {
            T temp = first;
            first = middle;
            middle = temp;
        }
        if (comparator.compare(middle, last) > 0) {
            T temp = middle;
            middle = last;
            last = temp;
        }
        if (comparator.compare(first, middle) > 0) {
            middle = first;
        }
        return middle;
    }

}
