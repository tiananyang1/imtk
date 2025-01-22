package com.subgraph.orchid.data.exitpolicy;

import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.data.IPv4Address;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/exitpolicy/PolicyRule.class */
public class PolicyRule {
    private static final String WILDCARD = "*";
    private final boolean isAcceptRule;
    private final Network network;
    private final PortRange portRange;

    private PolicyRule(Network network, PortRange portRange, boolean z) {
        this.network = network;
        this.portRange = portRange;
        this.isAcceptRule = z;
    }

    public static PolicyRule createAcceptFromString(String str) {
        return createRule(str, true);
    }

    public static PolicyRule createRejectFromString(String str) {
        return createRule(str, false);
    }

    private static PolicyRule createRule(String str, boolean z) {
        String[] split = str.split(Constants.COLON_SEPARATOR);
        if (split.length == 2) {
            return new PolicyRule(parseNetwork(split[0]), parsePortRange(split[1]), z);
        }
        throw new TorParsingException("Could not parse exit policy rule: " + str);
    }

    private static Network parseNetwork(String str) {
        return str.equals(WILDCARD) ? Network.ALL_ADDRESSES : Network.createFromString(str);
    }

    private static PortRange parsePortRange(String str) {
        return str.equals(WILDCARD) ? PortRange.ALL_PORTS : PortRange.createFromString(str);
    }

    public boolean isAcceptRule() {
        return this.isAcceptRule;
    }

    public boolean matchesDestination(IPv4Address iPv4Address, int i) {
        if (this.network.contains(iPv4Address)) {
            return this.portRange.rangeContains(i);
        }
        return false;
    }

    public boolean matchesPort(int i) {
        if (this.network.equals(Network.ALL_ADDRESSES)) {
            return this.portRange.rangeContains(i);
        }
        return false;
    }

    public String toString() {
        return (this.isAcceptRule ? "accept" : "reject") + " " + this.network + Constants.COLON_SEPARATOR + this.portRange;
    }
}
