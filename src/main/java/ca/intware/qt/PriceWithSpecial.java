package ca.intware.qt;

import java.util.Objects;

public class PriceWithSpecial extends Price {
    public final SpecialPrice specialPrice;

    public PriceWithSpecial(final Price regularPrice, final SpecialPrice specialPrice) {
        super(regularPrice.unitPrice);
        Objects.requireNonNull(specialPrice, "special price must not be null");
        this.specialPrice = specialPrice;
    }
}
