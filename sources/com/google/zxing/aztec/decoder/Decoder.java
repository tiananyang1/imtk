package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/aztec/decoder/Decoder.class */
public final class Decoder {
    private AztecDetectorResult ddata;
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, "`", "|", Constants.WAVE_SEPARATOR, "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", Constants.ACCEPT_TIME_SEPARATOR_SP, Constants.ACCEPT_TIME_SEPARATOR_SERVER, ".", "/", Constants.COLON_SEPARATOR, ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", Constants.ACCEPT_TIME_SEPARATOR_SP, ".", "CTRL_UL", "CTRL_US"};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1 */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/aztec/decoder/Decoder$1.class */
    public static /* synthetic */ class C00821 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = new int[Table.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:29:0x004d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.google.zxing.aztec.decoder.Decoder$Table[] r0 = com.google.zxing.aztec.decoder.Decoder.Table.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.zxing.aztec.decoder.Decoder.C00821.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = r0
                int[] r0 = com.google.zxing.aztec.decoder.Decoder.C00821.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch: java.lang.NoSuchFieldError -> L41
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.UPPER     // Catch: java.lang.NoSuchFieldError -> L41
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L41
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L41
            L14:
                int[] r0 = com.google.zxing.aztec.decoder.Decoder.C00821.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch: java.lang.NoSuchFieldError -> L41 java.lang.NoSuchFieldError -> L45
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.LOWER     // Catch: java.lang.NoSuchFieldError -> L45
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L45
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L45
            L1f:
                int[] r0 = com.google.zxing.aztec.decoder.Decoder.C00821.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch: java.lang.NoSuchFieldError -> L45 java.lang.NoSuchFieldError -> L49
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.MIXED     // Catch: java.lang.NoSuchFieldError -> L49
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L49
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L49
            L2a:
                int[] r0 = com.google.zxing.aztec.decoder.Decoder.C00821.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch: java.lang.NoSuchFieldError -> L49 java.lang.NoSuchFieldError -> L4d
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.PUNCT     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L35:
                int[] r0 = com.google.zxing.aztec.decoder.Decoder.C00821.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.DIGIT     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
                return
            L41:
                r4 = move-exception
                goto L14
            L45:
                r4 = move-exception
                goto L1f
            L49:
                r4 = move-exception
                goto L2a
            L4d:
                r4 = move-exception
                goto L35
            L51:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.C00821.m3484clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/aztec/decoder/Decoder$Table.class */
    public enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        byte[] bArr = new byte[(zArr.length + 7) / 8];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr.length) {
                return bArr;
            }
            bArr[i2] = readByte(zArr, i2 << 3);
            i = i2 + 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00f1, code lost:            throw com.google.zxing.FormatException.getFormatInstance();     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean[] correctBits(boolean[] r7) throws com.google.zxing.FormatException {
        /*
            Method dump skipped, instructions count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.correctBits(boolean[]):boolean[]");
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i = (isCompact ? 11 : 14) + (nbLayers << 2);
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        if (!isCompact) {
            int i2 = i / 2;
            int i3 = ((i + 1) + (((i2 - 1) / 15) * 2)) / 2;
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 >= i2) {
                    break;
                }
                int i6 = (i5 / 15) + i5;
                iArr[(i2 - i5) - 1] = (i3 - i6) - 1;
                iArr[i2 + i5] = i6 + i3 + 1;
                i4 = i5 + 1;
            }
        } else {
            int i7 = 0;
            while (true) {
                int i8 = i7;
                if (i8 >= iArr.length) {
                    break;
                }
                iArr[i8] = i8;
                i7 = i8 + 1;
            }
        }
        int i9 = 0;
        for (int i10 = 0; i10 < nbLayers; i10++) {
            int i11 = ((nbLayers - i10) << 2) + (isCompact ? 9 : 12);
            int i12 = i10 << 1;
            int i13 = (i - 1) - i12;
            int i14 = 0;
            while (true) {
                int i15 = i14;
                if (i15 < i11) {
                    int i16 = i15 << 1;
                    int i17 = 0;
                    while (true) {
                        int i18 = i17;
                        if (i18 < 2) {
                            int i19 = i12 + i18;
                            int i20 = iArr[i19];
                            int i21 = i12 + i15;
                            zArr[i9 + i16 + i18] = bitMatrix.get(i20, iArr[i21]);
                            int i22 = iArr[i21];
                            int i23 = i13 - i18;
                            zArr[(i11 * 2) + i9 + i16 + i18] = bitMatrix.get(i22, iArr[i23]);
                            int i24 = iArr[i23];
                            int i25 = i13 - i15;
                            zArr[(i11 * 4) + i9 + i16 + i18] = bitMatrix.get(i24, iArr[i25]);
                            zArr[(i11 * 6) + i9 + i16 + i18] = bitMatrix.get(iArr[i25], iArr[i19]);
                            i17 = i18 + 1;
                        }
                    }
                    i14 = i15 + 1;
                }
            }
            i9 += i11 << 3;
        }
        return zArr;
    }

    private static String getCharacter(Table table, int i) {
        int i2 = C00821.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (i2 == 1) {
            return UPPER_TABLE[i];
        }
        if (i2 == 2) {
            return LOWER_TABLE[i];
        }
        if (i2 == 3) {
            return MIXED_TABLE[i];
        }
        if (i2 == 4) {
            return PUNCT_TABLE[i];
        }
        if (i2 == 5) {
            return DIGIT_TABLE[i];
        }
        throw new IllegalStateException("Bad table");
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        int i = 0;
        while (i < length) {
            if (table2 != Table.BINARY) {
                int i2 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int readCode = readCode(zArr, i, i2);
                i += i2;
                String character = getCharacter(table2, readCode);
                if (character.startsWith("CTRL_")) {
                    table = getTable(character.charAt(5));
                    if (character.charAt(6) != 'L') {
                        Table table3 = table2;
                        table2 = table;
                        table = table3;
                    }
                } else {
                    sb.append(character);
                }
                table2 = table;
            } else {
                if (length - i < 5) {
                    break;
                }
                int readCode2 = readCode(zArr, i, 5);
                int i3 = i + 5;
                i = i3;
                int i4 = readCode2;
                if (readCode2 == 0) {
                    if (length - i3 < 11) {
                        break;
                    }
                    i4 = readCode(zArr, i3, 11) + 31;
                    i = i3 + 11;
                }
                int i5 = 0;
                while (true) {
                    int i6 = i5;
                    if (i6 >= i4) {
                        break;
                    }
                    if (length - i < 8) {
                        i = length;
                        break;
                    }
                    sb.append((char) readCode(zArr, i, 8));
                    i += 8;
                    i5 = i6 + 1;
                }
                table2 = table;
            }
        }
        return sb.toString();
    }

    private static Table getTable(char c) {
        return c != 'B' ? c != 'D' ? c != 'P' ? c != 'L' ? c != 'M' ? Table.UPPER : Table.MIXED : Table.LOWER : Table.PUNCT : Table.DIGIT : Table.BINARY;
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static byte readByte(boolean[] zArr, int i) {
        int length = zArr.length - i;
        return (byte) (length >= 8 ? readCode(zArr, i, 8) : readCode(zArr, i, length) << (8 - length));
    }

    private static int readCode(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            int i5 = i3 << 1;
            i3 = i5;
            if (zArr[i4]) {
                i3 = i5 | 1;
            }
        }
        return i3;
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        boolean[] correctBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits), getEncodedData(correctBits), null, null);
        decoderResult.setNumBits(correctBits.length);
        return decoderResult;
    }
}
