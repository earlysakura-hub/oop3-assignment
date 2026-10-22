package products;

/**
 * A pair of shoes product. Its real price difference is weighted by 0.4.
 */
public class Shoes extends Product {

	/**
     * Creates a pair of shoes product with the given prices.
     *
     * @param originalPrice the price before the update
     * @param updatedPrice  the price after the update
     */
    public Shoes(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    /**
     * Calculates the real difference as 0.4 times the absolute difference
     * between the original and updated prices.
     *
     * @return the weighted absolute price difference
     */
    @Override
    public double calcRealDiff() {
        return 0.4 * Math.abs(originalPrice - updatedPrice);
    }
}