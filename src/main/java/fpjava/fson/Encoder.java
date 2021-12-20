package fpjava.fson;

import java.util.function.Function;

public interface Encoder<A> {
    Fson encode(A value);

    default <B> Encoder<B> contramap(Function<B, A> mapper) {
        final Function<A, Fson> encoderA = this::encode;
        return valueB -> encoderA.compose(mapper).apply(valueB);
    }
}
