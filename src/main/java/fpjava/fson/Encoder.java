package fpjava.fson;

import java.util.function.Function;

@FunctionalInterface
public interface Encoder<A> {
    Fson encode(A value);

    default <B> Encoder<B> contramap(Function<B, A> mapper) {
        return mapper.andThen(this::encode)::apply;
    }

    Encoder<Integer> intEncoder = Fson::number;
    Encoder<String> stringEncoder = Fson::string;
}
