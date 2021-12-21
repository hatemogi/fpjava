package fpjava.data;

import java.util.function.Function;

public final class State<S, A> {
    private final Function<S, S> next;
    private final Function<S, A> unwrap;

    private State(Function<S, S> next, Function<S, A> unwrap) {
        this.next = next;
        this.unwrap = unwrap;
    }

    public static <S, A> State<S, A> of(Function<S, S> next, Function<S, A> unwrap) {
        return new State<>(next, unwrap);
    }

    public static <S, A> State<S, A> pure(A value) {
        return of(Function.identity(), state -> value);
    }

    public Tuple<S, A> run(S initial) {
        S state = next.apply(initial);
        return Tuple.of(state, unwrap.apply(state));
    }

    public <B> State<S, B> map(Function<A, B> mapper) {
        return of(next, state -> mapper.compose(unwrap).apply(state));
    }

    public <B> State<S, B> flatMap(State<S, B> other) {
        return of(next.andThen(other.next), other.unwrap);
    }
}
