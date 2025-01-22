package com.subgraph.orchid.data.exitpolicy;

import com.subgraph.orchid.data.IPv4Address;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/exitpolicy/ExitPolicy.class */
public class ExitPolicy {
    private final List<PolicyRule> rules = new ArrayList();

    public boolean acceptsDestination(IPv4Address iPv4Address, int i) {
        if (iPv4Address == null) {
            return acceptsPort(i);
        }
        for (PolicyRule policyRule : this.rules) {
            if (policyRule.matchesDestination(iPv4Address, i)) {
                return policyRule.isAcceptRule();
            }
        }
        return true;
    }

    public boolean acceptsPort(int i) {
        for (PolicyRule policyRule : this.rules) {
            if (policyRule.matchesPort(i)) {
                return policyRule.isAcceptRule();
            }
        }
        return false;
    }

    public boolean acceptsTarget(ExitTarget exitTarget) {
        return exitTarget.isAddressTarget() ? acceptsDestination(exitTarget.getAddress(), exitTarget.getPort()) : acceptsPort(exitTarget.getPort());
    }

    public void addAcceptRule(String str) {
        this.rules.add(PolicyRule.createAcceptFromString(str));
    }

    public void addRejectRule(String str) {
        this.rules.add(PolicyRule.createRejectFromString(str));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<PolicyRule> it = this.rules.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("\n");
        }
        return sb.toString();
    }
}
