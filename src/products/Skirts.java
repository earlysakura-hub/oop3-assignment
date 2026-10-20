package products;

public class Skirts extends Product {

    public Skirts(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    @Override
    public double calcRealDiff() {
        return 0.1 * Math.abs(originalPrice - updatedPrice);
    }
}