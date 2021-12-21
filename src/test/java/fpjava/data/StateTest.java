package fpjava.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateTest {
    @Test
    void equalTest() {
        State<Integer, Boolean> plus = State.of(index -> index + 1, index -> index % 2 == 0);
        State<Integer, Boolean> combined = plus.flatMap(plus).flatMap(plus).map(b -> !b);
        assertEquals(Tuple.of(4, false), combined.run(1));
    }

}
