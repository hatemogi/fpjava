package fpjava.ch1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class Lambda {
    static List<Integer> triple(List<Integer> xs) {
        return xs.stream().map(x -> x * 3).collect(toList());
    }

    static <T, R> List<R> map(Function<? super T, ? extends R> f, List<T> xs) {
        return xs.stream().map(f).collect(toList());
    }

    static <T, R> Function<List<T>, List<R>> map2(Function<? super T, ? extends R> f) {
        return xs -> xs.stream().map(f).collect(toList());
    }

    static Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static void p(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        List<Integer> xs = Arrays.asList(1, 2, 3);

        p(triple(xs));
        p(map(n -> n * 2, xs));
        p(parseInt("a"));
        p(parseInt("456"));
    }
}
