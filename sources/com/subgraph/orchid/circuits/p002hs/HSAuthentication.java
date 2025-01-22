package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie;
import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.crypto.TorStreamCipher;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSAuthentication.class */
public class HSAuthentication {
    private static final int BASIC_ID_LENGTH = 4;
    private final HSDescriptorCookie cookie;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSAuthentication$BasicAuthEntry.class */
    public static class BasicAuthEntry {

        /* renamed from: id */
        final byte[] f188id;
        final byte[] skey;

        BasicAuthEntry(byte[] bArr, byte[] bArr2) {
            this.f188id = bArr;
            this.skey = bArr2;
        }
    }

    public HSAuthentication(HSDescriptorCookie hSDescriptorCookie) {
        this.cookie = hSDescriptorCookie;
    }

    private BasicAuthEntry createEntry(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[16];
        byteBuffer.get(bArr);
        byteBuffer.get(bArr2);
        return new BasicAuthEntry(bArr, bArr2);
    }

    private byte[] decryptAuthEntry(BasicAuthEntry basicAuthEntry) throws HSAuthenticationException {
        TorStreamCipher.createFromKeyBytes(this.cookie.getValue()).encrypt(basicAuthEntry.skey);
        return basicAuthEntry.skey;
    }

    private byte[] decryptIntroductionPointsWithBasicAuth(ByteBuffer byteBuffer) throws HSAuthenticationException {
        HSDescriptorCookie hSDescriptorCookie = this.cookie;
        if (hSDescriptorCookie == null || hSDescriptorCookie.getType() != HSDescriptorCookie.CookieType.COOKIE_BASIC) {
            throw new TorParsingException("Introduction points encrypted with 'basic' authentication and no cookie available to decrypt");
        }
        List<BasicAuthEntry> readBasicEntries = readBasicEntries(byteBuffer);
        byte[] readAuthIV = readAuthIV(byteBuffer);
        return decryptRemaining(byteBuffer, findKeyInAuthEntries(readBasicEntries, generateAuthId(readAuthIV)), readAuthIV);
    }

    private byte[] decryptIntroductionPointsWithStealthAuth(ByteBuffer byteBuffer) {
        HSDescriptorCookie hSDescriptorCookie = this.cookie;
        if (hSDescriptorCookie == null || hSDescriptorCookie.getType() != HSDescriptorCookie.CookieType.COOKIE_STEALTH) {
            throw new TorParsingException("Introduction points encrypted with 'stealth' authentication and no cookie available to descrypt");
        }
        return decryptRemaining(byteBuffer, this.cookie.getValue(), readAuthIV(byteBuffer));
    }

    private byte[] decryptRemaining(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) {
        TorStreamCipher createFromKeyBytesWithIV = TorStreamCipher.createFromKeyBytesWithIV(bArr, bArr2);
        byte[] bArr3 = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr3);
        createFromKeyBytesWithIV.encrypt(bArr3);
        return bArr3;
    }

    private byte[] findKeyInAuthEntries(List<BasicAuthEntry> list, byte[] bArr) throws HSAuthenticationException {
        for (BasicAuthEntry basicAuthEntry : list) {
            if (Arrays.equals(bArr, basicAuthEntry.f188id)) {
                return decryptAuthEntry(basicAuthEntry);
            }
        }
        throw new HSAuthenticationException("Could not find matching cookie id for basic authentication");
    }

    private byte[] generateAuthId(byte[] bArr) {
        TorMessageDigest torMessageDigest = new TorMessageDigest();
        torMessageDigest.update(this.cookie.getValue());
        torMessageDigest.update(bArr);
        byte[] digestBytes = torMessageDigest.getDigestBytes();
        byte[] bArr2 = new byte[4];
        System.arraycopy(digestBytes, 0, bArr2, 0, 4);
        return bArr2;
    }

    private byte[] readAuthIV(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[16];
        byteBuffer.get(bArr);
        return bArr;
    }

    private List<BasicAuthEntry> readBasicEntries(ByteBuffer byteBuffer) {
        int i = (byteBuffer.get() & 255) * 16;
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                return arrayList;
            }
            arrayList.add(createEntry(byteBuffer));
            i2 = i3 + 1;
        }
    }

    public byte[] decryptIntroductionPoints(byte[] bArr) throws HSAuthenticationException {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        int i = wrap.get() & 255;
        if (i == 1) {
            return decryptIntroductionPointsWithBasicAuth(wrap);
        }
        if (i == 2) {
            return decryptIntroductionPointsWithStealthAuth(wrap);
        }
        throw new HSAuthenticationException("Introduction points section begins with unrecognized byte (" + i + ")");
    }
}
