package products;

/**
 * A T-shirt product. Its real price difference is weighted by 0.2.
 */
public class TShirts extends Product {

	/**
     * Creates a T-shirt product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public TShirts(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    /**
     * Calculates the real difference as 0.2 times the absolute difference
     * between the original and updated prices.
     *
     * @return the weighted absolute price difference
     */
    @Override
    public double calcRealDiff() {
        return 0.2 * Math.abs(originalPrice - updatedPrice);
    }
}