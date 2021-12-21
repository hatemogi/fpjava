package fpjava.data;

import org.junit.jupiter.api.Test;

import static fpjava.data.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

public class TupleTest {
    @Test void equalTest() {
        assertEquals(tuple("a", 2), tuple("a", 2));
        assertNotEquals(tuple(1, 2), tuple(2, 1));
    }

    @Test void map() {
        Tuple<String, Integer> t = tuple("Hello", 456);
        assertEquals(t.map1(s -> "World"), tuple("World", 456));
    }

    @Test void swap() {
        assertEquals(tuple(2, "hello").swap(), tuple("hello", 2));
    }
}
