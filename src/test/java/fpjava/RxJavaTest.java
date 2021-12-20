package fpjava;

import io.reactivex.rxjava3.core.Flowable;
import org.junit.jupiter.api.Test;

public class RxJavaTest {
    @Test
    void example1() {
        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0)
                ;
        flow.subscribe(System.out::println);
    }
}
