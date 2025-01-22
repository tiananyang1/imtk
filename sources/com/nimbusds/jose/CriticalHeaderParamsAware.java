package com.nimbusds.jose;

import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/CriticalHeaderParamsAware.class */
public interface CriticalHeaderParamsAware {
    Set<String> getDeferredCriticalHeaderParams();

    Set<String> getProcessedCriticalHeaderParams();
}
