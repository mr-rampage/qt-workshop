package ca.intware.qt.generators;

import ca.intware.qt.Price;
import ca.intware.qt.PriceWithSpecial;
import org.quicktheories.core.Gen;

import static ca.intware.qt.generators.MoneyDSL.positiveMoney;
import static ca.intware.qt.generators.SpecialPriceDSL.specialPrices;

public final class PriceDSL {
    public static Gen<Price> prices() {
        return regularPrices().mix(rebatedPrices(), 25);
    }

    public static Gen<Price> regularPrices() {
        return positiveMoney().map(Price::new);
    }

    public static Gen<Price> rebatedPrices() {
        return positiveMoney().zip(specialPrices(), PriceWithSpecial::new);
    }
}
