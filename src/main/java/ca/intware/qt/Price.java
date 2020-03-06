package ca.intware.qt;

import org.javamoney.moneta.Money;

import javax.money.convert.CurrencyConversion;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public final class Price {
    public final Money unitPrice;
    public final Optional<SpecialPrice> specialPrice;

    public Price(final Money unitPrice) {
        this(unitPrice, Optional.empty());
    }

    private Price(final Money unitPrice, final Optional<SpecialPrice> specialPrice) {
        Objects.requireNonNull(unitPrice, "unit price must not be null");

        if (unitPrice.getNumberStripped().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("invalid price");
        } else {
            this.unitPrice = unitPrice;
            this.specialPrice = specialPrice;
        }
    }

    public Price withSpecial(SpecialPrice specialPrice) {
        return new Price(unitPrice, Optional.of(specialPrice));
    }

    public Price as(CurrencyConversion conversion) {
        return new Price(
                unitPrice.with(conversion),
                specialPrice.map(
                        rebate -> new SpecialPrice(rebate.rebateQuantity, rebate.rebateTotal.with(conversion))
                )
        );
    }
}
