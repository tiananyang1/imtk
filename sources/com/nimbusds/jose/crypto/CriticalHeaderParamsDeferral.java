package com.nimbusds.jose.crypto;

import com.nimbusds.jose.Header;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/CriticalHeaderParamsDeferral.class */
class CriticalHeaderParamsDeferral {
    private Set<String> deferredParams = Collections.emptySet();

    public void ensureHeaderPasses(JWEHeader jWEHeader) throws JOSEException {
        if (!headerPasses(jWEHeader)) {
            throw new JOSEException("Unsupported critical header parameter(s)");
        }
    }

    public Set<String> getDeferredCriticalHeaderParams() {
        return Collections.unmodifiableSet(this.deferredParams);
    }

    public Set<String> getProcessedCriticalHeaderParams() {
        return Collections.emptySet();
    }

    public boolean headerPasses(Header header) {
        Set<String> criticalParams = header.getCriticalParams();
        if (criticalParams == null || criticalParams.isEmpty()) {
            return true;
        }
        Set<String> set = this.deferredParams;
        return set != null && set.containsAll(criticalParams);
    }

    public void setDeferredCriticalHeaderParams(Set<String> set) {
        if (set == null) {
            this.deferredParams = Collections.emptySet();
        } else {
            this.deferredParams = set;
        }
    }
}
