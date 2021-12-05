package fpjava.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public final class L {
    public static <A> List<A> of(A value) {
        return Collections.singletonList(value);
    }

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

    public static <A> List<A> filter(Predicate<? super A> predicate, List<A> xs) {
        return xs.stream().filter(predicate).collect(toList());
    }

    public static <A> boolean allMatch(Predicate<? super A> predicate, List<A> xs) {
        return xs.stream().allMatch(predicate);
    }

    public static <A> boolean noneMatch(Predicate<? super A> predicate, List<A> xs) {
        return xs.stream().noneMatch(predicate);
    }

    public static <A> List<A> concat(List<A> xs, List<A> ys) {
        List<A> zs = new ArrayList<>(xs);
        zs.addAll(ys);
        return zs;
    }
}
