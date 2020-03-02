package com.intware.qt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class InvariantTest {

    @Test
    void test_list_size_should_remain_constant_after_concatenation() {
        qt().forAll(
                lists().of(strings().allPossible().ofLengthBetween(1, 100)).ofSizes(integers().from(0).upTo(50)),
                lists().of(strings().allPossible().ofLengthBetween(1, 100)).ofSizes(integers().from(0).upTo(50))
            ).check((a, b) -> Stream.concat(a.stream(), b.stream()).count() == a.size() + b.size());
    }
}
