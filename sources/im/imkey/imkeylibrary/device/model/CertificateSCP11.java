package im.imkey.imkeylibrary.device.model;

import im.imkey.imkeylibrary.utils.TLVUtil;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/CertificateSCP11.class */
public class CertificateSCP11 extends TLVUtil {
    public static final byte OPTION_LENGTH_OFF = 1;
    public static final byte OPTION_TAG_OFF = 0;
    public static final byte OPTION_VALUE_LENGTH = 3;
    public static final byte OPTION_VALUE_OFF = 2;
    public byte[] _42_identifier;
    public byte[] _53_DiscretionaryData;
    public byte[] _5F20_subjectID;
    public byte[] _5F24_ExpirationDate;
    public byte[] _5F25_EffectiveDate;
    public byte[] _5F37_Signature;
    public byte[] _73_DiscretionaryData;
    public byte[] _7F49_PubKey;
    public byte[] _93_csn;
    public byte[] _95_keyUsage;
    public byte[] _BF20;

    public CertificateSCP11(byte[] bArr) {
        super(bArr);
    }

    public CertificateSCP11(byte[] bArr, short s, short s2) throws Exception {
        super(bArr, s, s2);
    }

    public byte[] getSignatureBytes() {
        try {
            return getSubTLVValue((short) 24375);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getSignatureMessage() {
        short s;
        short s2;
        try {
            short parseTag = (short) (TLVUtil.parseTag(this.data, (short) 0, (short) 5) + 0);
            short s3 = parseTag;
            try {
                s = (short) (parseTag + ((short) (TLVUtil.parseLength(this.data, parseTag, (short) 5) >> 16)));
                s3 = s;
                s2 = getSubTLVInfo((short) 24375, (byte) 0);
            } catch (Exception e) {
                e = e;
                s = s3;
                e.printStackTrace();
                s2 = 0;
                return Arrays.copyOfRange(this.data, (int) s, (int) s2);
            }
        } catch (Exception e2) {
            e = e2;
            s = 0;
        }
        return Arrays.copyOfRange(this.data, (int) s, (int) s2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00c7, code lost:            r7 = -1;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public short getSubTLVInfo(short r5, byte r6) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: im.imkey.imkeylibrary.device.model.CertificateSCP11.getSubTLVInfo(short, byte):short");
    }

    public byte[] getSubTLVValue(short s) throws Exception {
        short s2;
        byte[] bArr = this.data;
        short size = size();
        int parseLength = TLVUtil.parseLength(bArr, (short) 2, size);
        short s3 = (short) (((short) (parseLength >> 16)) + 2);
        short s4 = (short) (((short) (parseLength & 65535)) + s3);
        while (s3 < s4) {
            short parseTag = TLVUtil.parseTag(bArr, s3, size);
            if (parseTag == 1) {
                s2 = bArr[s3];
            } else {
                if (parseTag != 2) {
                    throw new Exception("certificate data error");
                }
                s2 = TLVUtil.getShort(bArr, s3);
            }
            int i = s3 + parseTag;
            short s5 = (short) i;
            int parseLength2 = TLVUtil.parseLength(bArr, s5, size);
            short s6 = (short) (parseLength2 & 65535);
            short s7 = (short) (parseLength2 >> 16);
            if (s == s2) {
                int i2 = i + s7;
                return Arrays.copyOfRange(bArr, i2, s6 + i2);
            }
            s3 = (short) (((short) (s5 + s7)) + s6);
        }
        return null;
    }

    public byte[] get_42_identifier() {
        return this._42_identifier;
    }

    public byte[] get_53_DiscretionaryData() {
        return this._53_DiscretionaryData;
    }

    public byte[] get_5F20_subjectID() {
        return this._5F20_subjectID;
    }

    public byte[] get_5F24_ExpirationDate() {
        return this._5F24_ExpirationDate;
    }

    public byte[] get_5F25_EffectiveDate() {
        return this._5F25_EffectiveDate;
    }

    public byte[] get_5F37_Signature() {
        return this._5F37_Signature;
    }

    public byte[] get_73_DiscretionaryData() {
        return this._73_DiscretionaryData;
    }

    public byte[] get_7F49_PubKey() {
        return this._7F49_PubKey;
    }

    public byte[] get_93_csn() {
        return this._93_csn;
    }

    public byte[] get_95_keyUsage() {
        return this._95_keyUsage;
    }

    public byte[] get_BF20() {
        return this._BF20;
    }

    public void set_42_identifier(byte[] bArr) {
        this._42_identifier = bArr;
    }

    public void set_53_DiscretionaryData(byte[] bArr) {
        this._53_DiscretionaryData = bArr;
    }

    public void set_5F20_subjectID(byte[] bArr) {
        this._5F20_subjectID = bArr;
    }

    public void set_5F24_ExpirationDate(byte[] bArr) {
        this._5F24_ExpirationDate = bArr;
    }

    public void set_5F25_EffectiveDate(byte[] bArr) {
        this._5F25_EffectiveDate = bArr;
    }

    public void set_5F37_Signature(byte[] bArr) {
        this._5F37_Signature = bArr;
    }

    public void set_73_DiscretionaryData(byte[] bArr) {
        this._73_DiscretionaryData = bArr;
    }

    public void set_7F49_PubKey(byte[] bArr) {
        this._7F49_PubKey = bArr;
    }

    public void set_93_csn(byte[] bArr) {
        this._93_csn = bArr;
    }

    public void set_95_keyUsage(byte[] bArr) {
        this._95_keyUsage = bArr;
    }

    public void set_BF20(byte[] bArr) {
        this._BF20 = bArr;
    }
}
