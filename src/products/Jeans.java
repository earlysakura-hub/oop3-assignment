package products;

public class Jeans extends Product {

    public Jeans(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    @Override
    public double calcRealDiff() {
        return 0.5 * Math.abs(originalPrice - updatedPrice);
    }
}