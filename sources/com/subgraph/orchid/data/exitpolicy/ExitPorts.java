package com.subgraph.orchid.data.exitpolicy;

import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/exitpolicy/ExitPorts.class */
public class ExitPorts {
    private final boolean areAcceptPorts;
    private final List<PortRange> ranges = new ArrayList();

    private ExitPorts(boolean z) {
        this.areAcceptPorts = z;
    }

    public static ExitPorts createAcceptExitPorts(String str) {
        ExitPorts exitPorts = new ExitPorts(true);
        exitPorts.parsePortRanges(str);
        return exitPorts;
    }

    public static ExitPorts createRejectExitPorts(String str) {
        ExitPorts exitPorts = new ExitPorts(false);
        exitPorts.parsePortRanges(str);
        return exitPorts;
    }

    private void parsePortRanges(String str) {
        String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            this.ranges.add(PortRange.createFromString(split[i2]));
            i = i2 + 1;
        }
    }

    public boolean acceptsPort(int i) {
        return this.areAcceptPorts ? contains(i) : !contains(i);
    }

    public boolean areAcceptPorts() {
        return this.areAcceptPorts;
    }

    public boolean contains(int i) {
        Iterator<PortRange> it = this.ranges.iterator();
        while (it.hasNext()) {
            if (it.next().rangeContains(i)) {
                return true;
            }
        }
        return false;
    }
}
