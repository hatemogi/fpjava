package fpjava.ch1;

import static fpjava.chN.Functors.fmap;

import java.util.concurrent.Callable;
import java.util.function.Function;

public final class Try<T> {
    private final Callable<T> block;

    private Try(Callable<T> block) {
        this.block = block;
    }

    public static <T> Try<T> of(Callable<T> block) {
        return new Try(block);
    }

    public <R> Try<R> map(Function<? super T, ? extends R> mapper) {
        return of(fmap(block, mapper));
    }
}

