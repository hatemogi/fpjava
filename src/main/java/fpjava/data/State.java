package fpjava.data;

import java.util.function.Function;

import static fpjava.data.Tuple.tuple;

@FunctionalInterface
public interface State<S, A> {
    Tuple<S, A> run(S initial);

    default <B> State<S, B> map(Function<A, B> mapper) {
        return state -> run(state).map2(mapper);
    }

    default <B> State<S, B> flatMap(State<S, B> next) {
        return state -> next.run(run(state)._1);
    }

    static <S, A> State<S, A> pure(A value) {
        return state -> tuple(state, value);
    }

    static <S, A, B> State<S, B> sequence(State<S, A> s1, State<S, B> s2) {
        return s1.flatMap(s2);
    }

    static <S, A, B, C> State<S, C> sequence(State<S, A> s1, State<S, B> s2, State<S, C> s3) {
        return s1.flatMap(s2).flatMap(s3);
    }
}
