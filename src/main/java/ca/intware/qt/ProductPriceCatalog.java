package ca.intware.qt;

import javax.money.convert.CurrencyConversion;
import java.util.*;

import static javax.money.Monetary.getCurrency;
import static javax.money.convert.MonetaryConversions.getConversion;

public class ProductPriceCatalog {
    private final Map<Product, Price> catalog = new HashMap<>();
    private final CurrencyConversion convertToLocale;

    public ProductPriceCatalog(Locale locale) {
        Objects.requireNonNull(locale, "locale must be defined for catalog");
        convertToLocale = getConversion(getCurrency(locale));
    }

    public void add(Product product, Price price) {
        catalog.put(product, price.as(convertToLocale));
    }

    public List<Product> getProductList() {
        return List.of(catalog.keySet().toArray(new Product[]{}));
    }

    public Optional<Price> getProductPrice(Product product) {
        return Optional.ofNullable(catalog.get(product));
    }
}
