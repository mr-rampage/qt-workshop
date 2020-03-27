package ca.intware.qt.generators;

import ca.intware.qt.Cart;
import ca.intware.qt.Product;
import ca.intware.qt.ProductPriceCatalog;
import org.quicktheories.core.Gen;

import java.util.List;

import static ca.intware.qt.generators.ProductPriceCatalogDSL.productPriceCatalogs;
import static org.quicktheories.generators.Generate.pick;
import static org.quicktheories.generators.SourceDSL.lists;

public final class CartDSL {
    public static Gen<Cart> carts() {
        return productPriceCatalogs()
                .flatMap(catalog -> purchaseList(catalog).map(purchases -> {
                    var cart = new Cart(catalog);
                    purchases.forEach(cart::add);
                    return cart;
                }));
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

    private static Gen<Product> getProduct(ProductPriceCatalog productPriceCatalog) {
        return pick(productPriceCatalog.getProductList());
    }
}
