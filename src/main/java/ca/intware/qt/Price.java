package ca.intware.qt;

import java.util.Objects;

public class Price {
    public final Integer unitPrice;

    public Price(final Integer unitPrice) {
        Objects.requireNonNull(unitPrice, "unit price must not be null");

        if (unitPrice.equals(0)) {
            throw new IllegalArgumentException("invalid price");
        } else {
            this.unitPrice = unitPrice;
        }
    }
}
