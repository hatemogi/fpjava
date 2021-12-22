package fpjava.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonoidTest {
    @Test
    void basicTest() {
        Monoid<String> m = Monoid.stringMonoid;
        assertEquals("Hello World", m.combine("Hello ", "World"));
    }

}
