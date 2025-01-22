package com.nimbusds.jose.proc;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSObject;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/JOSEMatcher.class */
public class JOSEMatcher {
    private final Set<Algorithm> algs;
    private final Set<Class<? extends JOSEObject>> classes;
    private final Set<EncryptionMethod> encs;
    private final Set<URI> jkus;
    private final Set<String> kids;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/JOSEMatcher$Builder.class */
    public static class Builder {
        private Set<Algorithm> algs;
        private Set<Class<? extends JOSEObject>> classes;
        private Set<EncryptionMethod> encs;
        private Set<URI> jkus;
        private Set<String> kids;

        public Builder algorithm(Algorithm algorithm) {
            if (algorithm == null) {
                this.algs = null;
                return this;
            }
            this.algs = new HashSet(Collections.singletonList(algorithm));
            return this;
        }

        public Builder algorithms(Set<Algorithm> set) {
            this.algs = set;
            return this;
        }

        public Builder algorithms(Algorithm... algorithmArr) {
            algorithms(new HashSet(Arrays.asList(algorithmArr)));
            return this;
        }

        public JOSEMatcher build() {
            return new JOSEMatcher(this.classes, this.algs, this.encs, this.jkus, this.kids);
        }

        public Builder encryptionMethod(EncryptionMethod encryptionMethod) {
            if (encryptionMethod == null) {
                this.encs = null;
                return this;
            }
            this.encs = new HashSet(Collections.singletonList(encryptionMethod));
            return this;
        }

        public Builder encryptionMethods(Set<EncryptionMethod> set) {
            this.encs = set;
            return this;
        }

        public Builder encryptionMethods(EncryptionMethod... encryptionMethodArr) {
            encryptionMethods(new HashSet(Arrays.asList(encryptionMethodArr)));
            return this;
        }

        public Builder joseClass(Class<? extends JOSEObject> cls) {
            if (cls == null) {
                this.classes = null;
                return this;
            }
            this.classes = new HashSet(Collections.singletonList(cls));
            return this;
        }

        public Builder joseClasses(Set<Class<? extends JOSEObject>> set) {
            this.classes = set;
            return this;
        }

        public Builder joseClasses(Class<? extends JOSEObject>... clsArr) {
            joseClasses(new HashSet(Arrays.asList(clsArr)));
            return this;
        }

        public Builder jwkURL(URI uri) {
            if (uri == null) {
                this.jkus = null;
                return this;
            }
            this.jkus = new HashSet(Collections.singletonList(uri));
            return this;
        }

        public Builder jwkURLs(Set<URI> set) {
            this.jkus = set;
            return this;
        }

        public Builder jwkURLs(URI... uriArr) {
            jwkURLs(new HashSet(Arrays.asList(uriArr)));
            return this;
        }

        public Builder keyID(String str) {
            if (str == null) {
                this.kids = null;
                return this;
            }
            this.kids = new HashSet(Collections.singletonList(str));
            return this;
        }

        public Builder keyIDs(Set<String> set) {
            this.kids = set;
            return this;
        }

        public Builder keyIDs(String... strArr) {
            keyIDs(new HashSet(Arrays.asList(strArr)));
            return this;
        }
    }

    public JOSEMatcher(Set<Class<? extends JOSEObject>> set, Set<Algorithm> set2, Set<EncryptionMethod> set3, Set<URI> set4, Set<String> set5) {
        this.classes = set;
        this.algs = set2;
        this.encs = set3;
        this.jkus = set4;
        this.kids = set5;
    }

    public Set<Algorithm> getAlgorithms() {
        return this.algs;
    }

    public Set<EncryptionMethod> getEncryptionMethods() {
        return this.encs;
    }

    public Set<Class<? extends JOSEObject>> getJOSEClasses() {
        return this.classes;
    }

    public Set<URI> getJWKURLs() {
        return this.jkus;
    }

    public Set<String> getKeyIDs() {
        return this.kids;
    }

    public boolean matches(JOSEObject jOSEObject) {
        String str;
        Set<Class<? extends JOSEObject>> set = this.classes;
        if (set != null) {
            boolean z = false;
            for (Class<? extends JOSEObject> cls : set) {
                if (cls != null && cls.isInstance(jOSEObject)) {
                    z = true;
                }
            }
            if (!z) {
                return false;
            }
        }
        Set<Algorithm> set2 = this.algs;
        if (set2 != null && !set2.contains(jOSEObject.getHeader().getAlgorithm())) {
            return false;
        }
        Set<EncryptionMethod> set3 = this.encs;
        if (set3 != null && (!(jOSEObject instanceof JWEObject) || !set3.contains(((JWEObject) jOSEObject).getHeader().getEncryptionMethod()))) {
            return false;
        }
        if (this.jkus != null) {
            if (!this.jkus.contains(jOSEObject instanceof JWSObject ? ((JWSObject) jOSEObject).getHeader().getJWKURL() : jOSEObject instanceof JWEObject ? ((JWEObject) jOSEObject).getHeader().getJWKURL() : null)) {
                return false;
            }
        }
        if (this.kids == null) {
            return true;
        }
        if (jOSEObject instanceof JWSObject) {
            str = ((JWSObject) jOSEObject).getHeader().getKeyID();
        } else {
            str = null;
            if (jOSEObject instanceof JWEObject) {
                str = ((JWEObject) jOSEObject).getHeader().getKeyID();
            }
        }
        return this.kids.contains(str);
    }
}
