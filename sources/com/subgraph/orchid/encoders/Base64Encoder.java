package com.subgraph.orchid.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/encoders/Base64Encoder.class */
public class Base64Encoder implements Encoder {
    protected final byte[] encodingTable = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = 61;
    protected final byte[] decodingTable = new byte[128];

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        byte b = this.padding;
        if (c3 == b) {
            byte[] bArr = this.decodingTable;
            byte b2 = bArr[c];
            byte b3 = bArr[c2];
            if ((b2 | b3) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b2 << 2) | (b3 >> 4));
            return 1;
        }
        if (c4 == b) {
            byte[] bArr2 = this.decodingTable;
            byte b4 = bArr2[c];
            byte b5 = bArr2[c2];
            byte b6 = bArr2[c3];
            if ((b4 | b5 | b6) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b4 << 2) | (b5 >> 4));
            outputStream.write((b5 << 4) | (b6 >> 2));
            return 2;
        }
        byte[] bArr3 = this.decodingTable;
        byte b7 = bArr3[c];
        byte b8 = bArr3[c2];
        byte b9 = bArr3[c3];
        byte b10 = bArr3[c4];
        if ((b7 | b8 | b9 | b10) < 0) {
            throw new IOException("invalid characters encountered at end of base64 data");
        }
        outputStream.write((b7 << 2) | (b8 >> 4));
        outputStream.write((b8 << 4) | (b9 >> 2));
        outputStream.write((b9 << 6) | b10);
        return 3;
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    private int nextI(String str, int i, int i2) {
        while (i < i2 && ignore(str.charAt(i))) {
            i++;
        }
        return i;
    }

    private int nextI(byte[] bArr, int i, int i2) {
        while (i < i2 && ignore((char) bArr[i])) {
            i++;
        }
        return i;
    }

    @Override // com.subgraph.orchid.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        int i;
        int length = str.length();
        while (true) {
            i = length;
            if (i <= 0 || !ignore(str.charAt(i - 1))) {
                break;
            }
            length = i - 1;
        }
        int i2 = i - 4;
        int i3 = 0;
        int nextI = nextI(str, 0, i2);
        while (true) {
            int i4 = nextI;
            if (i4 >= i2) {
                return i3 + decodeLastBlock(outputStream, str.charAt(i2), str.charAt(i - 3), str.charAt(i - 2), str.charAt(i - 1));
            }
            byte b = this.decodingTable[str.charAt(i4)];
            int nextI2 = nextI(str, i4 + 1, i2);
            byte b2 = this.decodingTable[str.charAt(nextI2)];
            int nextI3 = nextI(str, nextI2 + 1, i2);
            byte b3 = this.decodingTable[str.charAt(nextI3)];
            int nextI4 = nextI(str, nextI3 + 1, i2);
            byte b4 = this.decodingTable[str.charAt(nextI4)];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i3 += 3;
            nextI = nextI(str, nextI4 + 1, i2);
        }
    }

    @Override // com.subgraph.orchid.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3;
        int i4 = i2 + i;
        while (true) {
            i3 = i4;
            if (i3 <= i || !ignore((char) bArr[i3 - 1])) {
                break;
            }
            i4 = i3 - 1;
        }
        int i5 = i3 - 4;
        int nextI = nextI(bArr, i, i5);
        int i6 = 0;
        while (nextI < i5) {
            byte b = this.decodingTable[bArr[nextI]];
            int nextI2 = nextI(bArr, nextI + 1, i5);
            byte b2 = this.decodingTable[bArr[nextI2]];
            int nextI3 = nextI(bArr, nextI2 + 1, i5);
            byte b3 = this.decodingTable[bArr[nextI3]];
            int nextI4 = nextI(bArr, nextI3 + 1, i5);
            byte b4 = this.decodingTable[bArr[nextI4]];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i6 += 3;
            nextI = nextI(bArr, nextI4 + 1, i5);
        }
        return i6 + decodeLastBlock(outputStream, (char) bArr[i5], (char) bArr[i3 - 3], (char) bArr[i3 - 2], (char) bArr[i3 - 1]);
    }

    @Override // com.subgraph.orchid.encoders.Encoder
    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3;
        int i4 = i2 % 3;
        int i5 = i2 - i4;
        int i6 = i;
        while (true) {
            int i7 = i6;
            i3 = i + i5;
            if (i7 >= i3) {
                break;
            }
            int i8 = bArr[i7] & 255;
            int i9 = bArr[i7 + 1] & 255;
            int i10 = bArr[i7 + 2] & 255;
            outputStream.write(this.encodingTable[(i8 >>> 2) & 63]);
            outputStream.write(this.encodingTable[((i8 << 4) | (i9 >>> 4)) & 63]);
            outputStream.write(this.encodingTable[((i9 << 2) | (i10 >>> 6)) & 63]);
            outputStream.write(this.encodingTable[i10 & 63]);
            i6 = i7 + 3;
        }
        if (i4 != 0) {
            if (i4 == 1) {
                int i11 = bArr[i3] & 255;
                outputStream.write(this.encodingTable[(i11 >>> 2) & 63]);
                outputStream.write(this.encodingTable[(i11 << 4) & 63]);
                outputStream.write(this.padding);
                outputStream.write(this.padding);
            } else if (i4 == 2) {
                int i12 = bArr[i3] & 255;
                int i13 = bArr[i3 + 1] & 255;
                outputStream.write(this.encodingTable[(i12 >>> 2) & 63]);
                outputStream.write(this.encodingTable[((i12 << 4) | (i13 >>> 4)) & 63]);
                outputStream.write(this.encodingTable[(i13 << 2) & 63]);
                outputStream.write(this.padding);
            }
        }
        int i14 = i5 / 3;
        int i15 = 4;
        if (i4 == 0) {
            i15 = 0;
        }
        return (i14 * 4) + i15;
    }

    protected void initialiseDecodingTable() {
        int i;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            byte[] bArr = this.decodingTable;
            i = 0;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = -1;
            i2 = i3 + 1;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i >= bArr2.length) {
                return;
            }
            this.decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }
}
