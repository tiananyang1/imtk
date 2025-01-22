package im.imkey.imkeylibrary.utils;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/utils/TLVUtil.class */
public class TLVUtil {
    protected byte[] data;
    short dataSize;

    public TLVUtil(byte[] bArr) {
        short verifyFormat = verifyFormat(bArr, (short) 0, (short) bArr.length);
        this.data = bArr;
        this.dataSize = verifyFormat;
    }

    public TLVUtil(byte[] bArr, short s) {
        this.data = bArr;
        this.dataSize = s;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TLVUtil(byte[] bArr, short s, short s2) throws Exception {
        int verifyFormat = verifyFormat(bArr, s, s2);
        if (verifyFormat < 0) {
            throw new Exception("tlv size error.");
        }
        try {
            byte[] bArr2 = new byte[verifyFormat];
            System.arraycopy(bArr, s, bArr2, 0, verifyFormat);
            this.data = bArr2;
            this.dataSize = verifyFormat;
        } catch (Exception e) {
        }
    }

    public TLVUtil(byte[] bArr, byte[] bArr2) {
        short parseTag = parseTag(bArr, (short) 0, (short) bArr.length);
        byte[] bArr3 = new byte[calcLengthSize(bArr2.length) + parseTag + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, parseTag);
        System.arraycopy(bArr2, 0, bArr3, calcLengthValue(bArr3, parseTag, bArr2.length), bArr2.length);
        this.data = bArr3;
        this.dataSize = (short) bArr3.length;
    }

    public static short calcLengthSize(int i) {
        if (i < 128) {
            return (short) 1;
        }
        if (i < 256) {
            return (short) 2;
        }
        return i < 65536 ? (short) 3 : (short) 4;
    }

    public static short calcLengthValue(byte[] bArr, short s, int i) {
        short s2;
        if (i < 128) {
            s2 = (short) (s + 1);
            bArr[s] = (byte) i;
        } else {
            if (i < 256) {
                short s3 = (short) (s + 1);
                bArr[s] = -127;
                short s4 = (short) (s3 + 1);
                bArr[s3] = (byte) i;
                return s4;
            }
            if (i >= 65536) {
                short s5 = (short) (s + 1);
                bArr[s] = -125;
                short s6 = (short) (s5 + 1);
                bArr[s5] = (byte) ((i >> 16) & 255);
                short s7 = (short) (s6 + 1);
                bArr[s6] = (byte) ((i >> 8) & 255);
                short s8 = (short) (s7 + 1);
                bArr[s7] = (byte) (i & 255);
                return s8;
            }
            short s9 = (short) (s + 1);
            bArr[s] = -126;
            short s10 = (short) (s9 + 1);
            bArr[s9] = (byte) ((i >> 8) & 255);
            s2 = (short) (s10 + 1);
            bArr[s10] = (byte) (i & 255);
        }
        return s2;
    }

    public static short getShort(byte[] bArr, short s) {
        return (short) ((bArr[s + 1] & 255) | ((bArr[s] << 8) & 65280));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int parseLength(byte[] bArr, short s, short s2) {
        short s3;
        if (s2 < 1) {
            return -1;
        }
        short s4 = (short) (s + 1);
        byte b = bArr[s];
        if (b >= 0) {
            return 65536 | (b & 255);
        }
        if (b == -127) {
            if (s2 < 2) {
                return -1;
            }
            return (bArr[s4] & 255) | 131072;
        }
        if (b != -126 || s2 < 3 || (s3 = getShort(bArr, s4)) < 0) {
            return -1;
        }
        return s3 | 196608;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0033, code lost:            if ((r0 - r4) <= r5) goto L22;     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:            return -1;     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:            r0 = (short) (r7 + 1);        r6 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:            if ((r0 - r4) <= r5) goto L18;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004a, code lost:            return -1;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:            return (short) (r6 - r4);     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:            if ((r3[r4] & 31) == 31) goto L8;     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:            if ((r3[r7] & 128) == 0) goto L21;     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:            r0 = (short) (r7 + 1);        r7 = r0;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static short parseTag(byte[] r3, short r4, short r5) {
        /*
            r0 = r5
            r1 = 1
            if (r0 >= r1) goto L7
            r0 = -1
            return r0
        L7:
            r0 = r4
            r1 = 1
            int r0 = r0 + r1
            short r0 = (short) r0
            r7 = r0
            r0 = r7
            r6 = r0
            r0 = r3
            r1 = r4
            r0 = r0[r1]
            r1 = 31
            r0 = r0 & r1
            r1 = 31
            if (r0 != r1) goto L4c
        L1b:
            r0 = r3
            r1 = r7
            r0 = r0[r1]
            r1 = 128(0x80, float:1.8E-43)
            r0 = r0 & r1
            if (r0 == 0) goto L38
            r0 = r7
            r1 = 1
            int r0 = r0 + r1
            short r0 = (short) r0
            r6 = r0
            r0 = r6
            r7 = r0
            r0 = r6
            r1 = r4
            int r0 = r0 - r1
            r1 = r5
            if (r0 <= r1) goto L1b
            r0 = -1
            return r0
        L38:
            r0 = r7
            r1 = 1
            int r0 = r0 + r1
            short r0 = (short) r0
            r7 = r0
            r0 = r7
            r6 = r0
            r0 = r7
            r1 = r4
            int r0 = r0 - r1
            r1 = r5
            if (r0 <= r1) goto L4c
            r0 = -1
            return r0
        L4c:
            r0 = r6
            r1 = r4
            int r0 = r0 - r1
            short r0 = (short) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: im.imkey.imkeylibrary.utils.TLVUtil.parseTag(byte[], short, short):short");
    }

    protected static short verifyFormat(byte[] bArr, short s, short s2) {
        short parseTag = parseTag(bArr, s, s2);
        if (parseTag < 0) {
            return parseTag;
        }
        short s3 = (short) (parseTag + 0);
        short s4 = (short) (s + parseTag);
        short s5 = (short) (s2 - parseTag);
        int parseLength = parseLength(bArr, s4, s5);
        int i = parseLength >> 16;
        short s6 = (short) (s3 + i);
        short s7 = (short) (s5 - i);
        int i2 = parseLength & 65535;
        if (s7 < i2) {
            return (short) -1;
        }
        return (short) (s6 + ((short) i2));
    }

    public short getValue(byte[] bArr, short s) {
        short parseTag = parseTag(this.data, (short) 0, this.dataSize);
        int parseLength = parseLength(this.data, parseTag, (short) (this.dataSize - parseTag));
        short s2 = (short) (65535 & parseLength);
        System.arraycopy(this.data, (short) (parseTag + ((short) (parseLength >> 16))), bArr, s, s2);
        return s2;
    }

    public short size() {
        return this.dataSize;
    }

    public boolean tagEquals(short s) {
        short parseTag = parseTag(this.data, (short) 0, this.dataSize);
        return parseTag == 1 ? s == ((short) (this.data[0] & 255)) : parseTag == 2 && s == getShort(this.data, (short) 0);
    }

    public short toBytes(byte[] bArr, short s) {
        short s2 = this.dataSize;
        System.arraycopy(this.data, 0, bArr, s, s2);
        return (short) (s + s2);
    }
}
