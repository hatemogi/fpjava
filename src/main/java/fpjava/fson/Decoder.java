package fpjava.fson;

import java.util.function.Function;

@FunctionalInterface
public interface Decoder<A> {
    A decode(Fson fson);

    default <B> Decoder<B> map(Function<A, B> mapper) {
        final Function<Fson, B> decoder = mapper.compose(this::decode);
        return decoder::apply;
    }
}
