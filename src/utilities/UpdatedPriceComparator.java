package utilities;

import java.util.Comparator;
import products.Product;

public class UpdatedPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getUpdatedPrice(), p2.getUpdatedPrice());
    }
}