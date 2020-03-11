package ca.intware.qt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static ca.intware.qt.generators.MoneyDSL.money;
import static org.quicktheories.QuickTheory.qt;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class IdempotentTest {
    @Test
    void test_casing_idempotent() {
        qt()
                .forAll(money().map(money -> money.multiply(-1)))
                .check(money -> money.abs().abs().equals(money.abs()));
    }
}
