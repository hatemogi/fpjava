package fpjava.util;

import fpjava.data.Either;

import java.util.concurrent.Callable;
import java.util.function.Function;

@FunctionalInterface
public interface Try<A> {

    static <A> Try<A> of(Callable<A> block) {
        return () -> {
            try {
                return Either.right(block.call());
            } catch (Throwable t) {
                return Either.left(t);
            }
        };
    }

    default <B> Try<B> map(Function<A, B> mapper) {
        return () -> run().map(mapper);
    }

    default <B> Try<B> flatMap(Function<A, Try<B>> mapper) {
        return () -> run().flatMap(a -> mapper.apply(a).run());
    }

    Either<Throwable, A> run();
}

