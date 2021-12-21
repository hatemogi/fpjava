package fpjava.data;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static fpjava.data.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

public class StateTest {
    @Test
    void basicTest() {
        State<Integer, Boolean> whateverTrue = State.pure(true);
        assertTrue(whateverTrue.run(456)._2);
        State<Integer, Boolean> plus = index -> tuple(index + 1, (index + 1) % 2 == 0);
        State<Integer, Boolean> combined = State.sequence(plus, plus, plus).map(b -> !b);
        assertEquals(tuple(4, false), combined.run(1));
    }

    @Test
    void rngTest() {
        // TODO: immutable RNG는 없나?
        State<Random, Integer> nextInt = r -> tuple(new Random(r.nextInt()), r.nextInt());
        assertEquals(nextInt.run(new Random(456))._2, nextInt.run(new Random(456))._2);
    }
}
