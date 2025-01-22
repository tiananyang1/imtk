package com.subgraph.orchid.data.exitpolicy;

import com.subgraph.orchid.TorException;
import com.subgraph.orchid.TorParsingException;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/exitpolicy/PortRange.class */
public class PortRange {
    private final int portEnd;
    private final int portStart;
    private static final int MAX_PORT = 65535;
    public static final PortRange ALL_PORTS = new PortRange(1, MAX_PORT);

    PortRange(int i) {
        this(i, i);
    }

    PortRange(int i, int i2) {
        if (isValidRange(i, i2)) {
            this.portStart = i;
            this.portEnd = i2;
            return;
        }
        throw new TorException("Invalid port range: " + i + Constants.ACCEPT_TIME_SEPARATOR_SERVER + i2);
    }

    public static PortRange createFromString(String str) {
        String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
        if (split.length == 1) {
            return new PortRange(stringToPort(split[0]));
        }
        if (split.length == 2) {
            return new PortRange(stringToPort(split[0]), stringToPort(split[1]));
        }
        throw new TorParsingException("Could not parse port range from string: " + str);
    }

    private static boolean isValidPort(int i) {
        return i >= 0 && i <= MAX_PORT;
    }

    private static boolean isValidRange(int i, int i2) {
        return isValidPort(i) && isValidPort(i2) && i <= i2;
    }

    private static int stringToPort(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (isValidPort(parseInt)) {
                return parseInt;
            }
            throw new TorParsingException("Illegal port value: " + str);
        } catch (NumberFormatException e) {
            throw new TorParsingException("Could not parse port value: " + str);
        }
    }

    public boolean rangeContains(int i) {
        return i >= this.portStart && i <= this.portEnd;
    }

    public String toString() {
        if (this.portStart == 1 && this.portEnd == MAX_PORT) {
            return "*";
        }
        int i = this.portStart;
        if (i == this.portEnd) {
            return Integer.toString(i);
        }
        return Integer.toString(this.portStart) + Constants.ACCEPT_TIME_SEPARATOR_SERVER + Integer.toString(this.portEnd);
    }
}
