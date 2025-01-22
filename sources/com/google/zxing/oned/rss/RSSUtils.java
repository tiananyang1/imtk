package com.google.zxing.oned.rss;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/rss/RSSUtils.class */
public final class RSSUtils {
    private RSSUtils() {
    }

    private static int combins(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = i - i2;
        if (i7 > i2) {
            i3 = i2;
        } else {
            i3 = i7;
            i7 = i2;
        }
        int i8 = 1;
        int i9 = 1;
        int i10 = i;
        while (true) {
            int i11 = i10;
            i4 = i9;
            i5 = i8;
            if (i11 <= i7) {
                break;
            }
            int i12 = i8 * i11;
            i8 = i12;
            i9 = i4;
            if (i4 <= i3) {
                i8 = i12 / i4;
                i9 = i4 + 1;
            }
            i10 = i11 - 1;
        }
        for (i6 = i4; i6 <= i3; i6++) {
            i5 /= i6;
        }
        return i5;
    }

    public static int getRSSvalue(int[] iArr, int i, boolean z) {
        int i2;
        int i3;
        int i4 = 0;
        for (int i5 : iArr) {
            i4 += i5;
        }
        int length = iArr.length;
        int i6 = 0;
        int i7 = 0;
        int i8 = i4;
        int i9 = 0;
        while (true) {
            int i10 = length - 1;
            if (i6 >= i10) {
                return i9;
            }
            int i11 = 1 << i6;
            int i12 = i7 | i11;
            int i13 = i9;
            int i14 = 1;
            int i15 = i12;
            while (true) {
                i2 = i15;
                if (i14 < iArr[i6]) {
                    int i16 = i8 - i14;
                    int i17 = length - i6;
                    int i18 = i17 - 2;
                    int combins = combins(i16 - 1, i18);
                    int i19 = combins;
                    if (z) {
                        i19 = combins;
                        if (i2 == 0) {
                            int i20 = i17 - 1;
                            i19 = combins;
                            if (i16 - i20 >= i20) {
                                i19 = combins - combins(i16 - i17, i18);
                            }
                        }
                    }
                    if (i17 - 1 > 1) {
                        int i21 = 0;
                        for (int i22 = i16 - i18; i22 > i; i22--) {
                            i21 += combins((i16 - i22) - 1, i17 - 3);
                        }
                        i3 = i19 - (i21 * (i10 - i6));
                    } else {
                        i3 = i19;
                        if (i16 > i) {
                            i3 = i19 - 1;
                        }
                    }
                    i13 += i3;
                    i14++;
                    i15 = i2 & i11;
                }
            }
            i8 -= i14;
            i6++;
            i9 = i13;
            i7 = i2;
        }
    }
}
