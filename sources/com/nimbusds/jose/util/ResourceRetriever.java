package com.nimbusds.jose.util;

import java.io.IOException;
import java.net.URL;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/ResourceRetriever.class */
public interface ResourceRetriever {
    Resource retrieveResource(URL url) throws IOException;
}
