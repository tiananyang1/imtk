package com.google.protobuf;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/ProtobufArrayList.class */
final class ProtobufArrayList<E> extends AbstractProtobufList<E> {
    private static final ProtobufArrayList<Object> EMPTY_LIST = new ProtobufArrayList<>();
    private final List<E> list;

    static {
        EMPTY_LIST.makeImmutable();
    }

    ProtobufArrayList() {
        this(new ArrayList(10));
    }

    private ProtobufArrayList(List<E> list) {
        this.list = list;
    }

    public static <E> ProtobufArrayList<E> emptyList() {
        return (ProtobufArrayList<E>) EMPTY_LIST;
    }

    public void add(int i, E e) {
        ensureIsMutable();
        this.list.add(i, e);
        this.modCount++;
    }

    public E get(int i) {
        return this.list.get(i);
    }

    public ProtobufArrayList<E> mutableCopyWithCapacity(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.list);
        return new ProtobufArrayList<>(arrayList);
    }

    public E remove(int i) {
        ensureIsMutable();
        E remove = this.list.remove(i);
        this.modCount++;
        return remove;
    }

    public E set(int i, E e) {
        ensureIsMutable();
        E e2 = this.list.set(i, e);
        this.modCount++;
        return e2;
    }

    public int size() {
        return this.list.size();
    }
}
