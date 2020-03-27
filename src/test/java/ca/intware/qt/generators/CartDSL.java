package ca.intware.qt.generators;

import ca.intware.qt.Cart;
import ca.intware.qt.PriceWithSpecial;
import ca.intware.qt.Product;
import ca.intware.qt.ProductPriceCatalog;
import org.quicktheories.api.Pair;
import org.quicktheories.core.Gen;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ca.intware.qt.generators.ProductPriceCatalogDSL.productPriceCatalogsWithSpecials;
import static ca.intware.qt.generators.ProductPriceCatalogDSL.productPriceCatalogsWithoutSpecials;
import static org.quicktheories.generators.Generate.pick;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

public final class CartDSL {
    public static Gen<Pair<Cart, Integer>> carts() {
        return productPriceCatalogsWithoutSpecials()
                .flatMap(catalog -> purchaseList(catalog).map(createCart(catalog)))
                .mix(productPriceCatalogsWithSpecials()
                        .flatMap(catalog -> purchaseListWithSpecials(catalog).map(createCart(catalog))), 25);
    }

    /**
     * Creates a list of random products from a product price catalog
     *
     * @param productPriceCatalog product price catalog
     * @return A Gen of List of Product
     */
    public static Gen<Pair<List<Product>, Integer>> purchaseList(ProductPriceCatalog productPriceCatalog) {
        return lists().of(getProduct(productPriceCatalog)).ofSizeBetween(1, 10)
                .map(productAndPrice -> Pair.of(
                        productAndPrice.stream().map(pair -> pair._1).collect(Collectors.toList()),
                        productAndPrice.stream().map(pair -> pair._2).reduce(0, Integer::sum)
                ));
    }

    public static Gen<Pair<List<Product>, Integer>> purchaseListWithSpecials(ProductPriceCatalog productPriceCatalog) {
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

    private static Gen<Pair<Product, Integer>> getProduct(ProductPriceCatalog productPriceCatalog) {
        return pick(productPriceCatalog.getProductList())
                .map(product -> {
                    var price = productPriceCatalog.getProductPrice(product);
                    return Pair.of(product, price.unitPrice);
                });
    }

    private static Gen<Pair<List<Product>, Integer>> getSpecialProduct(ProductPriceCatalog productPriceCatalog) {
        return pick(productPriceCatalog.getProductList())
                .zip(integers().between(1, 3), (product, multiplier) -> {
                    var price = (PriceWithSpecial) productPriceCatalog.getProductPrice(product);
                    var quantity = price.specialPrice.rebateQuantity * multiplier;
                    var total = price.specialPrice.rebateTotal.unitPrice * multiplier;
                    return Pair.of(
                            IntStream.range(0, quantity).mapToObj(index -> product).collect(Collectors.toList()),
                            total
                    );
                });
    }

    private static Function<Pair<List<Product>, Integer>, Pair<Cart, Integer>> createCart(ProductPriceCatalog catalog) {
        return purchases -> {
            var cart = new Cart(catalog);
            purchases._1.forEach(cart::add);
            return Pair.of(cart, purchases._2);
        };
    }
}

