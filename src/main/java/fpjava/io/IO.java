package fpjava.io;

import java.util.function.Function;
import java.util.function.Supplier;

public interface IO<A> {
    <B> IO<B> flatMap(Function<? super A, ? extends IO<? extends B>> mapper);
    A unsafeRunSync();

    default <B> IO<B> map(Function<? super A, ? extends B> mapper) {
        return flatMap(mapper.andThen(IOUtil::pure));
    }

    default <B> IO<B> as(B value) {
        return map(v -> value);
    }
}

final class IOUtil {
    public static <A> IO<A> delay(Supplier<A> supplier) {
        return SimpleIO.delay(supplier);
    }

    public static <A> IO<A> pure(A value) {
        return SimpleIO.pure(value);
    }

    public static <A> IO<A> flatten(IO<? extends IO<A>> wrapped) {
        return new IO<A>() {

            @Override
            public <B> IO<B> flatMap(Function<? super A, ? extends IO<? extends B>> mapper) {
                return flatten(wrapped).flatMap(mapper);
            }

            @Override
            public A unsafeRunSync() {
                return wrapped.unsafeRunSync().unsafeRunSync();
            }
        };
    }
}

class SimpleIO<A> implements IO<A> {
    private final Supplier<A> supplier;

    private SimpleIO(Supplier<A> supplier) {
        this.supplier = supplier;
    }

    static <A> IO<A> pure(A value) {
        return new SimpleIO<>(() -> value);
    }

    static <A> IO<A> delay(Supplier<A> supplier) {
        return new SimpleIO<A>(supplier);
    }

    @Override
    public <B> IO<B> flatMap(Function<? super A, ? extends IO<? extends B>> mapper) {
        return new IO<B>() {
            @Override
            public <C> IO<C> flatMap(Function<? super B, ? extends IO<? extends C>> m) {
                return IOUtil.flatten(delay(() -> mapper.apply(supplier.get()).flatMap(m)));
            }

            @Override
            public B unsafeRunSync() {
                return mapper.apply(supplier.get()).unsafeRunSync();
            }
        };
    }

    @Override
    public A unsafeRunSync() {
        return supplier.get();
    }
}