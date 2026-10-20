package products;

public class Jackets extends Product {

    public Jackets(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    @Override
    public double calcRealDiff() {
        return 0.7 * Math.abs(originalPrice - updatedPrice);
    }
}