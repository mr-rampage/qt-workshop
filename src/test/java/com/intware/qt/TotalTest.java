package com.intware.qt;

import ca.intware.qt.Cart;
import ca.intware.qt.Product;
import ca.intware.qt.Price;
import ca.intware.qt.SpecialPrice;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import java.util.HashMap;

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
        var priceCatalog = new HashMap<Product, Price>() {
            {
                put(new Product("A"), new Price(Money.of(50, CAD), new SpecialPrice(3, Money.of(150, CAD))));
                put(new Product("B"), new Price(Money.of(30, CAD), new SpecialPrice(2, Money.of(45, CAD))));
                put(new Product("C"), new Price(Money.of(20, CAD)));
                put(new Product("D"), new Price(Money.of(15, CAD)));
            }
        };
        cart = new Cart(priceCatalog);
    }

    @Test
    void should_calculate_the_total_of_the_shopping_cart() {
        qt()
            .forAll(integers().all())
            .check(generatedInteger -> cart.total().equals(Money.zero(CAD)));
    }
}
