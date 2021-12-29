package fpjava.util;

import fpjava.data.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TryTest {
    @Test
    void basicTest() {
        Try<String> block = Try.of(() -> "ok");
        assertEquals(Either.right("ok"), block.run());
        assertTrue(Try.of(() -> { throw new Exception("error"); }).run().isLeft());
    }
}
