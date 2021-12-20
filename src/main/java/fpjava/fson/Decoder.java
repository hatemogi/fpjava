package fpjava.fson;

import java.util.function.Function;

public interface Decoder<A> {
    A decode(Fson fson);

    default <B> Decoder<B> map(Function<A, B> mapper) {
        final Function<Fson, A> decoderA = this::decode;
        return fson -> mapper.compose(decoderA).apply(fson);
    }
}
