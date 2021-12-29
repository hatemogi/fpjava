package fpjava.data;

import java.util.function.Function;

@FunctionalInterface
public interface Functor<F, A> {
    <B> HigherKind<F, B> fmap(HigherKind<F, A> fa, Function<A, B> f);
}
