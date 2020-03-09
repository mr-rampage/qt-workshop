package ca.intware.qt;

import org.javamoney.moneta.Money;

import javax.money.convert.CurrencyConversion;
import java.math.BigDecimal;
import java.util.Objects;

public class Price {
    public final Money unitPrice;

    public Price(final Money unitPrice) {
        Objects.requireNonNull(unitPrice, "unit price must not be null");

        if (unitPrice.getNumberStripped().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("invalid price");
        } else {
            this.unitPrice = unitPrice;
        }
    }

    public Price as(CurrencyConversion conversion) {
        return new Price(unitPrice.with(conversion));
    }
}
