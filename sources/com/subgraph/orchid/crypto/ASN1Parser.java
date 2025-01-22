package com.subgraph.orchid.crypto;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/ASN1Parser.class */
public class ASN1Parser {
    private static final int ASN1_TAG_BITSTRING = 3;
    private static final int ASN1_TAG_INTEGER = 2;
    private static final int ASN1_TAG_SEQUENCE = 16;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/ASN1Parser$ASN1BitString.class */
    public static class ASN1BitString implements ASN1Object {
        final byte[] bytes;

        ASN1BitString(byte[] bArr) {
            this.bytes = bArr;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public byte[] getBytes() {
            return this.bytes;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/ASN1Parser$ASN1Blob.class */
    public static class ASN1Blob extends ASN1BitString {
        ASN1Blob(byte[] bArr) {
            super(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/ASN1Parser$ASN1Integer.class */
    public static class ASN1Integer implements ASN1Object {
        final BigInteger value;

        ASN1Integer(BigInteger bigInteger) {
            this.value = bigInteger;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public BigInteger getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/ASN1Parser$ASN1Object.class */
    public interface ASN1Object {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/ASN1Parser$ASN1Sequence.class */
    public static class ASN1Sequence implements ASN1Object {
        private final List<ASN1Object> items;

        ASN1Sequence(List<ASN1Object> list) {
            this.items = list;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public List<ASN1Object> getItems() {
            return this.items;
        }
    }

    private byte[] getRemainingBytes(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    ASN1Blob createBlob(ByteBuffer byteBuffer) {
        return new ASN1Blob(getRemainingBytes(byteBuffer));
    }

    ByteBuffer getObjectBuffer(ByteBuffer byteBuffer) {
        int parseASN1Length = parseASN1Length(byteBuffer);
        if (parseASN1Length > byteBuffer.remaining()) {
            throw new IllegalArgumentException();
        }
        ByteBuffer slice = byteBuffer.slice();
        slice.limit(parseASN1Length);
        byteBuffer.position(byteBuffer.position() + parseASN1Length);
        return slice;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Object parseASN1(ByteBuffer byteBuffer) {
        int i = byteBuffer.get() & 255 & 31;
        ByteBuffer objectBuffer = getObjectBuffer(byteBuffer);
        return i != 2 ? i != 3 ? i != 16 ? createBlob(objectBuffer) : parseASN1Sequence(objectBuffer) : parseASN1BitString(objectBuffer) : parseASN1Integer(objectBuffer);
    }

    ASN1BitString parseASN1BitString(ByteBuffer byteBuffer) {
        if ((byteBuffer.get() & 255) == 0) {
            return new ASN1BitString(getRemainingBytes(byteBuffer));
        }
        throw new IllegalArgumentException();
    }

    ASN1Integer parseASN1Integer(ByteBuffer byteBuffer) {
        return new ASN1Integer(new BigInteger(getRemainingBytes(byteBuffer)));
    }

    int parseASN1Length(ByteBuffer byteBuffer) {
        int i = byteBuffer.get() & 255;
        return i < 128 ? i : parseASN1LengthLong(i & 127, byteBuffer);
    }

    int parseASN1LengthLong(int i, ByteBuffer byteBuffer) {
        if (i == 0 || i > 3) {
            throw new IllegalArgumentException();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) | (byteBuffer.get() & 255);
        }
        return i2;
    }

    ASN1Sequence parseASN1Sequence(ByteBuffer byteBuffer) {
        ArrayList arrayList = new ArrayList();
        while (byteBuffer.hasRemaining()) {
            arrayList.add(parseASN1(byteBuffer));
        }
        return new ASN1Sequence(arrayList);
    }
}
