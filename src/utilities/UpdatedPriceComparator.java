package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Compares {@link Product} objects by their updated price, in ascending numerical
 * order. Used by the sorting algorithms to rank products by
 * {@link Product#getUpdatedPrice()}.
 */
public class UpdatedPriceComparator implements Comparator<Product> {

	/**
     * Compares two products by their updated price.
     *
     * @param p1 the first product
     * @param p2 the second product
     * @return a negative integer, zero, or a positive integer if the first
     *         product's updated price is less than, equal to, or greater than
     *         the second's
     * @throws NullPointerException if either product is {@code null}
     */
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getUpdatedPrice(), p2.getUpdatedPrice());
    }
}