package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/qrcode/decoder/DecodedBitStreamParser.class */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode forBits;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        ArrayList arrayList = new ArrayList(1);
        CharacterSetECI characterSetECI = null;
        boolean z = false;
        int i = -1;
        int i2 = -1;
        do {
            try {
                forBits = bitSource.available() < 4 ? Mode.TERMINATOR : Mode.forBits(bitSource.readBits(4));
                CharacterSetECI characterSetECI2 = characterSetECI;
                int i3 = i;
                int i4 = i2;
                if (forBits != Mode.TERMINATOR) {
                    if (forBits != Mode.FNC1_FIRST_POSITION && forBits != Mode.FNC1_SECOND_POSITION) {
                        if (forBits == Mode.STRUCTURED_APPEND) {
                            if (bitSource.available() < 16) {
                                throw FormatException.getFormatInstance();
                            }
                            i3 = bitSource.readBits(8);
                            i4 = bitSource.readBits(8);
                            characterSetECI2 = characterSetECI;
                        } else if (forBits == Mode.ECI) {
                            characterSetECI2 = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                            if (characterSetECI2 == null) {
                                throw FormatException.getFormatInstance();
                            }
                            i3 = i;
                            i4 = i2;
                        } else if (forBits == Mode.HANZI) {
                            int readBits = bitSource.readBits(4);
                            int readBits2 = bitSource.readBits(forBits.getCharacterCountBits(version));
                            characterSetECI2 = characterSetECI;
                            i3 = i;
                            i4 = i2;
                            if (readBits == 1) {
                                decodeHanziSegment(bitSource, sb, readBits2);
                                characterSetECI2 = characterSetECI;
                                i3 = i;
                                i4 = i2;
                            }
                        } else {
                            int readBits3 = bitSource.readBits(forBits.getCharacterCountBits(version));
                            if (forBits == Mode.NUMERIC) {
                                decodeNumericSegment(bitSource, sb, readBits3);
                                characterSetECI2 = characterSetECI;
                                i3 = i;
                                i4 = i2;
                            } else if (forBits == Mode.ALPHANUMERIC) {
                                decodeAlphanumericSegment(bitSource, sb, readBits3, z);
                                characterSetECI2 = characterSetECI;
                                i3 = i;
                                i4 = i2;
                            } else if (forBits == Mode.BYTE) {
                                decodeByteSegment(bitSource, sb, readBits3, characterSetECI, arrayList, map);
                            } else {
                                if (forBits != Mode.KANJI) {
                                    throw FormatException.getFormatInstance();
                                }
                                decodeKanjiSegment(bitSource, sb, readBits3);
                            }
                        }
                    }
                    z = true;
                }
                characterSetECI = characterSetECI2;
                i = i3;
                i2 = i4;
            } catch (IllegalArgumentException e) {
                throw FormatException.getFormatInstance();
            }
        } while (forBits != Mode.TERMINATOR);
        return new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), i, i2);
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i, boolean z) throws FormatException {
        int length = sb.length();
        while (i > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(11);
            sb.append(toAlphaNumericChar(readBits / 45));
            sb.append(toAlphaNumericChar(readBits % 45));
            i -= 2;
        }
        if (i == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (!z) {
            return;
        }
        int i2 = length;
        while (true) {
            int i3 = i2;
            if (i3 >= sb.length()) {
                return;
            }
            if (sb.charAt(i3) == '%') {
                if (i3 < sb.length() - 1) {
                    int i4 = i3 + 1;
                    if (sb.charAt(i4) == '%') {
                        sb.deleteCharAt(i4);
                    }
                }
                sb.setCharAt(i3, (char) 29);
            }
            i2 = i3 + 1;
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        if ((i << 3) > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            bArr[i3] = (byte) bitSource.readBits(8);
            i2 = i3 + 1;
        }
        try {
            sb.append(new String(bArr, characterSetECI == null ? StringUtils.guessEncoding(bArr, map) : characterSetECI.name()));
            collection.add(bArr);
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            int i3 = (readBits % 96) | ((readBits / 96) << 8);
            int i4 = i3 + (i3 < 959 ? 41377 : 42657);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, StringUtils.GB2312));
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            int i3 = (readBits % 192) | ((readBits / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, StringUtils.SHIFT_JIS));
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(10);
            if (readBits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits / 100));
            sb.append(toAlphaNumericChar((readBits / 10) % 10));
            sb.append(toAlphaNumericChar(readBits % 10));
            i -= 3;
        }
        if (i == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int readBits2 = bitSource.readBits(7);
            if (readBits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits2 / 10));
            sb.append(toAlphaNumericChar(readBits2 % 10));
            return;
        }
        if (i == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int readBits3 = bitSource.readBits(4);
            if (readBits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits3));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & 192) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.getFormatInstance();
    }
}
