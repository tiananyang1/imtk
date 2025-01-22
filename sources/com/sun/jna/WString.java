package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/WString.class */
public final class WString implements CharSequence, Comparable {
    private String string;

    public WString(String str) {
        if (str == null) {
            throw new NullPointerException("String initializer must be non-null");
        }
        this.string = str;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return toString().charAt(i);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return toString().compareTo(obj.toString());
    }

    public boolean equals(Object obj) {
        return (obj instanceof WString) && toString().equals(obj.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // java.lang.CharSequence
    public int length() {
        return toString().length();
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return toString().subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.string;
    }
}
