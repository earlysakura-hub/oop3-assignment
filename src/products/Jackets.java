package products;

/**
 * A jacket product. Its real price difference is weighted by 0.7.
 */
public class Jackets extends Product {

	/**
     * Creates a jacket product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public Jackets(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    /**
     * Calculates the real difference as 0.7 times the absolute difference
     * between the original and updated prices.
     *
     * @return the weighted absolute price difference
     */
    @Override
    public double calcRealDiff() {
        return 0.7 * Math.abs(originalPrice - updatedPrice);
    }
}