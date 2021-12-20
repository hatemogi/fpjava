package fpjava.chN;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TupleTest {
    @Test void equalTest() {
        assertEquals(Tuple.of("a", 2), Tuple.of("a", 2));
        assertNotEquals(Tuple.of(1, 2), Tuple.of(2, 1));
    }

    @Test void map() {
        Tuple<String, Integer> t = Tuple.of("Hello", 456);
        assertEquals(t.map1(s -> "World"), Tuple.of("World", 456));
    }

    @Test void swap() {
        assertEquals(Tuple.of(2, "hello").swap(), Tuple.of("hello", 2));
    }
}
