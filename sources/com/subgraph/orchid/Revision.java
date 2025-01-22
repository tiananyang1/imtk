package com.subgraph.orchid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Revision.class */
public class Revision {
    private static final String REVISION_FILE_PATH = "/build-revision";

    public static String getBuildRevision() {
        InputStream tryResourceOpen = tryResourceOpen();
        if (tryResourceOpen == null) {
            return "";
        }
        try {
            return readFirstLine(tryResourceOpen);
        } catch (IOException e) {
            return "";
        }
    }

    private static String readFirstLine(InputStream inputStream) throws IOException {
        try {
            return new BufferedReader(new InputStreamReader(inputStream)).readLine();
        } finally {
            inputStream.close();
        }
    }

    private static InputStream tryResourceOpen() {
        return Revision.class.getResourceAsStream(REVISION_FILE_PATH);
    }
}
