package fpjava.data;

import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
