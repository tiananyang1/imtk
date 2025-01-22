package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.HiddenServiceCircuit;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie;
import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.data.Base32;
import com.subgraph.orchid.data.HexDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HiddenService.class */
public class HiddenService {
    private HiddenServiceCircuit circuit;
    private final TorConfig config;
    private HSDescriptor descriptor;
    private final byte[] permanentId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HiddenService(TorConfig torConfig, byte[] bArr) {
        this.config = torConfig;
        this.permanentId = bArr;
    }

    static byte[] calculateTimePeriod(long j, int i) {
        return toNetworkBytes((j + ((i * 86400) / 256)) / 86400);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] decodeOnion(String str) {
        int indexOf = str.indexOf(".onion");
        return indexOf == -1 ? Base32.base32Decode(str) : Base32.base32Decode(str.substring(0, indexOf));
    }

    static byte[] toNetworkBytes(long j) {
        byte[] bArr = new byte[4];
        int i = 3;
        while (true) {
            int i2 = i;
            if (i2 < 0) {
                return bArr;
            }
            bArr[i2] = (byte) (255 & j);
            j >>= 8;
            i = i2 - 1;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.equals(this.permanentId, ((HiddenService) obj).permanentId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<HexDigest> getAllCurrentDescriptorIds() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getCurrentDescriptorId(0));
        arrayList.add(getCurrentDescriptorId(1));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSDescriptorCookie getAuthenticationCookie() {
        return this.config.getHidServAuth(getOnionAddress());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HiddenServiceCircuit getCircuit() {
        return this.circuit;
    }

    HexDigest getCurrentDescriptorId(int i) {
        TorMessageDigest torMessageDigest = new TorMessageDigest();
        torMessageDigest.update(this.permanentId);
        torMessageDigest.update(getCurrentSecretId(i));
        return torMessageDigest.getHexDigest();
    }

    byte[] getCurrentSecretId(int i) {
        TorMessageDigest torMessageDigest = new TorMessageDigest();
        torMessageDigest.update(getCurrentTimePeriod());
        HSDescriptorCookie authenticationCookie = getAuthenticationCookie();
        if (authenticationCookie != null && authenticationCookie.getType() == HSDescriptorCookie.CookieType.COOKIE_STEALTH) {
            torMessageDigest.update(authenticationCookie.getValue());
        }
        torMessageDigest.update(new byte[]{(byte) i});
        return torMessageDigest.getDigestBytes();
    }

    byte[] getCurrentTimePeriod() {
        return calculateTimePeriod(System.currentTimeMillis() / 1000, this.permanentId[0] & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSDescriptor getDescriptor() {
        return this.descriptor;
    }

    String getOnionAddress() {
        return Base32.base32Encode(this.permanentId) + ".onion";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getOnionAddressForLogging() {
        return this.config.getSafeLogging() ? "[scrubbed]" : getOnionAddress();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasCurrentDescriptor() {
        HSDescriptor hSDescriptor = this.descriptor;
        return (hSDescriptor == null || hSDescriptor.isExpired()) ? false : true;
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.permanentId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCircuit(HiddenServiceCircuit hiddenServiceCircuit) {
        this.circuit = hiddenServiceCircuit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDescriptor(HSDescriptor hSDescriptor) {
        this.descriptor = hSDescriptor;
    }
}
