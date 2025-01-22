package com.nimbusds.jose.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.PlainObject;
import com.nimbusds.jose.proc.SecurityContext;
import java.text.ParseException;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/JOSEProcessor.class */
public interface JOSEProcessor<C extends SecurityContext> {
    Payload process(JOSEObject jOSEObject, C c) throws BadJOSEException, JOSEException;

    Payload process(JWEObject jWEObject, C c) throws BadJOSEException, JOSEException;

    Payload process(JWSObject jWSObject, C c) throws BadJOSEException, JOSEException;

    Payload process(PlainObject plainObject, C c) throws BadJOSEException, JOSEException;

    Payload process(String str, C c) throws ParseException, BadJOSEException, JOSEException;
}
