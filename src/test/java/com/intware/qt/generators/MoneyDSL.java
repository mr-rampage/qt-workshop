package com.intware.qt.generators;

import org.javamoney.moneta.Money;
import org.quicktheories.core.Gen;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

import static org.quicktheories.generators.SourceDSL.integers;

public final class MoneyDSL {
    private static final Locale[] LOCALES = Locale.getAvailableLocales();

    public static Gen<Money> money() {
        return integers().allPositive()
                .zip(currentyUnit(), Money::of);
    }

    private static Gen<CurrencyUnit> currentyUnit() {
        return locale()
                .map(locale -> Monetary.getCurrency(locale));
    }

    private static Gen<Locale> locale() {
        return integers().between(0, LOCALES.length)
                .map(index -> LOCALES[index]);
    }
}
