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
    void voidTest() {
        State<Integer, String> s1 = State.pure("hello");
        State<Integer, Void> s2 = State.setState(3);
        System.out.println(s2.flatMap(s3 -> tuple(s3, "abc")).run(3));
    }

    @Test
    void setGetState() {
        State<Integer, String> s1 = State.pure("hello");
        State<Integer, String> s2 = s1.flatMap(State.getState()).flatMap(s -> {
            assertEquals(3, s);
            return tuple(s + 1, "world");
        });
        assertEquals(tuple(4, "world"), s2.run(3));
        assertEquals(tuple(2, null), s2.flatMap(State.setState(2)).run(3));
    }

    @Test
    void rngTest() {
        // TODO: immutable RNG는 없나?
        State<Random, Integer> nextInt = r -> tuple(new Random(r.nextInt()), r.nextInt());
        assertEquals(nextInt.run(new Random(456))._2, nextInt.run(new Random(456))._2);
    }
}
