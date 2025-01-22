package com.xiaomi.push;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ck */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ck.class */
public class AsyncTaskC0549ck extends AsyncTask {

    /* renamed from: a */
    final /* synthetic */ C0552cn f588a;

    /* renamed from: a */
    private boolean f589a;

    private AsyncTaskC0549ck(C0552cn c0552cn) {
        this.f588a = c0552cn;
        this.f589a = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ AsyncTaskC0549ck(C0552cn c0552cn, HandlerC0540cb handlerC0540cb) {
        this(c0552cn);
    }

    /* renamed from: a */
    private String m775a(String str) {
        String m622a = C0525bn.m622a(str, null);
        String str2 = null;
        if (m622a != null) {
            try {
                str2 = new JSONObject(m622a).getString("real-ip");
            } catch (JSONException e) {
                return null;
            }
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(String... strArr) {
        if (!this.f589a) {
            return null;
        }
        try {
            return m775a(strArr[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        if (this.f589a) {
            C0552cn.m785a(this.f588a).sendMessage(C0552cn.m785a(this.f588a).obtainMessage(3, str));
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        this.f589a = false;
    }
}
