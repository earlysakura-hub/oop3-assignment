package products;

/**
 * A pair of jeans product. Its real price difference is weighted by 0.5.
 */
public class Jeans extends Product {

	/**
     * Creates a pair of jeans product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public Jeans(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    /**
     * Calculates the real difference as 0.5 times the absolute difference
     * between the original and updated prices.
     *
     * @return the weighted absolute price difference
     */
    @Override
    public double calcRealDiff() {
        return 0.5 * Math.abs(originalPrice - updatedPrice);
    }
}