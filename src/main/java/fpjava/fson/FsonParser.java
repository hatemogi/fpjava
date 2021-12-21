package fpjava.fson;

import fpjava.data.Either;

public class FsonParser {
    public Either<Error, Fson> parse(CharSequence input) {
        return Either.left(error("not implemented"));
    }

    private static Error error(String reason) {
        return new Error(reason);
    }

    public static class Error {
        private final String reason;
        private Error(String reason) {
            this.reason = reason;
        }
    }
}
