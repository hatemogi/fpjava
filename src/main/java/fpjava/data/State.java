package fpjava.data;

import java.util.function.Function;

public final class State<S, A> {
    private final Function<S, Tuple<S, A>> next;

    private State(Function<S, Tuple<S, A>> next) {
        this.next = next;
    }

    public static <S, A> State<S, A> of(Function<S, Tuple<S, A>> next) {
        return new State<>(next);
    }

    public static <S, A> State<S, A> pure(A value) {
        return of(state -> Tuple.of(state, value));
    }

    public Tuple<S, A> run(S initial) {
        return next.apply(initial);
    }

    public <B> State<S, B> map(Function<A, B> mapper) {
        return of(state -> {
            Tuple<S, A> n = next.apply(state);
            return Tuple.of(n._1, mapper.apply(n._2));
        });
    }

    public <B> State<S, B> flatMap(State<S, B> another) {
        return of(state -> {
            Tuple<S, A> n = next.apply(state);
            return another.next.apply(n._1);
        });
    }
}
