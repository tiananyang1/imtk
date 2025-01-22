package com.nimbusds.jose;

import com.nimbusds.jose.Algorithm;
import java.util.Collection;
import java.util.LinkedHashSet;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/AlgorithmFamily.class */
class AlgorithmFamily<T extends Algorithm> extends LinkedHashSet<T> {
    private static final long serialVersionUID = 1;

    public AlgorithmFamily(T... tArr) {
        int length = tArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            super.add((AlgorithmFamily<T>) tArr[i2]);
            i = i2 + 1;
        }
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
}
