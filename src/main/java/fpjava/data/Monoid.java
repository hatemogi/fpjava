package fpjava.data;

public interface Monoid<A> extends Semigroup<A> {
    A id();

    static <A> Monoid<A> monoid(A identity, Semigroup<A> semigroup) {
        return new Monoid<A>() {
            @Override
            public A id() {
                return identity;
            }

            @Override
            public A combine(A a1, A a2) {
                return semigroup.combine(a1, a2);
            }
        };
    }

    Monoid<String> stringMonoid = monoid("", stringSemigroup);
}
