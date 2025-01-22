package com.sensorsdata.analytics.android.sdk;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/DebugModeSelectDialog.class */
public class DebugModeSelectDialog extends Dialog implements View.OnClickListener {
    private SensorsDataAPI.DebugMode currentDebugMode;
    private OnDebugModeViewClickListener onDebugModeDialogClickListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/DebugModeSelectDialog$OnDebugModeViewClickListener.class */
    public interface OnDebugModeViewClickListener {
        void onCancel(Dialog dialog);

        void setDebugMode(Dialog dialog, SensorsDataAPI.DebugMode debugMode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DebugModeSelectDialog(Context context, SensorsDataAPI.DebugMode debugMode) {
        super(context);
        this.currentDebugMode = debugMode;
    }

    private int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private StateListDrawable getDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(Color.parseColor("#dddddd"));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(-1);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable);
        stateListDrawable.addState(new int[0], gradientDrawable2);
        return stateListDrawable;
    }

    private void initView() {
        ((TextView) findViewById(C0198R.id.sensors_analytics_debug_mode_title)).setText("SDK 调试模式选择");
        TextView textView = (TextView) findViewById(C0198R.id.sensors_analytics_debug_mode_cancel);
        textView.setText("取消");
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(C0198R.id.sensors_analytics_debug_mode_only);
        textView2.setText("开启调试模式（不导入数据）");
        textView2.setOnClickListener(this);
        TextView textView3 = (TextView) findViewById(C0198R.id.sensors_analytics_debug_mode_track);
        textView3.setText("开启调试模式（导入数据）");
        textView3.setOnClickListener(this);
        ((TextView) findViewById(C0198R.id.sensors_analytics_debug_mode_message)).setText(this.currentDebugMode == SensorsDataAPI.DebugMode.DEBUG_ONLY ? "当前为 调试模式（不导入数据）" : this.currentDebugMode == SensorsDataAPI.DebugMode.DEBUG_AND_TRACK ? "当前为 测试模式（导入数据）" : "调试模式已关闭");
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(getDrawable());
            textView2.setBackground(getDrawable());
            textView3.setBackground(getDrawable());
        } else {
            textView.setBackgroundDrawable(getDrawable());
            textView2.setBackgroundDrawable(getDrawable());
            textView3.setBackgroundDrawable(getDrawable());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.onDebugModeDialogClickListener == null) {
            return;
        }
        int id = view.getId();
        if (id == C0198R.id.sensors_analytics_debug_mode_track) {
            this.onDebugModeDialogClickListener.setDebugMode(this, SensorsDataAPI.DebugMode.DEBUG_AND_TRACK);
        } else if (id == C0198R.id.sensors_analytics_debug_mode_only) {
            this.onDebugModeDialogClickListener.setDebugMode(this, SensorsDataAPI.DebugMode.DEBUG_ONLY);
        } else if (id == C0198R.id.sensors_analytics_debug_mode_cancel) {
            this.onDebugModeDialogClickListener.onCancel(this);
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0198R.layout.sensors_analytics_debug_mode_dialog_content);
        initView();
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = dip2px(getContext(), 270.0f);
            attributes.height = dip2px(getContext(), 240.0f);
            window.setAttributes(attributes);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setColor(-1);
            gradientDrawable.setCornerRadius(dip2px(getContext(), 7.0f));
            window.setBackgroundDrawable(gradientDrawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnDebugModeDialogClickListener(OnDebugModeViewClickListener onDebugModeViewClickListener) {
        this.onDebugModeDialogClickListener = onDebugModeViewClickListener;
    }
}
