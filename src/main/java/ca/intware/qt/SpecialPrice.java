package ca.intware.qt;

import java.util.Objects;

public final class SpecialPrice {
    public final Integer rebateQuantity;
    public final Price rebateTotal;

    public SpecialPrice(final Integer rebateQuantity, final Price rebateTotal) {
        Objects.requireNonNull(rebateQuantity, "rebate quantity must not be null");
        Objects.requireNonNull(rebateTotal, "rebate total must not be null");

        if (rebateQuantity < 0) {
            throw new IllegalArgumentException("invalid rebate");
        } else {
            this.rebateQuantity = rebateQuantity;
            this.rebateTotal = rebateTotal;
        }
    }
}
