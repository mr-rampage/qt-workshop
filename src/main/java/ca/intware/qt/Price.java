package ca.intware.qt;

import org.javamoney.moneta.Money;

import java.util.Objects;
import java.util.Optional;

import static java.util.Locale.CANADA;
import static javax.money.Monetary.getCurrency;
import static org.javamoney.moneta.Money.zero;

public final class Price {
    public final Money unitPrice;
    public final Optional<SpecialPrice> specialPrice;

    public Price(final Money unitPrice) {
        this(unitPrice, null);
    }

    public Price(final Money unitPrice, final SpecialPrice specialPrice) {
        Objects.requireNonNull(unitPrice, "unit price must not be null");

        if (unitPrice.isLessThan(zero(getCurrency(CANADA)))) {
            throw new IllegalArgumentException("invalid price");
        } else {
            this.unitPrice = unitPrice;
            this.specialPrice = Optional.ofNullable(specialPrice);
        }
    }
}
