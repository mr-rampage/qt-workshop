package ca.intware.qt.generators;

import ca.intware.qt.Price;
import ca.intware.qt.PriceWithSpecial;
import org.quicktheories.core.Gen;

import static ca.intware.qt.generators.SpecialPriceDSL.specialPrices;
import static org.quicktheories.generators.SourceDSL.integers;

public final class PriceDSL {
    public static Gen<Price> prices() {
        return integers().between(1, 10000).map(Price::new);
    }

    public static Gen<Price> pricesWithSpecials() {
        return prices().zip(specialPrices(), PriceWithSpecial::new);
    }

    public static Gen<Price> mixPrices() {
        return prices().mix(pricesWithSpecials(), 25);
    }
}
