package com.xiaomi.push.service;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0769m;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.o */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/o.class */
public class C0865o {

    /* renamed from: a */
    private static volatile C0865o f2687a;

    /* renamed from: a */
    private AccountManager f2688a;

    /* renamed from: a */
    private OnAccountsUpdateListener f2689a;

    /* renamed from: a */
    private Context f2690a;

    /* renamed from: a */
    private Object f2691a = new Object();

    /* renamed from: a */
    private ArrayList<a> f2692a;

    /* renamed from: com.xiaomi.push.service.o$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/o$a.class */
    public interface a {
        /* renamed from: a */
        void mo287a(String str, Context context);
    }

    private C0865o(Context context) {
        this.f2690a = context;
        if (C0769m.m2397a(this.f2690a)) {
            this.f2688a = AccountManager.get(this.f2690a);
            this.f2692a = new ArrayList<>();
        }
    }

    /* renamed from: a */
    public static C0865o m2771a(Context context) {
        if (f2687a == null) {
            synchronized (C0865o.class) {
                try {
                    if (f2687a == null) {
                        f2687a = new C0865o(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2687a;
    }

    /* renamed from: a */
    private void m2773a(String str) {
        synchronized (this.f2691a) {
            if (this.f2692a != null && this.f2692a.size() >= 1) {
                Iterator it = new ArrayList(this.f2692a).iterator();
                while (it.hasNext()) {
                    ((a) it.next()).mo287a(str, this.f2690a);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2774a(Account[] accountArr) {
        Account account;
        String str;
        if (accountArr == null || accountArr.length <= 0) {
            return;
        }
        int length = accountArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                account = null;
                break;
            }
            Account account2 = accountArr[i2];
            if (account2 != null && TextUtils.equals("com.xiaomi", account2.type)) {
                account = account2;
                break;
            }
            i = i2 + 1;
        }
        boolean z = false;
        if (account != null) {
            z = false;
            if (!TextUtils.isEmpty(account.name)) {
                z = true;
            }
        }
        boolean m2786a = C0868r.m2782a(this.f2690a).m2786a();
        if (!z || m2786a) {
            if (!z && m2786a) {
                C0868r.m2782a(this.f2690a).m2784a();
                str = "0";
                m2773a(str);
            } else if (!z || !m2786a || TextUtils.equals(C0868r.m2782a(this.f2690a).m2783a(), account.name)) {
                return;
            }
        }
        C0868r.m2782a(this.f2690a).m2785a(account.name);
        str = account.name;
        m2773a(str);
    }

    /* renamed from: b */
    private String m2775b() {
        Account m2396a = C0769m.m2396a(this.f2690a);
        return m2396a == null ? "" : m2396a.name;
    }

    /* renamed from: b */
    private void m2776b() {
        if (this.f2689a != null) {
            return;
        }
        this.f2689a = new C0866p(this);
    }

    /* renamed from: a */
    public String m2777a() {
        String m2775b = m2775b();
        if (TextUtils.isEmpty(m2775b)) {
            C0868r.m2782a(this.f2690a).m2785a("0");
            return "0";
        }
        C0868r.m2782a(this.f2690a).m2785a(m2775b);
        return m2775b;
    }

    /* renamed from: a */
    public void m2778a() {
        OnAccountsUpdateListener onAccountsUpdateListener;
        if (C0769m.m2397a(this.f2690a) && (onAccountsUpdateListener = this.f2689a) != null) {
            this.f2688a.removeOnAccountsUpdatedListener(onAccountsUpdateListener);
        }
    }

    /* renamed from: a */
    public void m2779a(a aVar) {
        synchronized (this.f2691a) {
            if (this.f2692a == null) {
                this.f2692a = new ArrayList<>();
            }
            if (aVar != null) {
                int size = this.f2692a.size();
                this.f2692a.add(aVar);
                if (size == 0 && !m2780a()) {
                    AbstractC0407b.m70a("MIIDManager startMIIDUpdateListener failed cause lack of GET_ACCOUNTS permission");
                }
            }
        }
    }

    /* renamed from: a */
    public boolean m2780a() {
        try {
            if (!C0769m.m2397a(this.f2690a)) {
                return false;
            }
            if (this.f2689a == null) {
                m2776b();
            }
            this.f2688a.addOnAccountsUpdatedListener(this.f2689a, null, true);
            return true;
        } catch (Exception e) {
            AbstractC0407b.m75d(e.toString());
            return false;
        }
    }

    /* renamed from: b */
    public void m2781b(a aVar) {
        synchronized (this.f2691a) {
            if (this.f2692a == null) {
                return;
            }
            if (aVar != null) {
                this.f2692a.remove(aVar);
                if (this.f2692a.size() == 0) {
                    m2778a();
                }
            }
        }
    }
}
