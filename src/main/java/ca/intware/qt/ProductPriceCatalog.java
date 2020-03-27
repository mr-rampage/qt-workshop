package ca.intware.qt;

import java.util.*;

public class ProductPriceCatalog {
    private final Map<Product, Price> catalog = new HashMap<>();

    public ProductPriceCatalog(Locale locale) {
        Objects.requireNonNull(locale, "locale must be defined for catalog");
    }

    public void add(Product product, Price price) {
        catalog.put(product, price);
    }

    public List<Product> getProductList() {
        return List.of(catalog.keySet().toArray(new Product[]{}));
    }

    public Price getProductPrice(Product product) {
        return catalog.get(product);
    }
}
