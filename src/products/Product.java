package products;

public abstract class Product implements Comparable<Product> {

    protected double originalPrice;
    protected double updatedPrice;

    public Product(double originalPrice, double updatedPrice) {
        this.originalPrice = originalPrice;
        this.updatedPrice = updatedPrice;
    }

    public abstract double calcRealDiff();
    
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.calcRealDiff(), other.calcRealDiff());
    }
    
    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getUpdatedPrice() {
        return updatedPrice;
    }
}