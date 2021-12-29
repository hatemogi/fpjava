package fpjava.fson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FsonTest {
    @Test void fsonTest() {
        assertEquals(123, Fson.number(123).intValue());
        assertEquals("123", Fson.string("123").stringValue());
        assertEquals(true, Fson.bool(true).boolValue());
    }
}
