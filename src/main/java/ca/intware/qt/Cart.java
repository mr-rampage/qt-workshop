package ca.intware.qt;

import org.javamoney.moneta.Money;

import java.util.*;

public class Cart {
    private final List<Product> purchaseList = new ArrayList<>();
    private final Map<Product, Price> priceCatalog;

    public Cart (final Map<Product, Price> priceCatalog) {
        Objects.requireNonNull(priceCatalog, "price catalog cannot be null");
        this.priceCatalog = new HashMap<>(priceCatalog);
    }

    public void add(final Product product) {
        purchaseList.add(new Product(product));
    }

    public Money total() {
        throw new UnsupportedOperationException("You complete me...");
    }
}
