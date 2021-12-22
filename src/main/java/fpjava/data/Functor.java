package fpjava.data;

import java.util.function.Function;

@FunctionalInterface
public interface Functor<F, A> {
    <B> Functor<F, B> fmap(Function<A, B> f);
}
