package ca.intware.qt.generators;

import ca.intware.qt.SpecialPrice;
import org.quicktheories.core.Gen;

import static ca.intware.qt.generators.PriceDSL.prices;
import static org.quicktheories.generators.SourceDSL.integers;

public final class SpecialPriceDSL {
    public static Gen<SpecialPrice> specialPrices() {
        return integers().allPositive()
                .zip(prices(), SpecialPrice::new);
    }
}
