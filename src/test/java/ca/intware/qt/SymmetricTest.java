package ca.intware.qt;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;

import static ca.intware.qt.generators.MoneyDSL.currencyUnit;
import static ca.intware.qt.generators.MoneyDSL.money;
import static javax.money.convert.MonetaryConversions.getConversion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.quicktheories.QuickTheory.qt;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SymmetricTest {
    @Test
    void test_conversions_symmetry() {
        qt()
                .forAll(money(), currencyUnit())
                .assuming((money, currencyUnit) -> !money.getCurrency().equals(currencyUnit))
                .check((money, currency) -> {
                    var toOriginal = getConversion(money.getCurrency());
                    var toNew = getConversion(currency);
                    return money
                            .with(toNew)
                            .with(toOriginal)
                            .with(Monetary.getRounding(money.getCurrency()))
                            .equals(money);
                });
    }

    @Test
    void test_conversion_from_gbp_cny() {
        var money = Money.of(1310.72, "GBP");
        var toGBP = getConversion(money.getCurrency());
        var toCNY = getConversion(Monetary.getCurrency("CNY"));
        assertEquals(money.with(toCNY).with(toGBP).with(Monetary.getRounding(money.getCurrency())), money);
    }
}
