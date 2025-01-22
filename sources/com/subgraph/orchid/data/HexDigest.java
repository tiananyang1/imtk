package com.subgraph.orchid.data;

import com.subgraph.orchid.Tor;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.encoders.Base64;
import com.subgraph.orchid.encoders.Hex;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/HexDigest.class */
public class HexDigest {
    private final byte[] digestBytes;
    private final boolean isDigest256;

    private HexDigest(byte[] bArr) {
        if (bArr.length == 20 || bArr.length == 32) {
            this.digestBytes = new byte[bArr.length];
            this.isDigest256 = this.digestBytes.length == 32;
            System.arraycopy(bArr, 0, this.digestBytes, 0, bArr.length);
            return;
        }
        throw new TorException("Digest data is not the correct length " + bArr.length + " != (20 or 32)");
    }

    public static HexDigest createDigestForData(byte[] bArr) {
        TorMessageDigest torMessageDigest = new TorMessageDigest();
        torMessageDigest.update(bArr);
        return new HexDigest(torMessageDigest.getDigestBytes());
    }

    public static HexDigest createFromBase32String(String str) {
        return new HexDigest(Base32.base32Decode(str));
    }

    public static HexDigest createFromDigestBytes(byte[] bArr) {
        return new HexDigest(bArr);
    }

    public static HexDigest createFromString(String str) {
        String[] split = str.split(" ");
        return split.length > 1 ? createFromStringList(Arrays.asList(split)) : new HexDigest(Hex.decode(str));
    }

    public static HexDigest createFromStringList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return createFromString(sb.toString());
    }

    private String stripTrailingEquals(String str) {
        int i;
        int length = str.length();
        while (true) {
            i = length;
            if (i <= 0 || str.charAt(i - 1) != '=') {
                break;
            }
            length = i - 1;
        }
        return str.substring(0, i);
    }

    public boolean equals(Object obj) {
        if (obj instanceof HexDigest) {
            return Arrays.equals(((HexDigest) obj).digestBytes, this.digestBytes);
        }
        return false;
    }

    public byte[] getRawBytes() {
        byte[] bArr = this.digestBytes;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i = (i << 8) | (this.digestBytes[i2] & 255);
        }
        return i;
    }

    public boolean isDigest256() {
        return this.isDigest256;
    }

    public String toBase32() {
        return Base32.base32Encode(this.digestBytes);
    }

    public String toBase64(boolean z) {
        String str = new String(Base64.encode(this.digestBytes), Tor.getDefaultCharset());
        return z ? stripTrailingEquals(str) : str;
    }

    public String toSpacedString() {
        String hexDigest = toString();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= hexDigest.length()) {
                return sb.toString();
            }
            if (i2 > 0 && i2 % 4 == 0) {
                sb.append(' ');
            }
            sb.append(hexDigest.charAt(i2));
            i = i2 + 1;
        }
    }

    public String toString() {
        return new String(Hex.encode(this.digestBytes));
    }
}
