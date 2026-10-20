package products;

public class TShirts extends Product {

    public TShirts(double originalPrice, double updatedPrice) {
        super(originalPrice, updatedPrice);
    }

    @Override
    public double calcRealDiff() {
        return 0.2 * Math.abs(originalPrice - updatedPrice);
    }
}