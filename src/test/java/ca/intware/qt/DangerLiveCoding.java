package ca.intware.qt;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;

import javax.money.CurrencyUnit;

import static org.quicktheories.QuickTheory.qt;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DangerLiveCoding {
    private Gen<Money> money() {
        throw new UnsupportedOperationException("Good luck on the live demo!");
        /*
        return bigDecimals().ofBytes(32).withScale(2)
                .zip(currencyUnits(), Money::of);
         */
    }

    private Gen<CurrencyUnit> currencyUnits() {
        throw new UnsupportedOperationException("I hope you remember what you're doing.");
        /*
        return Generate.pick(new ArrayList<>(Monetary.getCurrencies()));
         */
    }

    @Test
    void test_generator() {
        qt()
                .forAll(money())
                .check(money -> {
                    System.out.println(money.toString());
                    return true;
                });
    }
}
