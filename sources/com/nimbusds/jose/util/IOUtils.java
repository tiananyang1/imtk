package com.nimbusds.jose.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/IOUtils.class */
public class IOUtils {
    private IOUtils() {
    }

    public static String readFileToString(File file, Charset charset) throws IOException {
        return readInputStreamToString(new FileInputStream(file), charset);
    }

    public static String readInputStreamToString(InputStream inputStream, Charset charset) throws IOException {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        while (true) {
            int read = inputStreamReader.read(cArr, 0, cArr.length);
            if (read < 0) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }
}
