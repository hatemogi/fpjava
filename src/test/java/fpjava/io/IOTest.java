package fpjava.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOTest {
    @Test
    void execute() {
       IO<Integer> ioh = IOUtil.delay(() -> { System.out.println("hello"); return 3; });
       IO<String> iow = IOUtil.pure("world");
       IO<Void> iov = IOUtil.delay(() -> { System.out.println("print"); return null; });
       IO<Integer> twice = ioh.flatMap(a -> ioh).map(n -> n * 3);
       Integer n = twice.unsafeRunSync();
       assertEquals(9, n);
    }
}
