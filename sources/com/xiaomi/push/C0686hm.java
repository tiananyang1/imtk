package com.xiaomi.push;

import com.xiaomi.mipush.sdk.Constants;
import java.net.UnknownHostException;

/* renamed from: com.xiaomi.push.hm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hm.class */
final class C0686hm {

    /* renamed from: com.xiaomi.push.hm$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hm$a.class */
    static class a {

        /* renamed from: a */
        EnumC0637fr f1384a;

        /* renamed from: a */
        String f1385a;

        a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v38, types: [java.lang.Throwable] */
    /* renamed from: a */
    public static a m1556a(Exception exc) {
        m1557a(exc);
        Exception exc2 = exc;
        if (exc instanceof C0660gn) {
            C0660gn c0660gn = (C0660gn) exc;
            exc2 = exc;
            if (c0660gn.m1442a() != null) {
                exc2 = c0660gn.m1442a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        String str = exc2.getClass().getSimpleName() + Constants.COLON_SEPARATOR + message;
        int m1422a = C0651ge.m1422a(exc2);
        if (m1422a != 0) {
            aVar.f1384a = EnumC0637fr.m1280a(EnumC0637fr.GSLB_REQUEST_SUCCESS.m1281a() + m1422a);
        }
        if (aVar.f1384a == null) {
            aVar.f1384a = EnumC0637fr.GSLB_TCP_ERR_OTHER;
        }
        if (aVar.f1384a == EnumC0637fr.GSLB_TCP_ERR_OTHER) {
            aVar.f1385a = str;
        }
        return aVar;
    }

    /* renamed from: a */
    private static void m1557a(Exception exc) {
        if (exc == null) {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v49, types: [java.lang.Throwable] */
    /* renamed from: b */
    public static a m1558b(Exception exc) {
        EnumC0637fr enumC0637fr;
        Throwable cause;
        m1557a(exc);
        Exception exc2 = exc;
        if (exc instanceof C0660gn) {
            C0660gn c0660gn = (C0660gn) exc;
            exc2 = exc;
            if (c0660gn.m1442a() != null) {
                exc2 = c0660gn.m1442a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        int m1422a = C0651ge.m1422a(exc2);
        String str = exc2.getClass().getSimpleName() + Constants.COLON_SEPARATOR + message;
        if (m1422a != 0) {
            aVar.f1384a = EnumC0637fr.m1280a(EnumC0637fr.CONN_SUCCESS.m1281a() + m1422a);
            if (aVar.f1384a == EnumC0637fr.CONN_BOSH_ERR && (cause = exc2.getCause()) != null && (cause instanceof UnknownHostException)) {
                enumC0637fr = EnumC0637fr.CONN_BOSH_UNKNOWNHOST;
            }
            if (aVar.f1384a != EnumC0637fr.CONN_TCP_ERR_OTHER || aVar.f1384a == EnumC0637fr.CONN_XMPP_ERR || aVar.f1384a == EnumC0637fr.CONN_BOSH_ERR) {
                aVar.f1385a = str;
            }
            return aVar;
        }
        enumC0637fr = EnumC0637fr.CONN_XMPP_ERR;
        aVar.f1384a = enumC0637fr;
        if (aVar.f1384a != EnumC0637fr.CONN_TCP_ERR_OTHER) {
        }
        aVar.f1385a = str;
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v52, types: [java.lang.Throwable] */
    /* renamed from: c */
    public static a m1559c(Exception exc) {
        EnumC0637fr enumC0637fr;
        m1557a(exc);
        Exception exc2 = exc;
        if (exc instanceof C0660gn) {
            C0660gn c0660gn = (C0660gn) exc;
            exc2 = exc;
            if (c0660gn.m1442a() != null) {
                exc2 = c0660gn.m1442a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        int m1422a = C0651ge.m1422a(exc2);
        String str = exc2.getClass().getSimpleName() + Constants.COLON_SEPARATOR + message;
        if (m1422a == 105) {
            enumC0637fr = EnumC0637fr.BIND_TCP_READ_TIMEOUT;
        } else if (m1422a == 199) {
            enumC0637fr = EnumC0637fr.BIND_TCP_ERR;
        } else {
            if (m1422a == 499) {
                aVar.f1384a = EnumC0637fr.BIND_BOSH_ERR;
                if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                    enumC0637fr = EnumC0637fr.BIND_BOSH_ITEM_NOT_FOUND;
                }
                if (aVar.f1384a != EnumC0637fr.BIND_TCP_ERR || aVar.f1384a == EnumC0637fr.BIND_XMPP_ERR || aVar.f1384a == EnumC0637fr.BIND_BOSH_ERR) {
                    aVar.f1385a = str;
                }
                return aVar;
            }
            enumC0637fr = m1422a != 109 ? m1422a != 110 ? EnumC0637fr.BIND_XMPP_ERR : EnumC0637fr.BIND_TCP_BROKEN_PIPE : EnumC0637fr.BIND_TCP_CONNRESET;
        }
        aVar.f1384a = enumC0637fr;
        if (aVar.f1384a != EnumC0637fr.BIND_TCP_ERR) {
        }
        aVar.f1385a = str;
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v47, types: [java.lang.Throwable] */
    /* renamed from: d */
    public static a m1560d(Exception exc) {
        EnumC0637fr enumC0637fr;
        m1557a(exc);
        Exception exc2 = exc;
        if (exc instanceof C0660gn) {
            C0660gn c0660gn = (C0660gn) exc;
            exc2 = exc;
            if (c0660gn.m1442a() != null) {
                exc2 = c0660gn.m1442a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        int m1422a = C0651ge.m1422a(exc2);
        String str = exc2.getClass().getSimpleName() + Constants.COLON_SEPARATOR + message;
        if (m1422a == 105) {
            enumC0637fr = EnumC0637fr.CHANNEL_TCP_READTIMEOUT;
        } else if (m1422a == 199) {
            enumC0637fr = EnumC0637fr.CHANNEL_TCP_ERR;
        } else {
            if (m1422a == 499) {
                aVar.f1384a = EnumC0637fr.CHANNEL_BOSH_EXCEPTION;
                if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                    enumC0637fr = EnumC0637fr.CHANNEL_BOSH_ITEMNOTFIND;
                }
                if (aVar.f1384a != EnumC0637fr.CHANNEL_TCP_ERR || aVar.f1384a == EnumC0637fr.CHANNEL_XMPPEXCEPTION || aVar.f1384a == EnumC0637fr.CHANNEL_BOSH_EXCEPTION) {
                    aVar.f1385a = str;
                }
                return aVar;
            }
            enumC0637fr = m1422a != 109 ? m1422a != 110 ? EnumC0637fr.CHANNEL_XMPPEXCEPTION : EnumC0637fr.CHANNEL_TCP_BROKEN_PIPE : EnumC0637fr.CHANNEL_TCP_CONNRESET;
        }
        aVar.f1384a = enumC0637fr;
        if (aVar.f1384a != EnumC0637fr.CHANNEL_TCP_ERR) {
        }
        aVar.f1385a = str;
        return aVar;
    }
}
