package ca.intware.qt;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import java.util.*;

import static javax.money.Monetary.getCurrency;

public class ProductPriceCatalog {
    private final Map<Product, Price> catalog = new HashMap<>();
    private final CurrencyUnit currency;

    public ProductPriceCatalog(Locale locale) {
        Objects.requireNonNull(locale, "locale must be defined for catalog");
        currency = getCurrency(locale);
    }

    public void add(Product product, Number unitPrice) {
        catalog.put(product, new Price(Money.of(unitPrice, currency)));
    }

    public void add(Product product, Number unitPrice, Integer rebateQuantity, Number rebateTotal) {
        catalog.put(product,
                new Price(Money.of(unitPrice, currency))
                    .withSpecial(new SpecialPrice(rebateQuantity, Money.of(rebateTotal, currency))));
    }

    public Optional<Price> getProductPrice(Product product) {
        return Optional.ofNullable(catalog.get(product));
    }
}
