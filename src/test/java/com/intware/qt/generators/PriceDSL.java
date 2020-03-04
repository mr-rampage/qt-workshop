package com.intware.qt.generators;

import ca.intware.qt.Price;
import org.javamoney.moneta.Money;
import org.quicktheories.core.Gen;

import java.util.function.Function;

import static com.intware.qt.generators.MoneyDSL.money;
import static com.intware.qt.generators.SpecialPriceDSL.specialPrices;

public final class PriceDSL {
    public static Gen<Price> prices() {
        return regularPrices().mix(rebatedPrices(), 25);
    }

    public static Gen<Price> regularPrices() {
        return money().map((Function<Money, Price>) Price::new);
    }

    public static Gen<Price> rebatedPrices() {
        return money().zip(specialPrices(), Price::new);
    }
}
