package com.subgraph.orchid.data;

import com.subgraph.orchid.TorException;
import com.subgraph.orchid.TorParsingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/IPv4Address.class */
public class IPv4Address {
    private final int addressData;

    public IPv4Address(int i) {
        this.addressData = i;
    }

    public static IPv4Address createFromString(String str) {
        return new IPv4Address(parseStringToAddressData(str));
    }

    public static boolean isValidIPv4AddressString(String str) {
        try {
            createFromString(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static int octetStringToInt(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt >= 0 && parseInt <= 255) {
                return parseInt;
            }
            throw new TorParsingException("Octet out of range: " + str);
        } catch (NumberFormatException e) {
            throw new TorParsingException("Failed to parse octet: " + str);
        }
    }

    private static int parseStringToAddressData(String str) {
        String[] split = str.split("\\.");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i >= length) {
                return i2;
            }
            i2 |= octetStringToInt(split[i]) << new int[]{24, 16, 8, 0}[i4];
            i++;
            i3 = i4 + 1;
        }
    }

    public static String stringFormat(int i) {
        return ((i >> 24) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 8) & 255) + "." + (i & 255);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IPv4Address) && ((IPv4Address) obj).addressData == this.addressData;
    }

    public int getAddressData() {
        return this.addressData;
    }

    public byte[] getAddressDataBytes() {
        int i = this.addressData;
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i = (i << 4) ^ ((this.addressData >> (i2 * 8)) & 255);
        }
        return i;
    }

    public InetAddress toInetAddress() {
        try {
            return InetAddress.getByAddress(getAddressDataBytes());
        } catch (UnknownHostException e) {
            throw new TorException(e);
        }
    }

    public String toString() {
        return stringFormat(this.addressData);
    }
}
