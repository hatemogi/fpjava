package fpjava.data;

import java.util.function.Function;

@FunctionalInterface
public interface State<S, A> {
    static <S, A> State<S, A> pure(A value) {
        return state -> Tuple.of(state, value);
    }

    Tuple<S, A> run(S initial);

    default <B> State<S, B> map(Function<A, B> mapper) {
        return state -> {
            Tuple<S, A> n = run(state);
            return Tuple.of(n._1, mapper.apply(n._2));
        };
    }

    default <B> State<S, B> flatMap(State<S, B> another) {
        return state -> {
            Tuple<S, A> n = run(state);
            return another.run(n._1);
        };
    }
}
