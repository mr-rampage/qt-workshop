package ca.intware.qt.generators;

import ca.intware.qt.Cart;
import ca.intware.qt.Product;
import ca.intware.qt.ProductPriceCatalog;
import org.quicktheories.core.Gen;

import java.util.List;

import static ca.intware.qt.generators.ProductPriceCatalogDSL.productPriceCatalogs;

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
        throw new UnsupportedOperationException("You complete me...");
    }
}
