package products;

/**
 * Base class for all clothing products in the store.
 * <p>
 * A product has an original price and an updated price. Each concrete subclass
 * defines how the "real difference" between those prices is calculated through
 * {@link #calcRealDiff()}. The natural ordering of products
 * ({@link #compareTo(Product)}) is by real difference.
 */
public abstract class Product implements Comparable<Product> {

	/** The price of the product before the update. */
    protected double originalPrice;
    /** The price of the product after the update. */
    protected double updatedPrice;

    /**
     * Creates a product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public Product(double originalPrice, double updatedPrice) {
        this.originalPrice = originalPrice;
        this.updatedPrice = updatedPrice;
    }

    /**
     * Calculates the real difference between the original and updated prices.
     * Each product type applies its own weighting to the absolute price
     * difference.
     *
     * @return the weighted, non-negative difference between the two prices
     */
    public abstract double calcRealDiff();
    
    /**
     * Compares this product with another by real difference, as returned by
     * {@link #calcRealDiff()}.
     *
     * @param other the product to compare against
     * @return a negative integer, zero, or a positive integer if this product's
     *         real difference is less than, equal to, or greater than the
     *         other's
     * @throws NullPointerException if {@code other} is {@code null}
     */
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.calcRealDiff(), other.calcRealDiff());
    }
    
    /**
     * Returns the original price of this product.
     *
     * @return the price before the update
     */
    public double getOriginalPrice() {
        return originalPrice;
    }

    /**
     * Returns the updated price of this product.
     *
     * @return the price after the update
     */
    public double getUpdatedPrice() {
        return updatedPrice;
    }
}