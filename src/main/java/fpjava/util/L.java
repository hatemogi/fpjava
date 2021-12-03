package fpjava.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

public final class L {
    @SafeVarargs
    public static <A> List<A> of(A... values) {
        return Arrays.asList(values);
    }

    public static <A, B> List<B> map(Function<? super A, ? extends B> mapper, List<A> xs) {
        return xs.stream().map(mapper).collect(toList());
    }

    public static <A, B> List<B> flatMap(Function<? super A, ? extends List<? extends B>> mapper, List<A> xs) {
        return xs.stream().flatMap(mapper.andThen(List::stream)).collect(toList());
    }
}
