package util;

import java.util.Objects;

class Par<A,B>{
    private final A e1;
    private final B e2;

    public Par(A e1, B e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public A getE1() {
        return e1;
    }

    public B getE2() {
        return e2;
    }

    @Override
    public boolean equals(Object o) {
        Par<A, B> p = (Par<A, B>) o;
        return (this == p) ||
                (p != null &&
                        Objects.equals(e1, p.getE1()) &&
                        Objects.equals(e2, p.getE2()));
    }
    @Override
    public int hashCode() {
        int result = e1 != null ? e1.hashCode() : 0;
        result = 31 * result + (e2 != null ? e2.hashCode() : 0);
        return result;
    }
}
