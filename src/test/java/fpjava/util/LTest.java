package fpjava.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

public class LTest {
    @Test void flatMapTest() {
        List<Integer> xs = L.of(1, 2, 3);
        List<Integer> ys = L.flatMap(x -> L.of(x, x * 2), xs);
        assertEquals(L.of(1, 2, 2, 4, 3, 6), ys);
    }

    @Test void streamClosedTest() {
        Stream<Integer> xs = Stream.of(1, 2);
        xs.forEach(x -> {});
        assertThrows(IllegalStateException.class, () -> xs.forEach(x -> {}));
    }
}
