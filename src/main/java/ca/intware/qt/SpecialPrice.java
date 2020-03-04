package ca.intware.qt;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.util.Objects;

public final class SpecialPrice {
    public final Integer rebateQuantity;
    public final Money rebateTotal;

    public SpecialPrice(final Integer rebateQuantity, final Money rebateTotal) {
        Objects.requireNonNull(rebateQuantity, "rebate quantity must not be null");
        Objects.requireNonNull(rebateTotal, "rebate total must not be null");

        if (rebateQuantity < 0 || rebateTotal.getNumberStripped().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("invalid rebate");
        } else {
            this.rebateQuantity = rebateQuantity;
            this.rebateTotal = rebateTotal;
        }
    }
}
