package ca.intware.qt.generators;

import ca.intware.qt.Price;
import ca.intware.qt.Product;
import ca.intware.qt.ProductPriceCatalog;
import org.quicktheories.api.Pair;
import org.quicktheories.core.Gen;

import static ca.intware.qt.generators.MoneyDSL.locales;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

public class ProductPriceCatalogDSL {

    public static Gen<ProductPriceCatalog> productPriceCatalogs() {
        return emptyProductPriceCatalogs()
                .zip(lists().of(productPricesWithoutSpecials()).ofSizes(integers().between(1, 500)), (catalog, productPriceList) -> {
                    productPriceList.forEach(productPrice -> catalog.add(productPrice._1, productPrice._2));
                    return catalog;
                });
    }

    public static Gen<ProductPriceCatalog> productPriceCatalogsWithSpecials() {
        return emptyProductPriceCatalogs()
                .zip(lists().of(productPricesWithSpecials()).ofSizes(integers().between(1, 500)), (catalog, productPriceList) -> {
                    productPriceList.forEach(productPrice -> catalog.add(productPrice._1, productPrice._2));
                    return catalog;
                });
    }

    private static Gen<ProductPriceCatalog> emptyProductPriceCatalogs() {
        return locales().map(ProductPriceCatalog::new);
    }

    private static Gen<Pair<Product, Price>> productPricesWithoutSpecials() {
        return ProductDSL.products().zip(PriceDSL.prices(), Pair::of);
    }

    private static Gen<Pair<Product, Price>> productPricesWithSpecials() {
        return ProductDSL.products().zip(PriceDSL.pricesWithSpecials(), Pair::of);
    }

}
