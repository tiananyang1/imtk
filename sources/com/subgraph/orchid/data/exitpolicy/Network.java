package com.subgraph.orchid.data.exitpolicy;

import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.data.IPv4Address;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/exitpolicy/Network.class */
public class Network {
    public static final Network ALL_ADDRESSES = new Network(IPv4Address.createFromString("0.0.0.0"), 0, "*");
    private final int maskValue;
    private final IPv4Address network;
    private final String originalString;

    Network(IPv4Address iPv4Address, int i, String str) {
        this.network = iPv4Address;
        this.maskValue = createMask(i);
        this.originalString = str;
    }

    public static Network createFromString(String str) {
        String[] split = str.split("/");
        IPv4Address createFromString = IPv4Address.createFromString(split[0]);
        if (split.length == 1) {
            return new Network(createFromString, 32, str);
        }
        if (split.length != 2) {
            throw new TorParsingException("Invalid network CIDR notation: " + str);
        }
        try {
            return new Network(createFromString, Integer.parseInt(split[1]), str);
        } catch (NumberFormatException e) {
            throw new TorParsingException("Invalid netblock mask bit value: " + split[1]);
        }
    }

    private static int createMask(int i) {
        if (i == 0) {
            return 0;
        }
        return Integer.MIN_VALUE >> (i - 1);
    }

    public boolean contains(IPv4Address iPv4Address) {
        return (iPv4Address.getAddressData() & this.maskValue) == (this.network.getAddressData() & this.maskValue);
    }

    public String toString() {
        return this.originalString;
    }
}
