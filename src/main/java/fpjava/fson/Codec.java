package fpjava.fson;

import java.util.Objects;
import java.util.function.Function;

public interface Codec<A> extends Encoder<A>, Decoder<A> {
    static <B> Codec<B> build(Encoder<B> encoder, Decoder<B> decoder) {
        Objects.requireNonNull(encoder);
        Objects.requireNonNull(decoder);

        return new Codec<B>() {

            @Override
            public Fson encode(B value) {
                return encoder.encode(value);
            }

            @Override
            public B decode(Fson fson) {
                return decoder.decode(fson);
            }
        };
    }

    default <B> Codec<B> bimap(Function<A, B> ab, Function<B, A> ba) {
        Objects.requireNonNull(ab);
        Objects.requireNonNull(ba);
        final Function<B, Fson> encoder = ba.andThen(this::encode);
        final Function<Fson, B> decoder = ab.compose(this::decode);

        return new Codec<B>() {
            @Override
            public Fson encode(B valueB) {
                return encoder.apply(valueB);
            }

            @Override
            public B decode(Fson fson) {
                return decoder.apply(fson);
            }
        };
    }
}
