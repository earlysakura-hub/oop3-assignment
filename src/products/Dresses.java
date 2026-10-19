package products;

public class Dresses extends Product {

    public Dresses(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    @Override
    public double calcRealDiff() {
        return 0.6 * Math.abs(originalPrice - updatedPrice);
    }
}