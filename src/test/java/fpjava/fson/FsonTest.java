package fpjava.fson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FsonTest {
    @Test void fsonTest() {
        assertTrue(Fson.number(123) instanceof Fson.FNumber);
    }
}
