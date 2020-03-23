package ca.intware.qt.generators;

import ca.intware.qt.Price;
import ca.intware.qt.Product;
import ca.intware.qt.ProductPriceCatalog;
import org.quicktheories.core.Gen;

import static ca.intware.qt.generators.MoneyDSL.locales;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

public class ProductPriceCatalogDSL {

    public static Gen<ProductPriceCatalog> productPriceCatalogs() {
        return emptyProductPriceCatalogs()
                .zip(lists().of(productPricesWithoutSpecials()).ofSizes(integers().between(1, 500)), (catalog, productPriceList) -> {
                    productPriceList.forEach(productPrice -> catalog.add(productPrice.product, productPrice.price));
                    return catalog;
                });
    }

    private static Gen<ProductPriceCatalog> emptyProductPriceCatalogs() {
        return locales().map(ProductPriceCatalog::new);
    }

    private static Gen<ProductPrice> productPricesWithoutSpecials() {
        return ProductDSL.products().zip(PriceDSL.prices(), ProductPrice::new);
    }

    private static class ProductPrice {
        public final Product product;
        public final Price price;

        ProductPrice(Product product, Price price) {
            this.product = product;
            this.price = price;
        }
    }

}
