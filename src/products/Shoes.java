package products;

public class Shoes extends Product {

    public Shoes(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    @Override
    public double calcRealDiff() {
        return 0.4 * Math.abs(originalPrice - updatedPrice);
    }
}