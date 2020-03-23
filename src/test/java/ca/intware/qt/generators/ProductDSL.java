package ca.intware.qt.generators;

import ca.intware.qt.Product;
import org.quicktheories.core.Gen;

import static org.quicktheories.generators.SourceDSL.strings;

public final class ProductDSL {
    public static Gen<Product> products() {
        return strings().allPossible().ofLengthBetween(1, 50)
                .map(Product::new);
    }
}
