package com.xiaomi.push;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* renamed from: com.xiaomi.push.ge */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ge.class */
public class C0651ge {
    /* renamed from: a */
    public static int m1422a(Throwable th) {
        boolean z = th instanceof C0660gn;
        Throwable th2 = th;
        if (z) {
            C0660gn c0660gn = (C0660gn) th;
            th2 = th;
            if (c0660gn.m1442a() != null) {
                th2 = c0660gn.m1442a();
            }
        }
        String message = th2.getMessage();
        if (th2.getCause() != null) {
            message = th2.getCause().getMessage();
        }
        if (th2 instanceof SocketTimeoutException) {
            return 105;
        }
        if (!(th2 instanceof SocketException)) {
            if (th2 instanceof UnknownHostException) {
                return 107;
            }
            return z ? 399 : 0;
        }
        if (message.indexOf("Network is unreachable") != -1) {
            return 102;
        }
        if (message.indexOf("Connection refused") != -1) {
            return 103;
        }
        if (message.indexOf("Connection timed out") != -1) {
            return 105;
        }
        if (message.endsWith("EACCES (Permission denied)")) {
            return 101;
        }
        if (message.indexOf("Connection reset by peer") != -1) {
            return 109;
        }
        if (message.indexOf("Broken pipe") != -1) {
            return 110;
        }
        if (message.indexOf("No route to host") != -1) {
            return 104;
        }
        return message.endsWith("EINVAL (Invalid argument)") ? 106 : 199;
    }
}
