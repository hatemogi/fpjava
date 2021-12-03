package fpjava.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOTest {
    @Test
    void execute() {
       IO<Integer> io = SimpleIO.delay(() -> { System.out.println("hello"); return 3; });
       IO<Integer> twice = io.flatMap(a -> io).map(n -> n * 3);
       Integer n = twice.unsafeRunSync();
       assertEquals(9, n);
    }
}
