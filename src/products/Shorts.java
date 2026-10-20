package products;

public class Shorts extends Product {

    public Shorts(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    @Override
    public double calcRealDiff() {
        return 0.3 * Math.abs(originalPrice - updatedPrice);
    }
}