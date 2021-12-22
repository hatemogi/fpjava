package fpjava.data;

import java.util.function.Function;

public interface Monad<F, A> {
    Monad<F, A> pure(A value);
    Monad<F, A> flatMap(Function<A, Monad<F, A>> f);
}

