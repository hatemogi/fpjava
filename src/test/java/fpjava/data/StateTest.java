package fpjava.data;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {
    @Test
    void basicTest() {
        State<Integer, Boolean> whateverTrue = State.pure(true);
        assertTrue(whateverTrue.run(456)._2);
        State<Integer, Boolean> plus = index -> Tuple.of(index + 1, (index + 1) % 2 == 0);
        State<Integer, Boolean> combined = plus.flatMap(plus).flatMap(plus).map(b -> !b);
        assertEquals(Tuple.of(4, false), combined.run(1));
    }

    @Test
    void rngTest() {
        // TODO: immutable RNG는 없나?
        State<Random, Integer> nextInt = r -> Tuple.of(new Random(r.nextInt()), r.nextInt());
        assertEquals(nextInt.run(new Random(456))._2, nextInt.run(new Random(456))._2);
    }
}
