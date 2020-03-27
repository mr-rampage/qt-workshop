package ca.intware.qt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private final List<Product> purchaseList = new ArrayList<>();
    private final ProductPriceCatalog productPriceCatalog;

    public Cart (final ProductPriceCatalog productPriceCatalog) {
        Objects.requireNonNull(productPriceCatalog, "price catalog cannot be null");
        if (productPriceCatalog.getProductList().size() == 0) {
            throw new IllegalArgumentException("Product Price catalog cannot be empty");
        }
        this.productPriceCatalog = productPriceCatalog;
    }

    public void add(final Product product) {
        purchaseList.add(new Product(product));
    }

    public Integer total() {
        return this.purchaseList.stream()
                .map(productPriceCatalog::getProductPrice)
                .map(price -> price.unitPrice)
                .reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "purchases=" + purchaseList.size() +
                ", products=" + productPriceCatalog.getProductList().size() +
                '}';
    }
}
