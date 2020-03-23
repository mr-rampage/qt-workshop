package ca.intware.qt.generators;

import org.javamoney.moneta.Money;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

import static org.quicktheories.generators.SourceDSL.bigDecimals;

public final class MoneyDSL {

    public static Gen<Money> money() {
        return bigDecimals().ofBytes(32).withScale(2)
                .zip(currencyUnit(), Money::of);
    }

    public static Gen<Money> money(CurrencyUnit currencyUnit) {
        return money().map(money -> Money.of(money.getNumber(), currencyUnit));
    }

    public static Gen<Money> positiveMoney() {
        return money()
                .map(Money::abs);
    }

    public static Gen<CurrencyUnit> currencyUnit() {
        return Generate.pick(Arrays.asList(
                Monetary.getCurrency("CNY"),
                Monetary.getCurrency("EUR"),
                Monetary.getCurrency("GBP"),
                Monetary.getCurrency("JPY"),
                Monetary.getCurrency("USD"),
                Monetary.getCurrency("RUB"),
                Monetary.getCurrency("CAD")
        ));
    }

    public static Gen<Locale> locales() {
        return Generate.constant(Locale.CANADA);
    }

    public static Gen<Currency> currencies() {
        return Generate.pick(new ArrayList<>(Currency.getAvailableCurrencies()));
    }
}
