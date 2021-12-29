package fpjava.data;

import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EitherTest {
    @Test
    void equalTest() {
        Arbitrary<Integer> ints = Arbitrary.integer();
        Property.def("Either.left(int) == Either.left(int) && Either.right(int) == Either.right(int)")
                .forAll(ints)
                .suchThat(i -> {
                    Either<Integer, Void> l1 = Either.left(i);
                    Either<Integer, Void> l2 = Either.left(i);
                    Either<Void, Integer> r1 = Either.right(i);
                    Either<Void, Integer> r2 = Either.right(i);
                    return l1.equals(l2) && r1.equals(r2);
                })
                .check()
                .assertIsSatisfied();
    }

    @Test void swapTest() {
        assertEquals(Either.left(3).swap(), Either.right(3));
        assertEquals(Either.right(3).swap(), Either.left(3));
        assertEquals(Either.right("a").swap().swap(), Either.right("a"));
    }

    @Test void mapTest() {
        Either<String, Integer> l = Either.left("error");
        assertTrue(Either.right(3).map(i -> i + 2).contains(5));
        assertEquals(l, l.map(i -> i + 2));
    }

    @Test void flatMapTest() {
        Either<String, Integer> r3 = Either.right(3);
        Either<String, Integer> l = Either.left("error");
        assertEquals(Either.right(5), r3.flatMap(i -> Either.right(i + 2)));
        assertEquals(Either.left("error"), r3.flatMap(i -> Either.left("error")));
        assertEquals(Either.left("error"), l.flatMap(i -> Either.right(i + 2)));
    }

}
