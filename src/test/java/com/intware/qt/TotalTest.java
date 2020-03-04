package com.intware.qt;

import ca.intware.qt.Cart;
import ca.intware.qt.Product;
import ca.intware.qt.ProductPriceCatalog;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;

import static java.util.Locale.CANADA;
import static javax.money.Monetary.getCurrency;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TotalTest {

    private Cart cart;
    private final CurrencyUnit CAD = getCurrency(CANADA);

    @BeforeEach
    void setup() {
        var priceCatalog = new ProductPriceCatalog(CANADA);

        priceCatalog.add(new Product("A"), 50, 3, 150);
        priceCatalog.add(new Product("B"), 30, 2, 45);
        priceCatalog.add(new Product("C"), 20);
        priceCatalog.add(new Product("D"), 15);

        cart = new Cart(priceCatalog);
    }

    @Test
    void should_calculate_the_total_of_the_shopping_cart() {
        qt()
                .forAll(integers().all())
                .check(generatedInteger -> cart.total().equals(Money.zero(CAD)));
    }
}
