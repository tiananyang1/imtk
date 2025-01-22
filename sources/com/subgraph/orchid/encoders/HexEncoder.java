package com.subgraph.orchid.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/encoders/HexEncoder.class */
public class HexEncoder implements Encoder {
    protected final byte[] encodingTable = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    protected final byte[] decodingTable = new byte[128];

    public HexEncoder() {
        initialiseDecodingTable();
    }

    private static boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // com.subgraph.orchid.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        int i;
        int i2;
        int length = str.length();
        while (true) {
            i = length;
            if (i <= 0 || !ignore(str.charAt(i - 1))) {
                break;
            }
            length = i - 1;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            while (i3 < i && ignore(str.charAt(i3))) {
                i3++;
            }
            byte b = this.decodingTable[str.charAt(i3)];
            int i5 = i3 + 1;
            while (true) {
                i2 = i5;
                if (i2 >= i || !ignore(str.charAt(i2))) {
                    break;
                }
                i5 = i2 + 1;
            }
            byte b2 = this.decodingTable[str.charAt(i2)];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            outputStream.write((b << 4) | b2);
            i4++;
            i3 = i2 + 1;
        }
        return i4;
    }

    @Override // com.subgraph.orchid.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3;
        int i4;
        int i5 = i2 + i;
        while (true) {
            i3 = i5;
            if (i3 <= i || !ignore((char) bArr[i3 - 1])) {
                break;
            }
            i5 = i3 - 1;
        }
        int i6 = 0;
        while (i < i3) {
            while (i < i3 && ignore((char) bArr[i])) {
                i++;
            }
            byte b = this.decodingTable[bArr[i]];
            int i7 = i + 1;
            while (true) {
                i4 = i7;
                if (i4 >= i3 || !ignore((char) bArr[i4])) {
                    break;
                }
                i7 = i4 + 1;
            }
            byte b2 = this.decodingTable[bArr[i4]];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex data");
            }
            outputStream.write((b << 4) | b2);
            i6++;
            i = i4 + 1;
        }
        return i6;
    }

    @Override // com.subgraph.orchid.encoders.Encoder
    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3 = i;
        while (true) {
            int i4 = i3;
            if (i4 >= i + i2) {
                return i2 * 2;
            }
            int i5 = bArr[i4] & 255;
            outputStream.write(this.encodingTable[i5 >>> 4]);
            outputStream.write(this.encodingTable[i5 & 15]);
            i3 = i4 + 1;
        }
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
                byte[] bArr3 = this.decodingTable;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
            this.decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }
}
