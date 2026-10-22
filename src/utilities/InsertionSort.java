package utilities;

import java.util.Comparator;
import products.Product;

public class InsertionSort {

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
