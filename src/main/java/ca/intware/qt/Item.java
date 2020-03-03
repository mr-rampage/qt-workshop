package ca.intware.qt;

import java.util.Objects;

public class Item {
    public final String name;

    public Item(final String name) {
        Objects.requireNonNull(name, "item name cannot be null");

        if (name.equals("")) {
            throw new IllegalArgumentException("name cannot be empty");
        } else {
            this.name = name;
        }
    }

    public Item(final Item item) {
        this.name = item.name;
    }
}
