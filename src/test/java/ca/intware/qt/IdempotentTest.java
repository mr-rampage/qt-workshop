package ca.intware.qt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static ca.intware.qt.generators.MoneyDSL.currencyUnit;
import static ca.intware.qt.generators.MoneyDSL.money;
import static javax.money.convert.MonetaryConversions.getConversion;
import static org.quicktheories.QuickTheory.qt;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class IdempotentTest {
    @Test
    void test_applying_absolute_multiple_times() {
        qt()
                .forAll(money().map(money -> money.multiply(-1)))
                .check(money -> money.abs().abs().equals(money.abs()));
    }

    @Test
    void test_applying_exchange_multiple_times() {
        qt()
                .forAll(money(), currencyUnit().map(currencyUnit -> getConversion(currencyUnit)))
                .check((money, conversion) -> money
                        .with(conversion)
                        .with(conversion)
                        .equals(money.with(conversion)));
    }
}
