package com.intware.qt.generators;

import ca.intware.qt.SpecialPrice;
import org.quicktheories.core.Gen;

import static com.intware.qt.generators.MoneyDSL.money;
import static org.quicktheories.generators.SourceDSL.integers;

public final class SpecialPriceDSL {
    public static Gen<SpecialPrice> specialPrices() {
        return integers().allPositive().zip(money(), SpecialPrice::new);
    }
}
