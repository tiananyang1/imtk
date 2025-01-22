package com.xiaomi.clientreport.manager;

import android.content.Context;
import android.os.Process;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.processor.C0413a;
import com.xiaomi.clientreport.processor.C0414b;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.push.C0646g;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/manager/ClientReportClient.class */
public class ClientReportClient {
    public static void init(Context context) {
        init(context, Config.defaultConfig(context), new C0413a(context), new C0414b(context));
    }

    public static void init(Context context, Config config) {
        init(context, config, new C0413a(context), new C0414b(context));
    }

    public static void init(Context context, Config config, IEventProcessor iEventProcessor, IPerfProcessor iPerfProcessor) {
        AbstractC0407b.m74c("init in process " + C0646g.m1358a(context) + " pid :" + Process.myPid() + " threadId: " + Thread.currentThread().getId());
        C0410a.m77a(context).m86a(config, iEventProcessor, iPerfProcessor);
        if (C0646g.m1361a(context)) {
            AbstractC0407b.m74c("init in process\u3000start scheduleJob");
            C0410a.m77a(context).m85a();
        }
    }

    public static void reportEvent(Context context, EventClientReport eventClientReport) {
        if (eventClientReport != null) {
            C0410a.m77a(context).m87a(eventClientReport);
        }
    }

    public static void reportPerf(Context context, PerfClientReport perfClientReport) {
        if (perfClientReport != null) {
            C0410a.m77a(context).m88a(perfClientReport);
        }
    }

    public static void updateConfig(Context context, Config config) {
        if (config == null) {
            return;
        }
        C0410a.m77a(context).m89a(config.isEventUploadSwitchOpen(), config.isPerfUploadSwitchOpen(), config.getEventUploadFrequency(), config.getPerfUploadFrequency());
    }
}
