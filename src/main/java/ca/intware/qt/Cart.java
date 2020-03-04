package ca.intware.qt;

import org.javamoney.moneta.Money;

import java.util.*;

public class Cart {
    private final List<Product> purchaseList = new ArrayList<>();
    private final ProductPriceCatalog productPriceCatalog;

    public Cart (final ProductPriceCatalog productPriceCatalog) {
        Objects.requireNonNull(productPriceCatalog, "price catalog cannot be null");
        this.productPriceCatalog = productPriceCatalog;
    }

    public void add(final Product product) {
        purchaseList.add(new Product(product));
    }

    public Money total() {
        throw new UnsupportedOperationException("You complete me...");
    }
}
