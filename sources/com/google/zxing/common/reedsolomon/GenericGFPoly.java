package com.google.zxing.common.reedsolomon;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/reedsolomon/GenericGFPoly.class */
public final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly(GenericGF genericGF, int[] iArr) {
        int i;
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = genericGF;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.coefficients = iArr;
            return;
        }
        int i2 = 1;
        while (true) {
            i = i2;
            if (i >= length || iArr[i] != 0) {
                break;
            } else {
                i2 = i + 1;
            }
        }
        if (i == length) {
            this.coefficients = new int[]{0};
            return;
        }
        this.coefficients = new int[length - i];
        int[] iArr2 = this.coefficients;
        System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly addOrSubtract(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero()) {
            return genericGFPoly;
        }
        if (genericGFPoly.isZero()) {
            return this;
        }
        int[] iArr = this.coefficients;
        int[] iArr2 = genericGFPoly.coefficients;
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        if (iArr.length > iArr2.length) {
            iArr3 = iArr2;
            iArr4 = iArr;
        }
        int[] iArr5 = new int[iArr4.length];
        int length = iArr4.length - iArr3.length;
        System.arraycopy(iArr4, 0, iArr5, 0, length);
        int i = length;
        while (true) {
            int i2 = i;
            if (i2 >= iArr4.length) {
                return new GenericGFPoly(this.field, iArr5);
            }
            iArr5[i2] = GenericGF.addOrSubtract(iArr3[i2 - length], iArr4[i2]);
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly[] divide(GenericGFPoly genericGFPoly) {
        GenericGFPoly genericGFPoly2;
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (genericGFPoly.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        GenericGFPoly zero = this.field.getZero();
        int inverse = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
        GenericGFPoly genericGFPoly3 = this;
        while (true) {
            genericGFPoly2 = genericGFPoly3;
            if (genericGFPoly2.getDegree() < genericGFPoly.getDegree() || genericGFPoly2.isZero()) {
                break;
            }
            int degree = genericGFPoly2.getDegree() - genericGFPoly.getDegree();
            int multiply = this.field.multiply(genericGFPoly2.getCoefficient(genericGFPoly2.getDegree()), inverse);
            GenericGFPoly multiplyByMonomial = genericGFPoly.multiplyByMonomial(degree, multiply);
            zero = zero.addOrSubtract(this.field.buildMonomial(degree, multiply));
            genericGFPoly3 = genericGFPoly2.addOrSubtract(multiplyByMonomial);
        }
        return new GenericGFPoly[]{zero, genericGFPoly2};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int evaluateAt(int i) {
        if (i == 0) {
            return getCoefficient(0);
        }
        if (i != 1) {
            int[] iArr = this.coefficients;
            int i2 = iArr[0];
            int length = iArr.length;
            for (int i3 = 1; i3 < length; i3++) {
                i2 = GenericGF.addOrSubtract(this.field.multiply(i, i2), this.coefficients[i3]);
            }
            return i2;
        }
        int[] iArr2 = this.coefficients;
        int length2 = iArr2.length;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= length2) {
                return i4;
            }
            i4 = GenericGF.addOrSubtract(i4, iArr2[i6]);
            i5 = i6 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCoefficient(int i) {
        int[] iArr = this.coefficients;
        return iArr[(iArr.length - 1) - i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] getCoefficients() {
        return this.coefficients;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDegree() {
        return this.coefficients.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isZero() {
        return this.coefficients[0] == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly multiply(int i) {
        if (i == 0) {
            return this.field.getZero();
        }
        if (i == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                return new GenericGFPoly(this.field, iArr);
            }
            iArr[i3] = this.field.multiply(this.coefficients[i3], i);
            i2 = i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly multiply(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero() || genericGFPoly.isZero()) {
            return this.field.getZero();
        }
        int[] iArr = this.coefficients;
        int length = iArr.length;
        int[] iArr2 = genericGFPoly.coefficients;
        int length2 = iArr2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return new GenericGFPoly(this.field, iArr3);
            }
            int i3 = iArr[i2];
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 < length2) {
                    int i6 = i2 + i5;
                    iArr3[i6] = GenericGF.addOrSubtract(iArr3[i6], this.field.multiply(i3, iArr2[i5]));
                    i4 = i5 + 1;
                }
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly multiplyByMonomial(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.field.getZero();
        }
        int length = this.coefficients.length;
        int[] iArr = new int[i + length];
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= length) {
                return new GenericGFPoly(this.field, iArr);
            }
            iArr[i4] = this.field.multiply(this.coefficients[i4], i2);
            i3 = i4 + 1;
        }
    }

    public String toString() {
        int i;
        StringBuilder sb = new StringBuilder(getDegree() * 8);
        int degree = getDegree();
        while (true) {
            int i2 = degree;
            if (i2 < 0) {
                return sb.toString();
            }
            int coefficient = getCoefficient(i2);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    sb.append(" - ");
                    i = -coefficient;
                } else {
                    i = coefficient;
                    if (sb.length() > 0) {
                        sb.append(" + ");
                        i = coefficient;
                    }
                }
                if (i2 == 0 || i != 1) {
                    int log = this.field.log(i);
                    if (log == 0) {
                        sb.append('1');
                    } else if (log == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(log);
                    }
                }
                if (i2 != 0) {
                    if (i2 == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(i2);
                    }
                }
            }
            degree = i2 - 1;
        }
    }
}
