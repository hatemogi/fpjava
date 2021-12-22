package fpjava.data;

import java.util.function.Function;

@FunctionalInterface
public interface Applicative<F, A> {
    <B> Applicative<F, B> flatMap(Function<A, Applicative<F, B>> f);
}
