package fpjava.fson;

import java.util.function.Function;

@FunctionalInterface
public interface Encoder<A> {
    Fson encode(A value);

    default <B> Encoder<B> contramap(Function<B, A> mapper) {
        final Function<B, Fson> encoder = mapper.andThen(this::encode);
        return encoder::apply;
    }
}
