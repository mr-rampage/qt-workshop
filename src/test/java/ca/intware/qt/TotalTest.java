package ca.intware.qt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static ca.intware.qt.generators.CartDSL.carts;
import static org.quicktheories.QuickTheory.qt;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TotalTest {
    @Test
    void should_calculate_the_total_of_the_shopping_cart() {
        qt()
                .forAll(carts())
                .check(cartAndTotal -> {
                    var cart = cartAndTotal._1;
                    var total = cartAndTotal._2;
                    return cart.total().equals(total) && cart.total() > 0;
                });
    }
}
