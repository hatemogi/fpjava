package fpjava.data;

public interface HigherKind<F, A> {
    F pure(A value);
}
