package com.intware.qt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Idempotent {
    @Test
    void test_casing_idempotent() {
        qt()
            .forAll(strings().allPossible().ofLengthBetween(0, 1000))
            .check(s ->
                    s.toUpperCase().toUpperCase().equals(s.toUpperCase()) &&
                    s.toLowerCase().toLowerCase().equals(s.toLowerCase())
            );
    }
}
