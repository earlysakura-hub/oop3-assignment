package utilities;

import java.util.Comparator;
import products.Product;

public class OriginalPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getOriginalPrice(), p2.getOriginalPrice());
    }
}
