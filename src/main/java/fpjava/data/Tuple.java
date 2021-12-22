package fpjava.data;

import java.util.Objects;
import java.util.function.Function;

/**
 * 임의 타입 두개의 값을 담는 튜플.
 * io.vavr.Tuple2를 참고해서 축소 구현
 * @param <T1> 첫번째 값
 * @param <T2> 두번째 값
 */
public final class Tuple<T1, T2> {
    public final T1 _1;
    public final T2 _2;

    private Tuple(T1 t1, T2 t2) {
        this._1 = t1;
        this._2 = t2;
    }

    public static <T1, T2> Tuple<T1, T2> tuple(T1 t1, T2 t2) {
        return new Tuple<>(t1, t2);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Tuple)) {
            return false;
        } else {
            final Tuple<?, ?> that = (Tuple<?, ?>)o;
            return Objects.equals(_1, that._1) && Objects.equals(_2, that._2);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(_1, _2);
    }

    @Override
    public String toString() {
        return "Tuple(" + _1 + ", " + _2 + ")";
    }

    /**
     * 첫번째 값을 바꾸기.
     */
    public <U1> Tuple<U1, T2> map1(Function<T1, U1> mapper) {
        return tuple(mapper.apply(_1), _2);
    }

    /**
     * 두번째 값을 바꾸기.
     */
    public <U2> Tuple<T1, U2> map2(Function<T2, U2> mapper) {
        return tuple(_1, mapper.apply(_2));
    }

    /**
     * 두 값을 한 번에 바꾸기.
     */
    public <U1, U2> Tuple<U1, U2> map(Function<T1, U1> map1,
                                      Function<T2, U2> map2) {
        return tuple(map1.apply(_1), map2.apply(_2));
    }

    /**
     * 두 값을 교환하기.
     */
    public Tuple<T2, T1> swap() {
        return tuple(_2, _1);
    }
}
