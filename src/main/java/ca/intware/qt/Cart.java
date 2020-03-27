package ca.intware.qt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cart {
    private final List<Product> purchaseList = new ArrayList<>();
    private final ProductPriceCatalog productPriceCatalog;

    public Cart (final ProductPriceCatalog productPriceCatalog) {
        Objects.requireNonNull(productPriceCatalog, "price catalog cannot be null");
        if (productPriceCatalog.getProductList().size() == 0) {
            throw new IllegalArgumentException("Product Price catalog cannot be empty");
        }
        this.productPriceCatalog = productPriceCatalog;
    }

    public void add(final Product product) {
        purchaseList.add(new Product(product));
    }

    public Integer total() {
        var purchaseCount = purchaseList.stream().collect(
                Collectors.toMap(productPriceCatalog::getProductPrice, value -> 1, Integer::sum)
        );

        var specialAndRegularPriceList = purchaseCount.keySet().stream()
                .collect(Collectors.partitioningBy(price -> price instanceof PriceWithSpecial));

        var totalSpecials = specialAndRegularPriceList.get(true).stream()
                .map(calculatePurchaseWithSpecials(purchaseCount))
                .reduce(0, Integer::sum);

        var totalRegular = specialAndRegularPriceList.get(false).stream()
                .map(calculatePurchaseWithoutSpecials(purchaseCount))
                .reduce(0, Integer::sum);

        return totalSpecials + totalRegular;
    }

    private Function<Price, Integer> calculatePurchaseWithoutSpecials(Map<Price, Integer> purchaseCount) {
        return price -> price.unitPrice * purchaseCount.get(price);
    }

    private Function<Price, Integer> calculatePurchaseWithSpecials(Map<Price, Integer> purchaseCount) {
        return price -> {
            var quantity = purchaseCount.get(price);
            var priceWithSpecial = (PriceWithSpecial) price;
            var specialPrice = priceWithSpecial.specialPrice;
            var specialPriceQuantity = quantity / specialPrice.rebateQuantity;
            var regularPriceQuantity = quantity % specialPrice.rebateQuantity;
            return specialPrice.rebateTotal.unitPrice * specialPriceQuantity + regularPriceQuantity * price.unitPrice;
        };
    }

    @Override
    public String toString() {
        return "Cart{" +
                "purchases=" + purchaseList.size() +
                ", products=" + productPriceCatalog.getProductList().size() +
                '}';
    }
}
