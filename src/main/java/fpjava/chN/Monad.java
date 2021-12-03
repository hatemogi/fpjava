package fpjava.chN;

import java.util.function.Function;

// 자바 인터페이스로 묶을 방법이 떠오르지 않는다.
public interface Monad<T> {
    Monad<T> pure(T value);
    <R> Monad<R> flatMap(Function<T, Monad<R>> f);
}
