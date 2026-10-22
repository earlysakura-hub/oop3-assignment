package products;

/**
 * A pair of shorts product. Its real price difference is weighted by 0.3.
 */
public class Shorts extends Product {

	/**
     * Creates a pair of shorts product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public Shorts(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    /**
     * Calculates the real difference as 0.3 times the absolute difference
     * between the original and updated prices.
     *
     * @return the weighted absolute price difference
     */
    @Override
    public double calcRealDiff() {
        return 0.3 * Math.abs(originalPrice - updatedPrice);
    }
}