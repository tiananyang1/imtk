package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/DoubleArrayList.class */
final class DoubleArrayList extends AbstractProtobufList<Double> implements Internal.DoubleList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final DoubleArrayList EMPTY_LIST = new DoubleArrayList();
    private double[] array;
    private int size;

    static {
        EMPTY_LIST.makeImmutable();
    }

    DoubleArrayList() {
        this(new double[10], 0);
    }

    private DoubleArrayList(double[] dArr, int i) {
        this.array = dArr;
        this.size = i;
    }

    private void addDouble(int i, double d) {
        int i2;
        ensureIsMutable();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
        double[] dArr = this.array;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.array, i, dArr2, i + 1, this.size - i);
            this.array = dArr2;
        }
        this.array[i] = d;
        this.size++;
        this.modCount++;
    }

    public static DoubleArrayList emptyList() {
        return EMPTY_LIST;
    }

    private void ensureIndexInRange(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    @Override // java.util.List
    public void add(int i, Double d) {
        addDouble(i, d.doubleValue());
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends Double> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof DoubleArrayList)) {
            return super.addAll(collection);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) collection;
        int i = doubleArrayList.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        double[] dArr = this.array;
        if (i3 > dArr.length) {
            this.array = Arrays.copyOf(dArr, i3);
        }
        System.arraycopy(doubleArrayList.array, 0, this.array, this.size, doubleArrayList.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public void addDouble(double d) {
        addDouble(this.size, d);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DoubleArrayList)) {
            return super.equals(obj);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) obj;
        if (this.size != doubleArrayList.size) {
            return false;
        }
        double[] dArr = doubleArrayList.array;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.size) {
                return true;
            }
            if (this.array[i2] != dArr[i2]) {
                return false;
            }
            i = i2 + 1;
        }
    }

    @Override // java.util.List
    public Double get(int i) {
        return Double.valueOf(getDouble(i));
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public double getDouble(int i) {
        ensureIndexInRange(i);
        return this.array[i];
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        int i = 1;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.size) {
                return i;
            }
            i = (i * 31) + Internal.hashLong(Double.doubleToLongBits(this.array[i3]));
            i2 = i3 + 1;
        }
    }

    @Override // com.google.protobuf.Internal.ProtobufList
    /* renamed from: mutableCopyWithCapacity, reason: merged with bridge method [inline-methods] */
    public Internal.ProtobufList<Double> mutableCopyWithCapacity2(int i) {
        if (i >= this.size) {
            return new DoubleArrayList(Arrays.copyOf(this.array, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.List
    public Double remove(int i) {
        ensureIsMutable();
        ensureIndexInRange(i);
        double[] dArr = this.array;
        double d = dArr[i];
        System.arraycopy(dArr, i + 1, dArr, i, this.size - i);
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        ensureIsMutable();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.size) {
                return false;
            }
            if (obj.equals(Double.valueOf(this.array[i2]))) {
                double[] dArr = this.array;
                System.arraycopy(dArr, i2 + 1, dArr, i2, this.size - i2);
                this.size--;
                this.modCount++;
                return true;
            }
            i = i2 + 1;
        }
    }

    @Override // java.util.List
    public Double set(int i, Double d) {
        return Double.valueOf(setDouble(i, d.doubleValue()));
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public double setDouble(int i, double d) {
        ensureIsMutable();
        ensureIndexInRange(i);
        double[] dArr = this.array;
        double d2 = dArr[i];
        dArr[i] = d;
        return d2;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.size;
    }
}
