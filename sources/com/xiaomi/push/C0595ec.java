package com.xiaomi.push;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import com.xiaomi.mipush.sdk.Constants;

/* renamed from: com.xiaomi.push.ec */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ec.class */
public class C0595ec extends AbstractC0601ei {
    public C0595ec(Context context, int i) {
        super(context, i);
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 7;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.Account;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        StringBuilder sb = new StringBuilder();
        try {
            Account[] accounts = AccountManager.get(this.f733a).getAccounts();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= Math.min(accounts.length, 10)) {
                    break;
                }
                Account account = accounts[i2];
                if (i2 > 0) {
                    sb.append(";");
                }
                sb.append(account.name);
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(account.type);
                i = i2 + 1;
            }
        } catch (Throwable th) {
        }
        return sb.toString();
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    protected boolean mo976a() {
        return this.f733a.getPackageManager().checkPermission("android.permission.GET_ACCOUNTS", this.f733a.getPackageName()) == 0;
    }
}
