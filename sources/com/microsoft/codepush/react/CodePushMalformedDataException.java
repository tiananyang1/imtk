package com.microsoft.codepush.react;

import java.net.MalformedURLException;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushMalformedDataException.class */
public class CodePushMalformedDataException extends RuntimeException {
    public CodePushMalformedDataException(String str, Throwable th) {
        super("Unable to parse contents of " + str + ", the file may be corrupted.", th);
    }

    public CodePushMalformedDataException(String str, MalformedURLException malformedURLException) {
        super("The package has an invalid downloadUrl: " + str, malformedURLException);
    }
}
