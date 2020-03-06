package com.intware.qt.generators;

import ca.intware.qt.Price;
import org.quicktheories.core.Gen;

import static com.intware.qt.generators.MoneyDSL.positiveMoney;
import static com.intware.qt.generators.SpecialPriceDSL.specialPrices;

public final class PriceDSL {
    public static Gen<Price> prices() {
        return regularPrices().mix(rebatedPrices(), 25);
    }

    public static Gen<Price> regularPrices() {
        return positiveMoney().map(Price::new);
    }

    public static Gen<Price> rebatedPrices() {
        return positiveMoney().zip(specialPrices(), (price, special) -> new Price(price).withSpecial(special));
    }
}
