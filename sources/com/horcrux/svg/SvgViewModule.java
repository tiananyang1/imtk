package com.horcrux.svg;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import javax.annotation.Nonnull;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/SvgViewModule.class */
class SvgViewModule extends ReactContextBaseJavaModule {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.SvgViewModule$1 */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/SvgViewModule$1.class */
    public static final class RunnableC01091 implements Runnable {
        final /* synthetic */ int val$attempt;
        final /* synthetic */ ReadableMap val$options;
        final /* synthetic */ Callback val$successCallback;
        final /* synthetic */ int val$tag;

        RunnableC01091(int i, ReadableMap readableMap, Callback callback, int i2) {
            this.val$tag = i;
            this.val$options = readableMap;
            this.val$successCallback = callback;
            this.val$attempt = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            SvgView svgViewByTag = SvgViewManager.getSvgViewByTag(this.val$tag);
            if (svgViewByTag == null) {
                SvgViewManager.runWhenViewIsAvailable(this.val$tag, new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SvgView svgViewByTag2 = SvgViewManager.getSvgViewByTag(RunnableC01091.this.val$tag);
                        if (svgViewByTag2 == null) {
                            return;
                        }
                        svgViewByTag2.setToDataUrlTask(new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                SvgViewModule.toDataURL(RunnableC01091.this.val$tag, RunnableC01091.this.val$options, RunnableC01091.this.val$successCallback, RunnableC01091.this.val$attempt + 1);
                            }
                        });
                    }
                });
                return;
            }
            if (svgViewByTag.notRendered()) {
                svgViewByTag.setToDataUrlTask(new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SvgViewModule.toDataURL(RunnableC01091.this.val$tag, RunnableC01091.this.val$options, RunnableC01091.this.val$successCallback, RunnableC01091.this.val$attempt + 1);
                    }
                });
                return;
            }
            ReadableMap readableMap = this.val$options;
            if (readableMap != null) {
                this.val$successCallback.invoke(new Object[]{svgViewByTag.toDataURL(readableMap.getInt(SettingsJsonConstants.ICON_WIDTH_KEY), this.val$options.getInt(SettingsJsonConstants.ICON_HEIGHT_KEY))});
            } else {
                this.val$successCallback.invoke(new Object[]{svgViewByTag.toDataURL()});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SvgViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void toDataURL(int i, ReadableMap readableMap, Callback callback, int i2) {
        UiThreadUtil.runOnUiThread(new RunnableC01091(i, readableMap, callback, i2));
    }

    @Nonnull
    public String getName() {
        return "RNSVGSvgViewManager";
    }

    @ReactMethod
    public void toDataURL(int i, ReadableMap readableMap, Callback callback) {
        toDataURL(i, readableMap, callback, 0);
    }
}
