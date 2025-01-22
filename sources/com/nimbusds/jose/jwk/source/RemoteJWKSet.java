package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.RemoteKeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/source/RemoteJWKSet.class */
public class RemoteJWKSet<C extends SecurityContext> implements JWKSource<C> {
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 250;
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 250;
    public static final int DEFAULT_HTTP_SIZE_LIMIT = 51200;
    private final AtomicReference<JWKSet> cachedJWKSet;
    private final ResourceRetriever jwkSetRetriever;
    private final URL jwkSetURL;

    public RemoteJWKSet(URL url) {
        this(url, null);
    }

    public RemoteJWKSet(URL url, ResourceRetriever resourceRetriever) {
        this.cachedJWKSet = new AtomicReference<>();
        if (url == null) {
            throw new IllegalArgumentException("The JWK set URL must not be null");
        }
        this.jwkSetURL = url;
        if (resourceRetriever != null) {
            this.jwkSetRetriever = resourceRetriever;
        } else {
            this.jwkSetRetriever = new DefaultResourceRetriever(250, 250, DEFAULT_HTTP_SIZE_LIMIT);
        }
    }

    protected static String getFirstSpecifiedKeyID(JWKMatcher jWKMatcher) {
        Set<String> keyIDs = jWKMatcher.getKeyIDs();
        if (keyIDs == null || keyIDs.isEmpty()) {
            return null;
        }
        for (String str : keyIDs) {
            if (str != null) {
                return str;
            }
        }
        return null;
    }

    private JWKSet updateJWKSetFromURL() throws RemoteKeySourceException {
        try {
            try {
                JWKSet parse = JWKSet.parse(this.jwkSetRetriever.retrieveResource(this.jwkSetURL).getContent());
                this.cachedJWKSet.set(parse);
                return parse;
            } catch (ParseException e) {
                throw new RemoteKeySourceException("Couldn't parse remote JWK set: " + e.getMessage(), e);
            }
        } catch (IOException e2) {
            throw new RemoteKeySourceException("Couldn't retrieve remote JWK set: " + e2.getMessage(), e2);
        }
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSource
    public List<JWK> get(JWKSelector jWKSelector, C c) throws RemoteKeySourceException {
        JWKSet updateJWKSetFromURL;
        JWKSet jWKSet = this.cachedJWKSet.get();
        JWKSet jWKSet2 = jWKSet;
        if (jWKSet == null) {
            jWKSet2 = updateJWKSetFromURL();
        }
        List<JWK> select = jWKSelector.select(jWKSet2);
        if (!select.isEmpty()) {
            return select;
        }
        String firstSpecifiedKeyID = getFirstSpecifiedKeyID(jWKSelector.getMatcher());
        if (firstSpecifiedKeyID != null && jWKSet2.getKeyByKeyId(firstSpecifiedKeyID) == null && (updateJWKSetFromURL = updateJWKSetFromURL()) != null) {
            return jWKSelector.select(updateJWKSetFromURL);
        }
        return Collections.emptyList();
    }

    public JWKSet getCachedJWKSet() {
        return this.cachedJWKSet.get();
    }

    public URL getJWKSetURL() {
        return this.jwkSetURL;
    }

    public ResourceRetriever getResourceRetriever() {
        return this.jwkSetRetriever;
    }
}
