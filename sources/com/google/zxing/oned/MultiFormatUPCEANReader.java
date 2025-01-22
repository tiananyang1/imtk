package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/MultiFormatUPCEANReader.class */
public final class MultiFormatUPCEANReader extends OneDReader {
    private final UPCEANReader[] readers;

    public MultiFormatUPCEANReader(Map<DecodeHintType, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new EAN13Reader());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new UPCAReader());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new EAN8Reader());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new UPCEReader());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new EAN13Reader());
            arrayList.add(new EAN8Reader());
            arrayList.add(new UPCEReader());
        }
        this.readers = (UPCEANReader[]) arrayList.toArray(new UPCEANReader[arrayList.size()]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0078, code lost:            if (r9.contains(com.google.zxing.BarcodeFormat.UPC_A) != false) goto L21;     */
    @Override // com.google.zxing.oned.OneDReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.zxing.Result decodeRow(int r8, com.google.zxing.common.BitArray r9, java.util.Map<com.google.zxing.DecodeHintType, ?> r10) throws com.google.zxing.NotFoundException {
        /*
            r7 = this;
            r0 = r9
            int[] r0 = com.google.zxing.oned.UPCEANReader.findStartGuardPattern(r0)
            r15 = r0
            r0 = r7
            com.google.zxing.oned.UPCEANReader[] r0 = r0.readers
            r16 = r0
            r0 = r16
            int r0 = r0.length
            r13 = r0
            r0 = 0
            r12 = r0
            r0 = 0
            r11 = r0
        L17:
            r0 = r11
            r1 = r13
            if (r0 >= r1) goto Lbc
            r0 = r16
            r1 = r11
            r0 = r0[r1]
            r14 = r0
            r0 = r14
            r1 = r8
            r2 = r9
            r3 = r15
            r4 = r10
            com.google.zxing.Result r0 = r0.decodeRow(r1, r2, r3, r4)     // Catch: com.google.zxing.ReaderException -> Lc0
            r14 = r0
            r0 = r14
            com.google.zxing.BarcodeFormat r0 = r0.getBarcodeFormat()
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.EAN_13
            if (r0 != r1) goto L4f
            r0 = r14
            java.lang.String r0 = r0.getText()
            r1 = 0
            char r0 = r0.charAt(r1)
            r1 = 48
            if (r0 != r1) goto L4f
            r0 = 1
            r8 = r0
            goto L51
        L4f:
            r0 = 0
            r8 = r0
        L51:
            r0 = r10
            if (r0 != 0) goto L5a
            r0 = 0
            r9 = r0
            goto L67
        L5a:
            r0 = r10
            com.google.zxing.DecodeHintType r1 = com.google.zxing.DecodeHintType.POSSIBLE_FORMATS
            java.lang.Object r0 = r0.get(r1)
            java.util.Collection r0 = (java.util.Collection) r0
            r9 = r0
        L67:
            r0 = r9
            if (r0 == 0) goto L7b
            r0 = r12
            r11 = r0
            r0 = r9
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.UPC_A
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L7e
        L7b:
            r0 = 1
            r11 = r0
        L7e:
            r0 = r8
            if (r0 == 0) goto Lb0
            r0 = r11
            if (r0 == 0) goto Lb0
            com.google.zxing.Result r0 = new com.google.zxing.Result
            r1 = r0
            r2 = r14
            java.lang.String r2 = r2.getText()
            r3 = 1
            java.lang.String r2 = r2.substring(r3)
            r3 = r14
            byte[] r3 = r3.getRawBytes()
            r4 = r14
            com.google.zxing.ResultPoint[] r4 = r4.getResultPoints()
            com.google.zxing.BarcodeFormat r5 = com.google.zxing.BarcodeFormat.UPC_A
            r1.<init>(r2, r3, r4, r5)
            r9 = r0
            r0 = r9
            r1 = r14
            java.util.Map r1 = r1.getResultMetadata()
            r0.putAllMetadata(r1)
            r0 = r9
            return r0
        Lb0:
            r0 = r14
            return r0
        Lb3:
            r0 = r11
            r1 = 1
            int r0 = r0 + r1
            r11 = r0
            goto L17
        Lbc:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        Lc0:
            r14 = move-exception
            goto Lb3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.MultiFormatUPCEANReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        UPCEANReader[] uPCEANReaderArr = this.readers;
        int length = uPCEANReaderArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            uPCEANReaderArr[i2].reset();
            i = i2 + 1;
        }
    }
}
