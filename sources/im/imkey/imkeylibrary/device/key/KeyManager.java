package im.imkey.imkeylibrary.device.key;

import com.google.common.io.BaseEncoding;
import im.imkey.imkeylibrary.core.foundation.crypto.AES;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import org.bitcoinj.core.ECKey;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/key/KeyManager.class */
public class KeyManager {
    private static KeyManager instance;
    private byte[] privKey = new byte[32];
    private byte[] pubKey = new byte[65];
    private byte[] sePubKey = new byte[65];
    private byte[] sessionKey = new byte[16];
    private byte[] checksum = new byte[4];
    private byte[] encryKey = new byte[16];

    /* renamed from: IV */
    private byte[] f2751IV = new byte[16];

    private KeyManager() {
    }

    public static KeyManager getInstance() {
        if (instance == null) {
            instance = new KeyManager();
        }
        return instance;
    }

    public Boolean decryptKeys(String str) {
        byte[] decryptByCBC = AES.decryptByCBC(BaseEncoding.base64().decode(str), this.encryKey, this.f2751IV);
        int i = 0;
        if (decryptByCBC.length == 0) {
            return false;
        }
        System.arraycopy(decryptByCBC, 0, this.privKey, 0, 32);
        System.arraycopy(decryptByCBC, 32, this.pubKey, 0, 65);
        System.arraycopy(decryptByCBC, 97, this.sePubKey, 0, 65);
        System.arraycopy(decryptByCBC, 162, this.sessionKey, 0, 16);
        System.arraycopy(decryptByCBC, 178, this.checksum, 0, 4);
        byte[] bArr = new byte[178];
        System.arraycopy(decryptByCBC, 0, bArr, 0, 178);
        byte[] sha256 = Hash.sha256(bArr);
        while (true) {
            byte[] bArr2 = this.checksum;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr2[i] != sha256[i]) {
                return false;
            }
            i++;
        }
    }

    public String encryptKeys() {
        byte[] bArr = new byte[178];
        System.arraycopy(this.privKey, 0, bArr, 0, 32);
        System.arraycopy(this.pubKey, 0, bArr, 32, 65);
        System.arraycopy(this.sePubKey, 0, bArr, 97, 65);
        System.arraycopy(this.sessionKey, 0, bArr, 162, 16);
        byte[] sha256 = Hash.sha256(bArr);
        byte[] bArr2 = new byte[182];
        System.arraycopy(bArr, 0, bArr2, 0, 178);
        System.arraycopy(sha256, 0, bArr2, 178, 4);
        return BaseEncoding.base64().encode(AES.encryptByCBC(bArr2, this.encryKey, this.f2751IV));
    }

    public void genEncryKey(String str, String str2) {
        byte[] sha256 = Hash.sha256(str.getBytes());
        byte[] sha2562 = Hash.sha256(str2.getBytes());
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= sha256.length) {
                System.arraycopy(sha256, 0, this.encryKey, 0, 16);
                System.arraycopy(sha256, sha256.length - 16, this.f2751IV, 0, 16);
                return;
            } else {
                sha256[i2] = (byte) (sha256[i2] ^ sha2562[i2]);
                i = i2 + 1;
            }
        }
    }

    public void genLocalKeys() {
        ECKey eCKey = new ECKey();
        this.privKey = eCKey.getPrivKeyBytes();
        this.pubKey = eCKey.decompress().getPubKey();
    }

    public byte[] getChecksum() {
        return this.checksum;
    }

    public byte[] getEncryKey() {
        return this.encryKey;
    }

    public byte[] getPrivKey() {
        return this.privKey;
    }

    public byte[] getPubKey() {
        return this.pubKey;
    }

    public byte[] getSePubKey() {
        return this.sePubKey;
    }

    public byte[] getSessionKey() {
        return this.sessionKey;
    }

    public void setChecksum(byte[] bArr) {
        this.checksum = bArr;
    }

    public void setEncryKey(byte[] bArr) {
        this.encryKey = bArr;
    }

    public void setPrivKey(byte[] bArr) {
        this.privKey = bArr;
    }

    public void setPubKey(byte[] bArr) {
        this.pubKey = bArr;
    }

    public void setSePubKey(byte[] bArr) {
        this.sePubKey = bArr;
    }

    public void setSessionKey(byte[] bArr) {
        this.sessionKey = bArr;
    }
}
