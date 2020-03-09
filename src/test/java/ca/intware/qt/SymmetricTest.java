package ca.intware.qt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SymmetricTest {
    @Test
    void test_base64_symmetry() {
        qt()
            .forAll(strings().basicLatinAlphabet().ofLengthBetween(0, 1000))
            .check(s -> decode(encode(s)).equals(s));
    }

    private String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    private String decode(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        return new String(decoded);
    }
}
