package fpjava.chN;

import java.util.Optional;
import java.util.function.BiFunction;

public class OptionalExample {
    public Optional<Integer> parse(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public Optional<Integer> add(Optional<Integer> oa, Optional<Integer> ob) {
        return lift(Integer::sum).apply(oa, ob);
    }

    public Optional<Integer> add(String sa, String sb) {
        return add(parse(sa), parse(sb));
    }

    public <A, B, C> BiFunction<Optional<A>, Optional<B>, Optional<C>> lift(BiFunction<A, B, C> f) {
        return (oa, ob) -> oa.flatMap(a -> ob.map(b -> f.apply(a, b)));
    }

    public void parseIntExample() {
        add("3", "5");
    }
}
