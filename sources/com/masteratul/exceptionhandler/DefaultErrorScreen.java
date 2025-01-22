package com.masteratul.exceptionhandler;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import com.stub.StubApp;

/* loaded from: classes08-dex2jar.jar:com/masteratul/exceptionhandler/DefaultErrorScreen.class */
public class DefaultErrorScreen extends Activity {
    private static String TAG = "RN_ERROR_HANDLER";
    private Button quitButton;
    private Button relaunchButton;
    private Button showDetailsButton;
    private TextView stackTraceView;

    /* renamed from: com.masteratul.exceptionhandler.DefaultErrorScreen$1 */
    /* loaded from: classes08-dex2jar.jar:com/masteratul/exceptionhandler/DefaultErrorScreen$1.class */
    class ViewOnClickListenerC01491 implements View.OnClickListener {
        ViewOnClickListenerC01491() {
        }

        @Override // android.view.View.OnClickListener
        @SensorsDataInstrumented
        public void onClick(View view) {
            if (DefaultErrorScreen.this.stackTraceView.getVisibility() == 0) {
                DefaultErrorScreen.this.stackTraceView.setVisibility(8);
                DefaultErrorScreen.this.showDetailsButton.setText("SHOW DETAILS");
            } else {
                DefaultErrorScreen.this.stackTraceView.setVisibility(0);
                DefaultErrorScreen.this.showDetailsButton.setText("HIDE DETAILS");
            }
            SensorsDataAutoTrackHelper.trackViewOnClick(view);
        }
    }

    /* renamed from: com.masteratul.exceptionhandler.DefaultErrorScreen$2 */
    /* loaded from: classes08-dex2jar.jar:com/masteratul/exceptionhandler/DefaultErrorScreen$2.class */
    class ViewOnClickListenerC01502 implements View.OnClickListener {
        ViewOnClickListenerC01502() {
        }

        @Override // android.view.View.OnClickListener
        @SensorsDataInstrumented
        public void onClick(View view) {
            DefaultErrorScreen.doRestart(StubApp.getOrigApplicationContext(DefaultErrorScreen.this.getApplicationContext()));
            SensorsDataAutoTrackHelper.trackViewOnClick(view);
        }
    }

    /* renamed from: com.masteratul.exceptionhandler.DefaultErrorScreen$3 */
    /* loaded from: classes08-dex2jar.jar:com/masteratul/exceptionhandler/DefaultErrorScreen$3.class */
    class ViewOnClickListenerC01513 implements View.OnClickListener {
        ViewOnClickListenerC01513() {
        }

        @Override // android.view.View.OnClickListener
        @SensorsDataInstrumented
        public void onClick(View view) {
            System.exit(0);
            SensorsDataAutoTrackHelper.trackViewOnClick(view);
        }
    }

    static {
        StubApp.interface11(10670);
    }

    public static void doRestart(Context context) {
        try {
            if (context == null) {
                throw new Exception("Was not able to restart application, Context null");
            }
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                throw new Exception("Was not able to restart application, PM null");
            }
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null) {
                throw new Exception("Was not able to restart application, mStartActivity null");
            }
            launchIntentForPackage.addFlags(67108864);
            ((AlarmManager) context.getSystemService("alarm")).set(1, System.currentTimeMillis() + 100, PendingIntent.getActivity(context, 654311, launchIntentForPackage, 268435456));
            System.exit(0);
        } catch (Exception e) {
            Log.e(TAG, "Was not able to restart application");
        }
    }

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);
}
