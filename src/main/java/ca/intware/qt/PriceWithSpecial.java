package ca.intware.qt;

import org.javamoney.moneta.Money;

import javax.money.convert.CurrencyConversion;
import java.math.BigDecimal;
import java.util.Objects;

public class PriceWithSpecial extends Price {
    public final SpecialPrice specialPrice;

    public PriceWithSpecial(final Money unitPrice, final SpecialPrice specialPrice) {
        super(unitPrice);

        Objects.requireNonNull(specialPrice, "special price must not be null");

        if (specialPrice.rebateQuantity > 0 && specialPrice.rebateTotal.getNumberStripped().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("invalid special price");
        } else {
            this.specialPrice = specialPrice;
        }
    }

    public Price as(CurrencyConversion conversion) {
        return new PriceWithSpecial(unitPrice.with(conversion),
                new SpecialPrice(specialPrice.rebateQuantity, specialPrice.rebateTotal.with(conversion)));
    }
}
