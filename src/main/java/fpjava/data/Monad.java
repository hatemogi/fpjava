package fpjava.data;

import java.util.function.Function;

public interface Monad<F, A> {
    Monad<F, A> pure(A value);
    <B> Monad<F, B> flatMap(Function<A, Monad<F, B>> f);
}

