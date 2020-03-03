package ca.intware.qt;

import org.javamoney.moneta.Money;

import java.util.*;

public class Cart {
    private final List<Item> purchaseList = new ArrayList<>();
    private final Map<Item, Price> priceCatalog;

    public Cart (final Map<Item, Price> priceCatalog) {
        Objects.requireNonNull(priceCatalog, "price catalog cannot be null");
        this.priceCatalog = new HashMap<>(priceCatalog);
    }

    public void purchase(final Item item) {
        purchaseList.add(new Item(item));
    }

    public Money total() {
        throw new UnsupportedOperationException("You complete me...");
    }
}
