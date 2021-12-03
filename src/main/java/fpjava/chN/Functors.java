package fpjava.chN;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Function;

public final class Functors {
    private Functors() {}

    public static <T, R> Optional<R> fmap(Optional<T> option, Function<? super T, ? extends R> mapper) {
        return option.map(mapper);
    }

    public static <T, R> CompletableFuture<R> fmap(CompletableFuture<T> future, Function<? super T, ? extends R> mapper) {
        return future.thenApply(mapper);
    }

    public static <T, R> Callable<R> fmap(Callable<T> callable, Function<? super T, ? extends R> mapper) {
        return new CallableFunctor(callable, mapper);
    }

}

class FutureFunctor<T, R> implements Future<R> {
    private final Future<T> future;
    private final Function<? super T, ? extends R> mapper;

    FutureFunctor(Future<T> future, Function<? super T, ? extends R> mapper) {
        this.future = future;
        this.mapper = mapper;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public R get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}

class CallableFunctor<T, R> implements Callable<R> {
    private final Callable<T> source;
    private final Function<? super T, ? extends R> mapper;

    CallableFunctor(Callable<T> source, Function<? super T, ? extends R> mapper) {
        this.source = source;
        this.mapper = mapper;
    }
    @Override
    public R call() throws Exception {
        return mapper.apply(source.call());
    }
}
