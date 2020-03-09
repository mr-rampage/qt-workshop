package ca.intware.qt;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;

import static java.util.Locale.CANADA;
import static javax.money.Monetary.getCurrency;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TotalTest {

    private Cart cart;
    private final CurrencyUnit CAD = getCurrency(CANADA);

    @Test
    void should_calculate_the_total_of_the_shopping_cart() {
        qt()
                .forAll(integers().all())
                .check(generatedInteger -> cart.total().equals(Money.zero(CAD)));
    }
}
