package fpjava.data;

@FunctionalInterface
public interface Semigroup<A> {
    A combine(A a1, A a2);

    Semigroup<String> stringSemigroup = (s1, s2) -> s1 + s2;
}
