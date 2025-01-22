package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/rss/expanded/BitArrayBuilder.class */
final class BitArrayBuilder {
    private BitArrayBuilder() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitArray buildBitArray(List<ExpandedPair> list) {
        int size = (list.size() << 1) - 1;
        int i = size;
        if (list.get(list.size() - 1).getRightChar() == null) {
            i = size - 1;
        }
        BitArray bitArray = new BitArray(i * 12);
        int value = list.get(0).getRightChar().getValue();
        int i2 = 0;
        for (int i3 = 11; i3 >= 0; i3--) {
            if (((1 << i3) & value) != 0) {
                bitArray.set(i2);
            }
            i2++;
        }
        int i4 = 1;
        while (true) {
            int i5 = i4;
            if (i5 >= list.size()) {
                return bitArray;
            }
            ExpandedPair expandedPair = list.get(i5);
            int value2 = expandedPair.getLeftChar().getValue();
            int i6 = 11;
            while (true) {
                int i7 = i6;
                if (i7 < 0) {
                    break;
                }
                if (((1 << i7) & value2) != 0) {
                    bitArray.set(i2);
                }
                i2++;
                i6 = i7 - 1;
            }
            int i8 = i2;
            if (expandedPair.getRightChar() != null) {
                int value3 = expandedPair.getRightChar().getValue();
                int i9 = 11;
                while (true) {
                    int i10 = i9;
                    i8 = i2;
                    if (i10 >= 0) {
                        if (((1 << i10) & value3) != 0) {
                            bitArray.set(i2);
                        }
                        i2++;
                        i9 = i10 - 1;
                    }
                }
            }
            i2 = i8;
            i4 = i5 + 1;
        }
    }
}
