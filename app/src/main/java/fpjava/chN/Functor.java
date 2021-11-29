package fpjava.chN;

import java.util.function.Function;

// 자바 인터페이스로는 묶을 방법을 아직 모르겠다.
public interface Functor<T> {
    <R> Functor<R> map(Function<T, R> f);
}
