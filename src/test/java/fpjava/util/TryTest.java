package fpjava.util;

import fpjava.data.Either;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TryTest {
    @Test
    void basicTest() {
        Try<String> block = Try.of(() -> "ok");
        assertEquals(Either.right("ok"), block.run());
        assertTrue(Try.of(() -> { throw new Exception("error"); }).run().isLeft());
    }

    private String errorBlock() throws IOException {
        throw new IOException("some IO error");
    }

    private String okBlock() throws IOException {
        return "ok";
    }

    @Test
    void methodExample() {
        Try<String> tError = Try.of(this::errorBlock);
        assertTrue(tError.run().isLeft());
        Try<String> tOk = Try.of(this::okBlock);
        assertTrue(tOk.run().contains("ok"));
        Try<String> tFM = tError.flatMap(a -> tOk);
        assertTrue(tFM.run().isLeft());
    }
}
