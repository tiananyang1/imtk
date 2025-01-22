package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.ba */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ba.class */
public class HandlerC0451ba extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0449az f325a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0451ba(C0449az c0449az, Looper looper) {
        super(looper);
        this.f325a = c0449az;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        C0449az c0449az;
        EnumC0455be enumC0455be;
        Context context5;
        HashMap<String, String> m343a;
        Context context6;
        Context context7;
        Context context8;
        Context context9;
        Context context10;
        C0449az c0449az2;
        EnumC0455be enumC0455be2;
        Context context11;
        Context context12;
        if (message.what != 19) {
            return;
        }
        String str = (String) message.obj;
        int i = message.arg1;
        synchronized (C0439ap.class) {
            try {
                context = this.f325a.f312a;
                if (C0439ap.m186a(context).m191a(str)) {
                    context2 = this.f325a.f312a;
                    if (C0439ap.m186a(context2).m187a(str) < 10) {
                        if (EnumC0455be.DISABLE_PUSH.ordinal() == i) {
                            context12 = this.f325a.f312a;
                            if ("syncing".equals(C0439ap.m186a(context12).m188a(EnumC0455be.DISABLE_PUSH))) {
                                c0449az2 = this.f325a;
                                enumC0455be2 = EnumC0455be.DISABLE_PUSH;
                                c0449az2.m230a(str, enumC0455be2, true, (HashMap<String, String>) null);
                                context11 = this.f325a.f312a;
                                C0439ap.m186a(context11).m192b(str);
                            }
                        }
                        if (EnumC0455be.ENABLE_PUSH.ordinal() == i) {
                            context10 = this.f325a.f312a;
                            if ("syncing".equals(C0439ap.m186a(context10).m188a(EnumC0455be.ENABLE_PUSH))) {
                                c0449az2 = this.f325a;
                                enumC0455be2 = EnumC0455be.ENABLE_PUSH;
                                c0449az2.m230a(str, enumC0455be2, true, (HashMap<String, String>) null);
                                context11 = this.f325a.f312a;
                                C0439ap.m186a(context11).m192b(str);
                            }
                        }
                        if (EnumC0455be.UPLOAD_HUAWEI_TOKEN.ordinal() == i) {
                            context8 = this.f325a.f312a;
                            if ("syncing".equals(C0439ap.m186a(context8).m188a(EnumC0455be.UPLOAD_HUAWEI_TOKEN))) {
                                c0449az = this.f325a;
                                enumC0455be = EnumC0455be.UPLOAD_HUAWEI_TOKEN;
                                context9 = this.f325a.f312a;
                                m343a = C0466i.m343a(context9, EnumC0463f.ASSEMBLE_PUSH_HUAWEI);
                                c0449az.m230a(str, enumC0455be, false, (HashMap<String, String>) m343a);
                                context11 = this.f325a.f312a;
                                C0439ap.m186a(context11).m192b(str);
                            }
                        }
                        if (EnumC0455be.UPLOAD_FCM_TOKEN.ordinal() == i) {
                            context6 = this.f325a.f312a;
                            if ("syncing".equals(C0439ap.m186a(context6).m188a(EnumC0455be.UPLOAD_FCM_TOKEN))) {
                                c0449az = this.f325a;
                                enumC0455be = EnumC0455be.UPLOAD_FCM_TOKEN;
                                context7 = this.f325a.f312a;
                                m343a = C0466i.m343a(context7, EnumC0463f.ASSEMBLE_PUSH_FCM);
                                c0449az.m230a(str, enumC0455be, false, (HashMap<String, String>) m343a);
                                context11 = this.f325a.f312a;
                                C0439ap.m186a(context11).m192b(str);
                            }
                        }
                        if (EnumC0455be.UPLOAD_COS_TOKEN.ordinal() == i) {
                            context4 = this.f325a.f312a;
                            if ("syncing".equals(C0439ap.m186a(context4).m188a(EnumC0455be.UPLOAD_COS_TOKEN))) {
                                c0449az = this.f325a;
                                enumC0455be = EnumC0455be.UPLOAD_COS_TOKEN;
                                context5 = this.f325a.f312a;
                                m343a = C0466i.m343a(context5, EnumC0463f.ASSEMBLE_PUSH_COS);
                                c0449az.m230a(str, enumC0455be, false, (HashMap<String, String>) m343a);
                            }
                        }
                        context11 = this.f325a.f312a;
                        C0439ap.m186a(context11).m192b(str);
                    } else {
                        context3 = this.f325a.f312a;
                        C0439ap.m186a(context3).m193c(str);
                    }
                }
            } finally {
            }
        }
    }
}
