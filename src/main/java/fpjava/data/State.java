package fpjava.data;

import java.util.function.Function;

import static fpjava.data.Tuple.tuple;

@FunctionalInterface
public interface State<S, A> {
    Tuple<S, A> run(S initial);

    default <B> State<S, B> map(Function<A, B> mapper) {
        return state -> run(state).map2(mapper);
    }

    // TODO: 확인. 보통은 flatMap :: S -> State<S, B> -> State<S, B>
    default <B> State<S, B> flatMap(State<S, B> next) {
        return state -> next.run(run(state)._1);
    }

    static <S> State<S, Void> setState(S newState) {
        return state -> tuple(newState, null);
    }

    static <S> State<S, S> getState() {
        return state -> tuple(state, state);
    }

    static <S, A> State<S, A> pure(A value) {
        return state -> tuple(state, value);
    }

    static <S, A, B> State<S, B> sequence(State<S, A> s1, State<S, B> s2) {
        return s1.flatMap(s2);
    }

    static <S, A, B, C> State<S, C> sequence(State<S, A> s1, State<S, B> s2, State<S, C> s3) {
        return sequence(s1, s2).flatMap(s3);
    }

    static <S, A, B, C, D> State<S, D> sequence(State<S, A> s1, State<S, B> s2, State<S, C> s3, State<S, D> s4) {
        return sequence(s1, s2, s3).flatMap(s4);
    }
}
