package fpjava.chN;

import io.vavr.collection.List;
import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.jupiter.api.Test;

public class TupleCheck {
    @Test
    void example() {
        Arbitrary<Integer> ints = Arbitrary.integer();
        // square(int) >= 0: OK, passed 1000 tests.
        Property.def("square(int) >= 0")
                .forAll(ints)
                .suchThat(i -> i * i >= 0)
                .check()
                .assertIsSatisfied();

        Arbitrary<List<Integer>> xs = Arbitrary.list(Arbitrary.integer());
        Property.def("reverse reverse xs == xs")
                .forAll(xs)
                .suchThat(ys -> ys.reverse().reverse().equals(ys))
                .check().assertIsSatisfied();

    }

}
