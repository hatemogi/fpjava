package fpjava.data;

import java.util.function.Function;

public interface Writer<L, A> {
    Writer<L, A> pure(A value, Monoid<A> monoid);
    <B> Writer<L, B> map(Function<A, B> f);
    <B> Writer<L, B> flatMap(Function<A, Writer<L, B>> f);
    Tuple<L, A> run();
}
