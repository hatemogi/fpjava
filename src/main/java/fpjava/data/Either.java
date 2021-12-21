package fpjava.data;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public interface Either<L, R> {

    static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }

    <R2> Either<L, R2> map(Function<R, R2> mapper);
    <L2> Either<L2, R> leftMap(Function<L, L2> mapper);
    R get();

    final class Left<L, R> implements Either<L, R> {
        private final L left;
        private Left(L left) {
            this.left = left;
        }

        @Override
        public <R2> Either<L, R2> map(Function<R, R2> mapper) {
            return new Left<>(left);
        }

        @Override
        public <L2> Either<L2, R> leftMap(Function<L, L2> mapper) {
            return new Left<>(mapper.apply(left));
        }

        @Override
        public R get() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Left)) {
                return false;
            } else {
                final Object that = ((Left<?, ?>)o).left;
                return Objects.equals(left, that);
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(left);
        }
    }

    final class Right<L, R> implements Either<L, R> {
        private final R right;

        private Right(R right) {
            this.right = right;
        }

        @Override
        public <R2> Either<L, R2> map(Function<R, R2> mapper) {
            return new Right<>(mapper.apply(right));
        }

        @Override
        public <L2> Either<L2, R> leftMap(Function<L, L2> mapper) {
            return new Right<>(right);
        }

        @Override
        public R get() {
            return right;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Right)) {
                return false;
            } else {
                final Object that = ((Right<?, ?>)o).right;
                return Objects.equals(right, that);
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(right);
        }
    }
}
