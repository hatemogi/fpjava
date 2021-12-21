package fpjava.fson;

import java.util.function.Function;

@FunctionalInterface
public interface Decoder<A> {
    A decode(Fson fson);

    default <B> Decoder<B> map(Function<A, B> mapper) {
        return mapper.compose(this::decode)::apply;
    }

    Decoder<Integer> intDecoder = Fson::intValue;
}
