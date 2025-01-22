package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/decoder/DecodedBitStreamParser.class */
final class DecodedBitStreamParser {

    /* renamed from: AL */
    private static final int f21AL = 28;

    /* renamed from: AS */
    private static final int f22AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;

    /* renamed from: LL */
    private static final int f23LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;

    /* renamed from: ML */
    private static final int f24ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;

    /* renamed from: PL */
    private static final int f25PL = 25;

    /* renamed from: PS */
    private static final int f26PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1 */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/decoder/DecodedBitStreamParser$1.class */
    public static /* synthetic */ class C00891 {

        /* renamed from: $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f27x45bba1d = new int[Mode.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:36:0x005d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.f27x45bba1d = r0
                int[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.f27x45bba1d     // Catch: java.lang.NoSuchFieldError -> L4d
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L14:
                int[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.f27x45bba1d     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
            L1f:
                int[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.f27x45bba1d     // Catch: java.lang.NoSuchFieldError -> L51 java.lang.NoSuchFieldError -> L55
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED     // Catch: java.lang.NoSuchFieldError -> L55
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L55
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L55
            L2a:
                int[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.f27x45bba1d     // Catch: java.lang.NoSuchFieldError -> L55 java.lang.NoSuchFieldError -> L59
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT     // Catch: java.lang.NoSuchFieldError -> L59
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L59
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L59
            L35:
                int[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.f27x45bba1d     // Catch: java.lang.NoSuchFieldError -> L59 java.lang.NoSuchFieldError -> L5d
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT     // Catch: java.lang.NoSuchFieldError -> L5d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L5d
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L5d
            L40:
                int[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.f27x45bba1d     // Catch: java.lang.NoSuchFieldError -> L5d java.lang.NoSuchFieldError -> L61
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT     // Catch: java.lang.NoSuchFieldError -> L61
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L61
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L61
                return
            L4d:
                r4 = move-exception
                goto L14
            L51:
                r4 = move-exception
                goto L1f
            L55:
                r4 = move-exception
                goto L2a
            L59:
                r4 = move-exception
                goto L35
            L5d:
                r4 = move-exception
                goto L40
            L61:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C00891.m3541clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/decoder/DecodedBitStreamParser$Mode.class */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            int i2 = i;
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i2 >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i2] = bigIntegerArr2[i2 - 1].multiply(valueOf);
            i = i2 + 1;
        }
    }

    private DecodedBitStreamParser() {
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        boolean z;
        int i3;
        int i4;
        int i5;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        if (i == BYTE_COMPACTION_MODE_LATCH) {
            int[] iArr2 = new int[6];
            int i7 = iArr[i2];
            int i8 = i2 + 1;
            boolean z2 = false;
            int i9 = MACRO_PDF417_TERMINATOR;
            loop0: while (true) {
                i3 = 0;
                long j = 0;
                while (i8 < iArr[0] && !z2) {
                    int i10 = i3 + 1;
                    iArr2[i3] = i7;
                    j = (j * 900) + i7;
                    int i11 = i8 + 1;
                    i7 = iArr[i8];
                    if (i7 == TEXT_COMPACTION_MODE_LATCH || i7 == BYTE_COMPACTION_MODE_LATCH || i7 == NUMERIC_COMPACTION_MODE_LATCH || i7 == BYTE_COMPACTION_MODE_LATCH_6 || i7 == 928 || i7 == i6 || i7 == i9) {
                        i8 = i11 - 1;
                        i3 = i10;
                        i9 = MACRO_PDF417_TERMINATOR;
                        i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        z2 = true;
                    } else if (i10 % 5 != 0 || i10 <= 0) {
                        i8 = i11;
                        i3 = i10;
                        i9 = MACRO_PDF417_TERMINATOR;
                        i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    } else {
                        int i12 = 0;
                        while (i12 < 6) {
                            byteArrayOutputStream.write((byte) (j >> ((5 - i12) * 8)));
                            i12++;
                            i9 = MACRO_PDF417_TERMINATOR;
                            i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        }
                        i8 = i11;
                    }
                }
            }
            if (i8 != iArr[0] || i7 >= TEXT_COMPACTION_MODE_LATCH) {
                i4 = i3;
                i5 = 0;
            } else {
                i4 = i3 + 1;
                iArr2[i3] = i7;
                i5 = 0;
            }
            while (true) {
                int i13 = i5;
                i2 = i8;
                if (i13 >= i4) {
                    break;
                }
                byteArrayOutputStream.write((byte) iArr2[i13]);
                i5 = i13 + 1;
            }
        } else if (i == BYTE_COMPACTION_MODE_LATCH_6) {
            int i14 = i2;
            int i15 = 0;
            long j2 = 0;
            boolean z3 = false;
            while (true) {
                boolean z4 = z3;
                if (i14 >= iArr[0] || z4) {
                    break;
                }
                int i16 = i14 + 1;
                int i17 = iArr[i14];
                if (i17 < TEXT_COMPACTION_MODE_LATCH) {
                    i15++;
                    j2 = (j2 * 900) + i17;
                    i14 = i16;
                    z = z4;
                } else if (i17 == TEXT_COMPACTION_MODE_LATCH || i17 == BYTE_COMPACTION_MODE_LATCH || i17 == NUMERIC_COMPACTION_MODE_LATCH || i17 == BYTE_COMPACTION_MODE_LATCH_6 || i17 == 928 || i17 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i17 == MACRO_PDF417_TERMINATOR) {
                    i14 = i16 - 1;
                    z = true;
                } else {
                    i14 = i16;
                    z = z4;
                }
                int i18 = i15;
                long j3 = j2;
                if (i15 % 5 == 0) {
                    i18 = i15;
                    j3 = j2;
                    if (i15 > 0) {
                        int i19 = 0;
                        while (true) {
                            int i20 = i19;
                            if (i20 >= 6) {
                                break;
                            }
                            byteArrayOutputStream.write((byte) (j2 >> ((5 - i20) * 8)));
                            i19 = i20 + 1;
                        }
                        i18 = 0;
                        j3 = 0;
                    }
                }
                i15 = i18;
                j2 = j3;
                z3 = z;
            }
            i2 = i14;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0030. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.zxing.common.DecoderResult decode(int[] r7, java.lang.String r8) throws com.google.zxing.FormatException {
        /*
            Method dump skipped, instructions count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decode(int[], java.lang.String):com.google.zxing.common.DecoderResult");
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            bigInteger = bigInteger.add(EXP900[(i - i3) - 1].multiply(BigInteger.valueOf(iArr[i3])));
            i2 = i3 + 1;
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i2 = 0;
        while (i2 < 2) {
            iArr2[i2] = iArr[i];
            i2++;
            i++;
        }
        pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int textCompaction = textCompaction(iArr, i, sb);
        pDF417ResultMetadata.setFileId(sb.toString());
        if (iArr[textCompaction] != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            int i3 = textCompaction;
            if (iArr[textCompaction] == MACRO_PDF417_TERMINATOR) {
                pDF417ResultMetadata.setLastSegment(true);
                i3 = textCompaction + 1;
            }
            return i3;
        }
        int i4 = textCompaction + 1;
        int[] iArr3 = new int[iArr[0] - i4];
        boolean z = false;
        int i5 = 0;
        while (i4 < iArr[0] && !z) {
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            if (i7 < TEXT_COMPACTION_MODE_LATCH) {
                iArr3[i5] = i7;
                i4 = i6;
                i5++;
            } else {
                if (i7 != MACRO_PDF417_TERMINATOR) {
                    throw FormatException.getFormatInstance();
                }
                pDF417ResultMetadata.setLastSegment(true);
                i4 = i6 + 1;
                z = true;
            }
        }
        pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
        return i4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0022. Please report as an issue. */
    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        char c;
        Mode mode;
        int i2;
        Mode mode2 = Mode.ALPHA;
        Mode mode3 = Mode.ALPHA;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i) {
                return;
            }
            int i5 = iArr[i4];
            switch (C00891.f27x45bba1d[mode2.ordinal()]) {
                case 1:
                    if (i5 >= 26) {
                        if (i5 != 26) {
                            if (i5 == 27) {
                                mode2 = Mode.LOWER;
                            } else if (i5 == 28) {
                                mode2 = Mode.MIXED;
                            } else if (i5 == 29) {
                                mode = Mode.PUNCT_SHIFT;
                                c = 0;
                                Mode mode4 = mode2;
                                mode2 = mode;
                                mode3 = mode4;
                                break;
                            } else if (i5 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i4]);
                            } else if (i5 == TEXT_COMPACTION_MODE_LATCH) {
                                mode2 = Mode.ALPHA;
                            }
                            c = 0;
                            break;
                        }
                        c = ' ';
                        break;
                    } else {
                        i2 = i5 + 65;
                        c = (char) i2;
                        break;
                    }
                case 2:
                    if (i5 >= 26) {
                        if (i5 != 26) {
                            if (i5 != 27) {
                                if (i5 == 28) {
                                    mode2 = Mode.MIXED;
                                } else if (i5 == 29) {
                                    mode = Mode.PUNCT_SHIFT;
                                } else if (i5 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                    sb.append((char) iArr2[i4]);
                                } else if (i5 == TEXT_COMPACTION_MODE_LATCH) {
                                    mode2 = Mode.ALPHA;
                                }
                                c = 0;
                                break;
                            } else {
                                mode = Mode.ALPHA_SHIFT;
                            }
                            c = 0;
                            Mode mode42 = mode2;
                            mode2 = mode;
                            mode3 = mode42;
                            break;
                        }
                        c = ' ';
                        break;
                    } else {
                        i2 = i5 + 97;
                        c = (char) i2;
                        break;
                    }
                case 3:
                    if (i5 >= 25) {
                        if (i5 != 25) {
                            if (i5 != 26) {
                                if (i5 == 27) {
                                    mode2 = Mode.LOWER;
                                } else if (i5 == 28) {
                                    mode2 = Mode.ALPHA;
                                } else if (i5 == 29) {
                                    mode = Mode.PUNCT_SHIFT;
                                    c = 0;
                                    Mode mode422 = mode2;
                                    mode2 = mode;
                                    mode3 = mode422;
                                    break;
                                } else if (i5 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                    sb.append((char) iArr2[i4]);
                                } else if (i5 == TEXT_COMPACTION_MODE_LATCH) {
                                    mode2 = Mode.ALPHA;
                                }
                            }
                            c = ' ';
                            break;
                        } else {
                            mode2 = Mode.PUNCT;
                        }
                        c = 0;
                        break;
                    } else {
                        c = MIXED_CHARS[i5];
                        break;
                    }
                case 4:
                    if (i5 >= 29) {
                        if (i5 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (i5 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            sb.append((char) iArr2[i4]);
                        } else if (i5 == TEXT_COMPACTION_MODE_LATCH) {
                            mode2 = Mode.ALPHA;
                        }
                        c = 0;
                        break;
                    } else {
                        c = PUNCT_CHARS[i5];
                        break;
                    }
                case 5:
                    if (i5 < 26) {
                        c = (char) (i5 + 65);
                        mode2 = mode3;
                        break;
                    } else if (i5 == 26) {
                        mode2 = mode3;
                        c = ' ';
                        break;
                    } else {
                        if (i5 == TEXT_COMPACTION_MODE_LATCH) {
                            mode2 = Mode.ALPHA;
                            c = 0;
                            break;
                        }
                        mode2 = mode3;
                        c = 0;
                    }
                case 6:
                    if (i5 >= 29) {
                        if (i5 == 29) {
                            mode2 = Mode.ALPHA;
                        } else {
                            if (i5 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i4]);
                            } else if (i5 == TEXT_COMPACTION_MODE_LATCH) {
                                mode2 = Mode.ALPHA;
                            }
                            mode2 = mode3;
                        }
                        c = 0;
                        break;
                    } else {
                        c = PUNCT_CHARS[i5];
                        mode2 = mode3;
                        break;
                    }
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
            i3 = i4 + 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0081, code lost:            if (r0 == com.google.zxing.pdf417.decoder.DecodedBitStreamParser.MACRO_PDF417_TERMINATOR) goto L25;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int numericCompaction(int[] r4, int r5, java.lang.StringBuilder r6) throws com.google.zxing.FormatException {
        /*
            Method dump skipped, instructions count: 194
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.numericCompaction(int[], int, java.lang.StringBuilder):int");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x0062. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x007d. Please report as an issue. */
    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i) << 1];
        int[] iArr3 = new int[(iArr[0] - i) << 1];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case TEXT_COMPACTION_MODE_LATCH /* 900 */:
                            iArr2[i2] = TEXT_COMPACTION_MODE_LATCH;
                            i2++;
                            break;
                        case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                        case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                            break;
                        default:
                            switch (i4) {
                            }
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }
}
