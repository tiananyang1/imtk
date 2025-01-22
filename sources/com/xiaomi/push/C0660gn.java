package com.xiaomi.push;

import java.io.PrintStream;
import java.io.PrintWriter;

/* renamed from: com.xiaomi.push.gn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gn.class */
public class C0660gn extends Exception {

    /* renamed from: a */
    private C0669gw f1107a;

    /* renamed from: a */
    private C0670gx f1108a;

    /* renamed from: a */
    private Throwable f1109a;

    public C0660gn() {
        this.f1107a = null;
        this.f1108a = null;
        this.f1109a = null;
    }

    public C0660gn(C0669gw c0669gw) {
        this.f1107a = null;
        this.f1108a = null;
        this.f1109a = null;
        this.f1107a = c0669gw;
    }

    public C0660gn(String str) {
        super(str);
        this.f1107a = null;
        this.f1108a = null;
        this.f1109a = null;
    }

    public C0660gn(String str, Throwable th) {
        super(str);
        this.f1107a = null;
        this.f1108a = null;
        this.f1109a = null;
        this.f1109a = th;
    }

    public C0660gn(Throwable th) {
        this.f1107a = null;
        this.f1108a = null;
        this.f1109a = null;
        this.f1109a = th;
    }

    /* renamed from: a */
    public Throwable m1442a() {
        return this.f1109a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        C0670gx c0670gx;
        String message = super.getMessage();
        if (message == null && (c0670gx = this.f1108a) != null) {
            return c0670gx.toString();
        }
        String str = message;
        if (message == null) {
            C0669gw c0669gw = this.f1107a;
            str = message;
            if (c0669gw != null) {
                str = c0669gw.toString();
            }
        }
        return str;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.f1109a != null) {
            printStream.println("Nested Exception: ");
            this.f1109a.printStackTrace(printStream);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.f1109a != null) {
            printWriter.println("Nested Exception: ");
            this.f1109a.printStackTrace(printWriter);
        }
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(": ");
        }
        C0670gx c0670gx = this.f1108a;
        if (c0670gx != null) {
            sb.append(c0670gx);
        }
        C0669gw c0669gw = this.f1107a;
        if (c0669gw != null) {
            sb.append(c0669gw);
        }
        if (this.f1109a != null) {
            sb.append("\n  -- caused by: ");
            sb.append(this.f1109a);
        }
        return sb.toString();
    }
}
