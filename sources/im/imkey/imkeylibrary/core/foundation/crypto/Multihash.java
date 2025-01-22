package im.imkey.imkeylibrary.core.foundation.crypto;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import org.bitcoinj.core.Base58;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/Multihash.class */
public class Multihash {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private final byte[] hash;
    public final Type type;

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/Multihash$Type.class */
    public enum Type {
        md5(213, 16),
        sha1(17, 20),
        sha2_256(18, 32),
        sha2_512(19, 64),
        sha3_512(20, 64),
        blake2b(64, 64),
        blake2s(65, 32);

        private static Map<Integer, Type> lookup = new TreeMap();
        public int index;
        public int length;

        static {
            for (Type type : values()) {
                lookup.put(Integer.valueOf(type.index), type);
            }
        }

        Type(int i, int i2) {
            this.index = i;
            this.length = i2;
        }

        public static Type lookup(int i) {
            if (lookup.containsKey(Integer.valueOf(i))) {
                return lookup.get(Integer.valueOf(i));
            }
            throw new IllegalStateException("Unknown Multihash type: " + i);
        }
    }

    public Multihash(Type type, byte[] bArr) {
        if (bArr.length > 127) {
            throw new IllegalStateException("Unsupported hash size: " + bArr.length);
        }
        if (bArr.length == type.length) {
            this.type = type;
            this.hash = bArr;
            return;
        }
        throw new IllegalStateException("Incorrect hash length: " + bArr.length + " != " + type.length);
    }

    public Multihash(Multihash multihash) {
        this(multihash.type, multihash.hash);
    }

    private Multihash(byte[] bArr) {
        this(Type.lookup(bArr[0] & 255), Arrays.copyOfRange(bArr, 2, bArr.length));
    }

    public static Multihash deserialize(DataInput dataInput) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        int readUnsignedByte2 = dataInput.readUnsignedByte();
        Type lookup = Type.lookup(readUnsignedByte);
        byte[] bArr = new byte[readUnsignedByte2];
        dataInput.readFully(bArr);
        return new Multihash(lookup, bArr);
    }

    public static Multihash fromBase58(String str) {
        return new Multihash(Base58.decode(str));
    }

    public static Multihash fromHex(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalStateException("Uneven number of hex digits!");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= str.length() - 1) {
                return new Multihash(byteArrayOutputStream.toByteArray());
            }
            int i3 = i2 + 2;
            byteArrayOutputStream.write(Integer.valueOf(str.substring(i2, i3), 16).intValue());
            i = i3;
        }
    }

    private byte[] toBytes() {
        byte[] bArr = new byte[this.hash.length + 2];
        bArr[0] = (byte) this.type.index;
        byte[] bArr2 = this.hash;
        bArr[1] = (byte) bArr2.length;
        System.arraycopy(bArr2, 0, bArr, 2, bArr2.length);
        return bArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Multihash)) {
            return false;
        }
        Multihash multihash = (Multihash) obj;
        boolean z = false;
        if (this.type == multihash.type) {
            z = false;
            if (Arrays.equals(this.hash, multihash.hash)) {
                z = true;
            }
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(this.hash) ^ this.type.hashCode();
    }

    public void serialize(DataOutput dataOutput) throws IOException {
        dataOutput.write(toBytes());
    }

    public String toBase58() {
        return Base58.encode(toBytes());
    }

    public String toHex() {
        byte[] bytes = toBytes();
        char[] cArr = new char[bytes.length * 2];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bytes.length) {
                return new String(cArr);
            }
            int i3 = bytes[i2] & 255;
            int i4 = i2 * 2;
            char[] cArr2 = hexArray;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
            i = i2 + 1;
        }
    }

    public String toString() {
        return toBase58();
    }
}
