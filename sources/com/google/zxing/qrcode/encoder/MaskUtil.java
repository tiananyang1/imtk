package com.google.zxing.qrcode.encoder;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/qrcode/encoder/MaskUtil.class */
final class MaskUtil {

    /* renamed from: N1 */
    private static final int f32N1 = 3;

    /* renamed from: N2 */
    private static final int f33N2 = 3;

    /* renamed from: N3 */
    private static final int f34N3 = 40;

    /* renamed from: N4 */
    private static final int f35N4 = 10;

    private MaskUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        byte b;
        int i;
        int height = z ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int i2 = 0;
        for (int i3 = 0; i3 < height; i3++) {
            int i4 = 0;
            int i5 = 0;
            byte b2 = -1;
            while (true) {
                byte b3 = b2;
                if (i4 >= width) {
                    break;
                }
                byte b4 = z ? array[i3][i4] : array[i4][i3];
                if (b4 == b3) {
                    i = i5 + 1;
                    b = b3;
                } else {
                    int i6 = i2;
                    if (i5 >= 5) {
                        i6 = i2 + (i5 - 5) + 3;
                    }
                    b = b4;
                    i2 = i6;
                    i = 1;
                }
                i4++;
                i5 = i;
                b2 = b;
            }
            int i7 = i2;
            if (i5 >= 5) {
                i7 = i2 + (i5 - 5) + 3;
            }
            i2 = i7;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        for (int i2 = 0; i2 < height - 1; i2++) {
            int i3 = 0;
            while (i3 < width - 1) {
                byte b = array[i2][i3];
                byte[] bArr = array[i2];
                int i4 = i3 + 1;
                int i5 = i;
                if (b == bArr[i4]) {
                    int i6 = i2 + 1;
                    i5 = i;
                    if (b == array[i6][i3]) {
                        i5 = i;
                        if (b == array[i6][i4]) {
                            i5 = i + 1;
                        }
                    }
                }
                i3 = i4;
                i = i5;
            }
        }
        return i * 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a6, code lost:            if (isWhiteHorizontal(r0, r10 + 7, r10 + 11) != false) goto L28;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int applyMaskPenaltyRule3(com.google.zxing.qrcode.encoder.ByteMatrix r6) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MaskUtil.applyMaskPenaltyRule3(com.google.zxing.qrcode.encoder.ByteMatrix):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            byte[] bArr = array[i2];
            int i3 = 0;
            while (i3 < width) {
                int i4 = i;
                if (bArr[i3] == 1) {
                    i4 = i + 1;
                }
                i3++;
                i = i4;
            }
        }
        int height2 = byteMatrix.getHeight() * byteMatrix.getWidth();
        return ((Math.abs((i << 1) - height2) * 10) / height2) * 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0009. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00b5 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean getDataMaskBit(int r4, int r5, int r6) {
        /*
            r0 = r5
            r7 = r0
            r0 = r6
            r8 = r0
            r0 = r6
            r9 = r0
            r0 = r4
            switch(r0) {
                case 0: goto La4;
                case 1: goto Laa;
                case 2: goto L9d;
                case 3: goto L94;
                case 4: goto L88;
                case 5: goto L79;
                case 6: goto L66;
                case 7: goto L57;
                default: goto L38;
            }
        L38:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            java.lang.String r2 = "Invalid mask pattern: "
            r1.<init>(r2)
            r10 = r0
            r0 = r10
            r1 = r4
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            r2 = r10
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L57:
            r0 = r6
            r1 = r5
            int r0 = r0 * r1
            r1 = 3
            int r0 = r0 % r1
            r1 = r6
            r2 = r5
            int r1 = r1 + r2
            r2 = 1
            r1 = r1 & r2
            int r0 = r0 + r1
            r4 = r0
            goto L72
        L66:
            r0 = r6
            r1 = r5
            int r0 = r0 * r1
            r4 = r0
            r0 = r4
            r1 = 1
            r0 = r0 & r1
            r1 = r4
            r2 = 3
            int r1 = r1 % r2
            int r0 = r0 + r1
            r4 = r0
        L72:
            r0 = r4
            r1 = 1
            r0 = r0 & r1
            r4 = r0
            goto Laf
        L79:
            r0 = r6
            r1 = r5
            int r0 = r0 * r1
            r4 = r0
            r0 = r4
            r1 = 1
            r0 = r0 & r1
            r1 = r4
            r2 = 3
            int r1 = r1 % r2
            int r0 = r0 + r1
            r4 = r0
            goto Laf
        L88:
            r0 = r6
            r1 = 2
            int r0 = r0 / r1
            r8 = r0
            r0 = r5
            r1 = 3
            int r0 = r0 / r1
            r7 = r0
            goto La4
        L94:
            r0 = r6
            r1 = r5
            int r0 = r0 + r1
            r1 = 3
            int r0 = r0 % r1
            r4 = r0
            goto Laf
        L9d:
            r0 = r5
            r1 = 3
            int r0 = r0 % r1
            r4 = r0
            goto Laf
        La4:
            r0 = r8
            r1 = r7
            int r0 = r0 + r1
            r9 = r0
        Laa:
            r0 = r9
            r1 = 1
            r0 = r0 & r1
            r4 = r0
        Laf:
            r0 = r4
            if (r0 != 0) goto Lb5
            r0 = 1
            return r0
        Lb5:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MaskUtil.getDataMaskBit(int, int, int):boolean");
    }

    private static boolean isWhiteHorizontal(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length);
        for (int max = Math.max(i, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWhiteVertical(byte[][] bArr, int i, int i2, int i3) {
        int min = Math.min(i3, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (bArr[max][i] == 1) {
                return false;
            }
        }
        return true;
    }
}
