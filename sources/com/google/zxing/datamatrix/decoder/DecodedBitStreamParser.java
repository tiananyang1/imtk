package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import com.subgraph.orchid.Cell;
import com.sun.jna.Function;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/datamatrix/decoder/DecodedBitStreamParser.class */
final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS = {'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS = C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    /* renamed from: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$1 */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/datamatrix/decoder/DecodedBitStreamParser$1.class */
    static /* synthetic */ class C00841 {

        /* renamed from: $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f13xb73eb560 = new int[Mode.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:29:0x004d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode[] r0 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.C00841.f13xb73eb560 = r0
                int[] r0 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.C00841.f13xb73eb560     // Catch: java.lang.NoSuchFieldError -> L41
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.C40_ENCODE     // Catch: java.lang.NoSuchFieldError -> L41
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L41
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L41
            L14:
                int[] r0 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.C00841.f13xb73eb560     // Catch: java.lang.NoSuchFieldError -> L41 java.lang.NoSuchFieldError -> L45
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.TEXT_ENCODE     // Catch: java.lang.NoSuchFieldError -> L45
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L45
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L45
            L1f:
                int[] r0 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.C00841.f13xb73eb560     // Catch: java.lang.NoSuchFieldError -> L45 java.lang.NoSuchFieldError -> L49
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ANSIX12_ENCODE     // Catch: java.lang.NoSuchFieldError -> L49
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L49
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L49
            L2a:
                int[] r0 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.C00841.f13xb73eb560     // Catch: java.lang.NoSuchFieldError -> L49 java.lang.NoSuchFieldError -> L4d
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.EDIFACT_ENCODE     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L35:
                int[] r0 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.C00841.f13xb73eb560     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.BASE256_ENCODE     // Catch: java.lang.NoSuchFieldError -> L51
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.C00841.m3510clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/datamatrix/decoder/DecodedBitStreamParser$Mode.class */
    public enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DecoderResult decode(byte[] bArr) throws FormatException {
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        Mode mode = Mode.ASCII_ENCODE;
        do {
            if (mode == Mode.ASCII_ENCODE) {
                mode = decodeAsciiSegment(bitSource, sb, sb2);
            } else {
                int i = C00841.f13xb73eb560[mode.ordinal()];
                if (i == 1) {
                    decodeC40Segment(bitSource, sb);
                } else if (i == 2) {
                    decodeTextSegment(bitSource, sb);
                } else if (i == 3) {
                    decodeAnsiX12Segment(bitSource, sb);
                } else if (i == 4) {
                    decodeEdifactSegment(bitSource, sb);
                } else {
                    if (i != 5) {
                        throw FormatException.getFormatInstance();
                    }
                    decodeBase256Segment(bitSource, sb, arrayList);
                }
                mode = Mode.ASCII_ENCODE;
            }
            if (mode == Mode.PAD_ENCODE) {
                break;
            }
        } while (bitSource.available() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        String sb3 = sb.toString();
        ArrayList arrayList2 = arrayList;
        if (arrayList.isEmpty()) {
            arrayList2 = null;
        }
        return new DecoderResult(bArr, sb3, arrayList2, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a3, code lost:            if (r4.available() > 0) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a6, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeAnsiX12Segment(com.google.zxing.common.BitSource r4, java.lang.StringBuilder r5) throws com.google.zxing.FormatException {
        /*
            r0 = 3
            int[] r0 = new int[r0]
            r8 = r0
        L5:
            r0 = r4
            int r0 = r0.available()
            r1 = 8
            if (r0 != r1) goto Lf
            return
        Lf:
            r0 = r4
            r1 = 8
            int r0 = r0.readBits(r1)
            r6 = r0
            r0 = r6
            r1 = 254(0xfe, float:3.56E-43)
            if (r0 != r1) goto L1e
            return
        L1e:
            r0 = r6
            r1 = r4
            r2 = 8
            int r1 = r1.readBits(r2)
            r2 = r8
            parseTwoBytes(r0, r1, r2)
            r0 = 0
            r6 = r0
        L2c:
            r0 = r6
            r1 = 3
            if (r0 >= r1) goto L9f
            r0 = r8
            r1 = r6
            r0 = r0[r1]
            r7 = r0
            r0 = r7
            if (r0 != 0) goto L44
            r0 = r5
            r1 = 13
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L94
        L44:
            r0 = r7
            r1 = 1
            if (r0 != r1) goto L53
            r0 = r5
            r1 = 42
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L94
        L53:
            r0 = r7
            r1 = 2
            if (r0 != r1) goto L62
            r0 = r5
            r1 = 62
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L94
        L62:
            r0 = r7
            r1 = 3
            if (r0 != r1) goto L71
            r0 = r5
            r1 = 32
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L94
        L71:
            r0 = r7
            r1 = 14
            if (r0 >= r1) goto L84
            r0 = r5
            r1 = r7
            r2 = 44
            int r1 = r1 + r2
            char r1 = (char) r1
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L94
        L84:
            r0 = r7
            r1 = 40
            if (r0 >= r1) goto L9b
            r0 = r5
            r1 = r7
            r2 = 51
            int r1 = r1 + r2
            char r1 = (char) r1
            java.lang.StringBuilder r0 = r0.append(r1)
        L94:
            r0 = r6
            r1 = 1
            int r0 = r0 + r1
            r6 = r0
            goto L2c
        L9b:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L9f:
            r0 = r4
            int r0 = r0.available()
            if (r0 > 0) goto L5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeAnsiX12Segment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    private static Mode decodeAsciiSegment(BitSource bitSource, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z;
        boolean z2 = false;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            }
            if (readBits <= 128) {
                int i = readBits;
                if (z2) {
                    i = readBits + 128;
                }
                sb.append((char) (i - 1));
                return Mode.ASCII_ENCODE;
            }
            if (readBits == 129) {
                return Mode.PAD_ENCODE;
            }
            if (readBits <= 229) {
                int i2 = readBits - Cell.AUTH_CHALLENGE;
                if (i2 < 10) {
                    sb.append('0');
                }
                sb.append(i2);
                z = z2;
            } else {
                if (readBits == 230) {
                    return Mode.C40_ENCODE;
                }
                if (readBits == 231) {
                    return Mode.BASE256_ENCODE;
                }
                if (readBits == 232) {
                    sb.append((char) 29);
                    z = z2;
                } else {
                    z = z2;
                    if (readBits != 233) {
                        z = z2;
                        if (readBits != 234) {
                            if (readBits == 235) {
                                z = true;
                            } else if (readBits == 236) {
                                sb.append("[)>\u001e05\u001d");
                                sb2.insert(0, "\u001e\u0004");
                                z = z2;
                            } else if (readBits == 237) {
                                sb.append("[)>\u001e06\u001d");
                                sb2.insert(0, "\u001e\u0004");
                                z = z2;
                            } else {
                                if (readBits == 238) {
                                    return Mode.ANSIX12_ENCODE;
                                }
                                if (readBits == 239) {
                                    return Mode.TEXT_ENCODE;
                                }
                                if (readBits == 240) {
                                    return Mode.EDIFACT_ENCODE;
                                }
                                z = z2;
                                if (readBits != 241) {
                                    z = z2;
                                    if (readBits >= 242) {
                                        if (readBits != 254 || bitSource.available() != 0) {
                                            throw FormatException.getFormatInstance();
                                        }
                                        z = z2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            z2 = z;
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int byteOffset = bitSource.getByteOffset() + 1;
        int i = byteOffset + 1;
        int unrandomize255State = unrandomize255State(bitSource.readBits(8), byteOffset);
        if (unrandomize255State == 0) {
            unrandomize255State = bitSource.available() / 8;
        } else if (unrandomize255State >= 250) {
            unrandomize255State = ((unrandomize255State - 249) * 250) + unrandomize255State(bitSource.readBits(8), i);
            i++;
        }
        if (unrandomize255State < 0) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[unrandomize255State];
        int i2 = 0;
        while (i2 < unrandomize255State) {
            if (bitSource.available() < 8) {
                throw FormatException.getFormatInstance();
            }
            bArr[i2] = (byte) unrandomize255State(bitSource.readBits(8), i);
            i2++;
            i++;
        }
        collection.add(bArr);
        try {
            sb.append(new String(bArr, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Platform does not support required encoding: " + e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x013d, code lost:            if (r4.available() > 0) goto L68;     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0140, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeC40Segment(com.google.zxing.common.BitSource r4, java.lang.StringBuilder r5) throws com.google.zxing.FormatException {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeC40Segment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:            if (r3.available() > 0) goto L24;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005b, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeEdifactSegment(com.google.zxing.common.BitSource r3, java.lang.StringBuilder r4) {
        /*
        L0:
            r0 = r3
            int r0 = r0.available()
            r1 = 16
            if (r0 > r1) goto La
            return
        La:
            r0 = 0
            r5 = r0
        Lc:
            r0 = r5
            r1 = 4
            if (r0 >= r1) goto L54
            r0 = r3
            r1 = 6
            int r0 = r0.readBits(r1)
            r7 = r0
            r0 = r7
            r1 = 31
            if (r0 != r1) goto L35
            r0 = 8
            r1 = r3
            int r1 = r1.getBitOffset()
            int r0 = r0 - r1
            r5 = r0
            r0 = r5
            r1 = 8
            if (r0 == r1) goto L34
            r0 = r3
            r1 = r5
            int r0 = r0.readBits(r1)
        L34:
            return
        L35:
            r0 = r7
            r6 = r0
            r0 = r7
            r1 = 32
            r0 = r0 & r1
            if (r0 != 0) goto L46
            r0 = r7
            r1 = 64
            r0 = r0 | r1
            r6 = r0
        L46:
            r0 = r4
            r1 = r6
            char r1 = (char) r1
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = 1
            int r0 = r0 + r1
            r5 = r0
            goto Lc
        L54:
            r0 = r3
            int r0 = r0.available()
            if (r0 > 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeEdifactSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x014e, code lost:            if (r4.available() > 0) goto L73;     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0151, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeTextSegment(com.google.zxing.common.BitSource r4, java.lang.StringBuilder r5) throws com.google.zxing.FormatException {
        /*
            Method dump skipped, instructions count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeTextSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static int unrandomize255State(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + Function.MAX_NARGS;
    }
}
