package fpjava.util;

import fpjava.data.Either;

import static fpjava.chN.Functors.fmap;

import java.util.concurrent.Callable;
import java.util.function.Function;

public final class Try<A> {
    private final Callable<A> block;

    private Try(Callable<A> block) {
        this.block = block;
    }

    public static <A> Try<A> of(Callable<A> block) {
        return new Try(block);
    }

    public <B> Try<B> map(Function<? super A, ? extends B> mapper) {
        return of(fmap(block, mapper));
    }

    public Either<Throwable, A> run() {
        try {
            return Either.right(block.call());
        } catch (Throwable t) {
            return Either.left(t);
        }
    }
}

