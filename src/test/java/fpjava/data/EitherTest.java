package fpjava.data;

import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.jupiter.api.Test;

public class EitherTest {
    @Test
    void equalTest() {
        Arbitrary<Integer> ints = Arbitrary.integer();
        Property.def("Either.left(int) == Either.left(int)")
                .forAll(ints)
                .suchThat(i -> {
                    Either<Integer, Void> l1 = Either.left(i);
                    Either<Integer, Void> l2 = Either.left(i);
                    return l1.equals(l2);
                })
                .check()
                .assertIsSatisfied();
    }

}
