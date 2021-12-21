package fpjava.chN;

import fpjava.data.Tuple;

import java.util.function.Function;

public interface Writer<L, A> {
    Writer<L, A> pure(A value);
    <B> Writer<L, B> map(Function<A, B> f);
    <B> Writer<L, B> flatMap(Function<A, Writer<L, B>> f);
    Tuple<L, A> run();
}
