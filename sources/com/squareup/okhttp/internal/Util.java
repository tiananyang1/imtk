package com.squareup.okhttp.internal;

import com.squareup.okhttp.HttpUrl;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.ByteString;
import okio.Source;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/Util.class */
public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Util() {
    }

    public static void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void closeAll(Closeable closeable, Closeable closeable2) throws IOException {
        Throwable th;
        try {
            closeable.close();
            th = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            closeable2.close();
            th = th;
        } catch (Throwable th3) {
            th = th;
            if (th == null) {
                th = th3;
            }
        }
        if (th == null) {
            return;
        }
        if (th instanceof IOException) {
            throw ((IOException) th);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (!(th instanceof Error)) {
            throw new AssertionError(th);
        }
        throw ((Error) th);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!isAndroidGetsocknameError(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
            }
        }
    }

    public static String[] concat(String[] strArr, String str) {
        String[] strArr2 = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    public static boolean contains(String[] strArr, String str) {
        return Arrays.asList(strArr).contains(str);
    }

    public static boolean discard(Source source, int i, TimeUnit timeUnit) {
        try {
            return skipAll(source, i, timeUnit);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static String hostHeader(HttpUrl httpUrl) {
        if (httpUrl.port() == HttpUrl.defaultPort(httpUrl.scheme())) {
            return httpUrl.host();
        }
        return httpUrl.host() + Constants.COLON_SEPARATOR + httpUrl.port();
    }

    public static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> immutableList(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        return Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    private static <T> List<T> intersect(T[] tArr, T[] tArr2) {
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            T t = tArr[i2];
            int length2 = tArr2.length;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < length2) {
                    T t2 = tArr2[i4];
                    if (t.equals(t2)) {
                        arrayList.add(t2);
                        break;
                    }
                    i3 = i4 + 1;
                }
            }
            i = i2 + 1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] intersect(Class<T> cls, T[] tArr, T[] tArr2) {
        List intersect = intersect(tArr, tArr2);
        return (T[]) intersect.toArray((Object[]) Array.newInstance((Class<?>) cls, intersect.size()));
    }

    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static String md5Hex(String str) {
        try {
            return ByteString.of(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"))).hex();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static ByteString sha1(ByteString byteString) {
        try {
            return ByteString.of(MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE).digest(byteString.toByteArray()));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static String shaBase64(String str) {
        try {
            return ByteString.of(MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE).digest(str.getBytes("UTF-8"))).base64();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static boolean skipAll(Source source, int i, TimeUnit timeUnit) throws IOException {
        long nanoTime = System.nanoTime();
        long deadlineNanoTime = source.timeout().hasDeadline() ? source.timeout().deadlineNanoTime() - nanoTime : Long.MAX_VALUE;
        source.timeout().deadlineNanoTime(Math.min(deadlineNanoTime, timeUnit.toNanos(i)) + nanoTime);
        try {
            Buffer buffer = new Buffer();
            while (source.read(buffer, 2048L) != -1) {
                buffer.clear();
            }
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
                return true;
            }
            source.timeout().deadlineNanoTime(nanoTime + deadlineNanoTime);
            return true;
        } catch (InterruptedIOException e) {
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
                return false;
            }
            source.timeout().deadlineNanoTime(nanoTime + deadlineNanoTime);
            return false;
        } catch (Throwable th) {
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(nanoTime + deadlineNanoTime);
            }
            throw th;
        }
    }

    public static ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() { // from class: com.squareup.okhttp.internal.Util.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002d, code lost:            r0 = new okio.Buffer();        r0.writeUtf8(r5, 0, r6);     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0042, code lost:            if (r6 >= r0) goto L26;     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0045, code lost:            r0 = r5.codePointAt(r6);     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004e, code lost:            if (r0 <= 31) goto L18;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0054, code lost:            if (r0 >= 127) goto L18;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0057, code lost:            r0 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:            r0.writeUtf8CodePoint(r0);        r6 = r6 + java.lang.Character.charCount(r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:            r0 = 63;     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:            r10 = r0.readUtf8();     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String toHumanReadableAscii(java.lang.String r5) {
        /*
            r0 = r5
            int r0 = r0.length()
            r9 = r0
            r0 = 0
            r6 = r0
        L8:
            r0 = r5
            r10 = r0
            r0 = r6
            r1 = r9
            if (r0 >= r1) goto L77
            r0 = r5
            r1 = r6
            int r0 = r0.codePointAt(r1)
            r7 = r0
            r0 = r7
            r1 = 31
            if (r0 <= r1) goto L2d
            r0 = r7
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 >= r1) goto L2d
            r0 = r6
            r1 = r7
            int r1 = java.lang.Character.charCount(r1)
            int r0 = r0 + r1
            r6 = r0
            goto L8
        L2d:
            okio.Buffer r0 = new okio.Buffer
            r1 = r0
            r1.<init>()
            r10 = r0
            r0 = r10
            r1 = r5
            r2 = 0
            r3 = r6
            okio.Buffer r0 = r0.writeUtf8(r1, r2, r3)
        L3f:
            r0 = r6
            r1 = r9
            if (r0 >= r1) goto L70
            r0 = r5
            r1 = r6
            int r0 = r0.codePointAt(r1)
            r8 = r0
            r0 = r8
            r1 = 31
            if (r0 <= r1) goto L5c
            r0 = r8
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 >= r1) goto L5c
            r0 = r8
            r7 = r0
            goto L5f
        L5c:
            r0 = 63
            r7 = r0
        L5f:
            r0 = r10
            r1 = r7
            okio.Buffer r0 = r0.writeUtf8CodePoint(r1)
            r0 = r6
            r1 = r8
            int r1 = java.lang.Character.charCount(r1)
            int r0 = r0 + r1
            r6 = r0
            goto L3f
        L70:
            r0 = r10
            java.lang.String r0 = r0.readUtf8()
            r10 = r0
        L77:
            r0 = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.Util.toHumanReadableAscii(java.lang.String):java.lang.String");
    }
}
