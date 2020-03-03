package com.intware.qt;

import ca.intware.qt.Cart;
import ca.intware.qt.Item;
import ca.intware.qt.Price;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.util.HashMap;
import java.util.Locale;

import static javax.money.Monetary.getCurrency;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TotalTest {

    private Cart cart;

    @BeforeEach
    void setup() {
        var cad = getCurrency(Locale.CANADA);
        var priceCatalog = new HashMap<Item, Price>() {
            {
                put(new Item("A"), new Price(Money.of(50, cad)));
                put(new Item("B"), new Price(Money.of(30, cad)));
            }
        };
        var cart = new Cart(priceCatalog);
    }

}
