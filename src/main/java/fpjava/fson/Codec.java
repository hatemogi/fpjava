package fpjava.fson;

import java.util.function.Function;

public interface Codec<A> extends Encoder<A>, Decoder<A> {
    default <B> Codec<B> bimap(Function<A, B> atob, Function<B, A> btoa) {
        final Codec<A> codecA = this;
        return new Codec<B>() {
            @Override
            public Fson encode(B valueB) {
                return btoa.andThen(codecA::encode).apply(valueB);
            }

            @Override
            public B decode(Fson fson) {
                return atob.compose(codecA::decode).apply(fson);
            }
        };
    }
}
