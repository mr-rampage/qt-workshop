package ca.intware.qt;

import org.javamoney.moneta.Money;

import java.util.Objects;

import static java.util.Locale.CANADA;
import static javax.money.Monetary.getCurrency;
import static org.javamoney.moneta.Money.zero;

public final class SpecialPrice {
    public final Integer rebateQuantity;
    public final Money rebateTotal;

    public SpecialPrice(final Integer rebateQuantity, final Money rebateTotal) {
        Objects.requireNonNull(rebateQuantity, "rebate quantity must not be null");
        Objects.requireNonNull(rebateTotal, "rebate total must not be null");

        if (rebateQuantity < 0 || rebateTotal.isLessThan(zero(getCurrency(CANADA)))) {
            throw new IllegalArgumentException("invalid rebate");
        } else {
            this.rebateQuantity = rebateQuantity;
            this.rebateTotal = rebateTotal;
        }
    }
}
