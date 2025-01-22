package com.meituan.android.walle;

/* loaded from: classes08-dex2jar.jar:com/meituan/android/walle/Pair.class */
final class Pair<A, B> {
    private final A mFirst;
    private final B mSecond;

    private Pair(A a, B b) {
        this.mFirst = a;
        this.mSecond = b;
    }

    /* renamed from: of */
    public static <A, B> Pair<A, B> m9of(A a, B b) {
        return new Pair<>(a, b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair pair = (Pair) obj;
        A a = this.mFirst;
        if (a == null) {
            if (pair.mFirst != null) {
                return false;
            }
        } else if (!a.equals(pair.mFirst)) {
            return false;
        }
        B b = this.mSecond;
        return b == null ? pair.mSecond == null : b.equals(pair.mSecond);
    }

    public A getFirst() {
        return this.mFirst;
    }

    public B getSecond() {
        return this.mSecond;
    }

    public int hashCode() {
        A a = this.mFirst;
        int i = 0;
        int hashCode = a == null ? 0 : a.hashCode();
        B b = this.mSecond;
        if (b != null) {
            i = b.hashCode();
        }
        return ((hashCode + 31) * 31) + i;
    }
}
