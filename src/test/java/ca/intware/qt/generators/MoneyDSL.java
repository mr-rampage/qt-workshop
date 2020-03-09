package ca.intware.qt.generators;

import org.javamoney.moneta.Money;
import org.quicktheories.core.Gen;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

import static org.quicktheories.generators.SourceDSL.bigDecimals;
import static org.quicktheories.generators.SourceDSL.integers;

public final class MoneyDSL {
    private static final Locale[] LOCALES = Locale.getAvailableLocales();

    public static Gen<Money> money() {
        return bigDecimals().ofBytes(32).withScale(2)
                .zip(currencyUnit(), Money::of);
    }

    public static Gen<Money> positiveMoney() {
        return money()
                .map(Money::abs);
    }

    private static Gen<CurrencyUnit> currencyUnit() {
        return locales()
                .map(locale -> Monetary.getCurrency(locale));
    }

    public static Gen<Locale> locales() {
        return integers().between(0, LOCALES.length)
                .map(index -> LOCALES[index]);
    }
}
