package ca.intware.qt.generators;

import ca.intware.qt.Cart;
import ca.intware.qt.PriceWithSpecial;
import ca.intware.qt.Product;
import ca.intware.qt.ProductPriceCatalog;
import org.quicktheories.api.Pair;
import org.quicktheories.core.Gen;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ca.intware.qt.generators.ProductPriceCatalogDSL.productPriceCatalogs;
import static ca.intware.qt.generators.ProductPriceCatalogDSL.productPriceCatalogsWithSpecials;
import static org.quicktheories.generators.Generate.pick;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

public final class CartDSL {
    public static Gen<Pair<Cart, Integer>> carts() {
        return productPriceCatalogs()
                .flatMap(catalog -> purchaseList(catalog).map(purchases -> {
                    var cart = new Cart(catalog);
                    var expected = purchases.stream()
                            .map(catalog::getProductPrice)
                            .map(price -> price.unitPrice)
                            .reduce(0, Integer::sum);
                    purchases.forEach(cart::add);
                    return Pair.of(cart, expected);
                }));
    }

    public static Gen<Pair<Cart, Integer>> cartsWithSpecials() {
        return productPriceCatalogsWithSpecials()
                .flatMap(catalog -> purchaseListWithSpecials(catalog)
                        .map(purchaseList -> {
                            var cart = new Cart(catalog);
                            purchaseList._1.forEach(cart::add);
                            return Pair.of(cart, purchaseList._2);
                        })
                );
    }

    /**
     * Creates a list of random products from a product price catalog
     *
     * @param productPriceCatalog product price catalog
     * @return A Gen of List of Product
     */
    private static Gen<List<Product>> purchaseList(ProductPriceCatalog productPriceCatalog) {
        return lists().of(getProduct(productPriceCatalog)).ofSizeBetween(1, 10);
    }

    private static Gen<Pair<List<Product>, Integer>> purchaseListWithSpecials(ProductPriceCatalog productPriceCatalog) {
        return lists().of(getSpecialProduct(productPriceCatalog)).ofSizeBetween(1, 10)
                .map(purchaseAndTotal -> {
                    var flattenedList = purchaseAndTotal.stream()
                            .map(pair -> pair._1)
                            .flatMap(List::stream)
                            .collect(Collectors.toList());
                    /* shuffle is not functional :( */
                    Collections.shuffle(flattenedList);

                    var total = purchaseAndTotal.stream()
                            .map(pair -> pair._2)
                            .reduce(0, Integer::sum);

                    return Pair.of(flattenedList, total);
                });
    }

    private static Gen<Product> getProduct(ProductPriceCatalog productPriceCatalog) {
        return pick(productPriceCatalog.getProductList());
    }

    private static Gen<Pair<List<Product>, Integer>> getSpecialProduct(ProductPriceCatalog productPriceCatalog) {
        return pick(productPriceCatalog.getProductList())
                .zip(integers().between(1, 3), (product, multiplier) -> {
                    var price = (PriceWithSpecial) productPriceCatalog.getProductPrice(product);
                    var quantity = price.specialPrice.rebateQuantity * multiplier;
                    var total = price.specialPrice.rebateTotal.unitPrice * multiplier;
                    return Pair.of(
                            IntStream.of(quantity).mapToObj(index -> product).collect(Collectors.toList()),
                            total
                    );
                });
    }
}

