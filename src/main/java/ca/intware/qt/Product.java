package ca.intware.qt;

import java.util.Objects;

public class Product {
    public final String name;

    public Product(final String name) {
        Objects.requireNonNull(name, "item name cannot be null");

        if (name.equals("")) {
            throw new IllegalArgumentException("name cannot be empty");
        } else {
            this.name = name;
        }
    }

    public Product(final Product product) {
        this.name = product.name;
    }
}
