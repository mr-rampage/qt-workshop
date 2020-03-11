package ca.intware.qt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;
import javax.money.convert.MonetaryConversions;

import static ca.intware.qt.generators.MoneyDSL.currencies;
import static org.quicktheories.QuickTheory.qt;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class InvariantTest {

    @Test
    void test_after_addition_currency_remains_the_same() {
        qt()
                .forAll(currencies())
                .check(currency -> Monetary.isCurrencyAvailable(currency.getCurrencyCode()));
    }

    @Test
    void test_converter_availability() {
        qt()
                .forAll(currencies(), currencies())
                .assuming((a, b) -> !a.equals(b))
                .check((a, b) -> MonetaryConversions.isConversionAvailable(a.getCurrencyCode(), b.getCurrencyCode()));
    }
}
