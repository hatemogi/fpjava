package fpjava;

import java.util.function.Function;

public interface Either<L, R> {

    static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }


    <R2> Either<L, R2> map(Function<R, R2> mapper);

    final class Left<L, R> implements Either<L, R> {
        private final L left;
        Left(L left) {
            this.left = left;
        }

        @Override
        public <R2> Either<L, R2> map(Function<R, R2> mapper) {
            return new Left<>(left);
        }
    }

    final class Right<L, R> implements Either<L, R> {
        private final R right;

        Right(R right) {
            this.right = right;
        }

        @Override
        public <R2> Either<L, R2> map(Function<R, R2> mapper) {
            return new Right<>(mapper.apply(right));
        }
    }

}
