package products;

/**
 * A skirt product. Its real price difference is weighted by 0.1.
 */
public class Skirts extends Product {

	/**
     * Creates a skirt product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public Skirts(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    /**
     * Calculates the real difference as 0.1 times the absolute difference
     * between the original and updated prices.
     *
     * @return the weighted absolute price difference
     */
    @Override
    public double calcRealDiff() {
        return 0.1 * Math.abs(originalPrice - updatedPrice);
    }
}