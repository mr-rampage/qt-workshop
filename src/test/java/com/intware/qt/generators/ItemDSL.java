package com.intware.qt.generators;

import ca.intware.qt.Item;
import org.quicktheories.core.Gen;

import static org.quicktheories.generators.SourceDSL.strings;

public final class ItemDSL {
    public static Gen<Item> items() {
        return strings().allPossible().ofLengthBetween(0, 50)
                .map(Item::new);
    }
}
