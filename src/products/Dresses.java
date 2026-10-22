package products;

/**
 * A dress product. Its real price difference is weighted by 0.6.
 */
public class Dresses extends Product {

	/**
     * Creates a dress product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public Dresses(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    /**
     * Calculates the real difference as 0.6 times the absolute difference
     * between the original and updated prices.
     *
     * @return the weighted absolute price difference
     */
    @Override
    public double calcRealDiff() {
        return 0.6 * Math.abs(originalPrice - updatedPrice);
    }
}