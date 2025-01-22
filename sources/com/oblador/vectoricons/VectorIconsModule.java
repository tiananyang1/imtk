package com.oblador.vectoricons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.views.text.ReactFontManager;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/oblador/vectoricons/VectorIconsModule.class */
public class VectorIconsModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "RNVectorIconsModule";
    private static final Map<String, Typeface> sTypefaceCache = new HashMap();

    public VectorIconsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getImageForFont(String str, String str2, Integer num, Integer num2, Callback callback) {
        FileOutputStream fileOutputStream;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        String str3 = reactApplicationContext.getCacheDir().getAbsolutePath() + "/";
        float f = reactApplicationContext.getResources().getDisplayMetrics().density;
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        int i = (int) f;
        sb.append(f == ((float) i) ? Integer.toString(i) : Float.toString(f));
        sb.append("x");
        String sb2 = sb.toString();
        int round = Math.round(num.intValue() * f);
        String str4 = str3 + Integer.toString((str + Constants.COLON_SEPARATOR + str2 + Constants.COLON_SEPARATOR + num2).hashCode(), 32) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + Integer.toString(num.intValue()) + sb2 + ".png";
        String str5 = "file://" + str4;
        File file = new File(str4);
        if (file.exists()) {
            callback.invoke(new Object[]{null, str5});
            return;
        }
        Typeface typeface = ReactFontManager.getInstance().getTypeface(str, 0, reactApplicationContext.getAssets());
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setColor(num2.intValue());
        paint.setTextSize(round);
        paint.setAntiAlias(true);
        Rect rect = new Rect();
        paint.getTextBounds(str2, 0, str2.length(), rect);
        Bitmap createBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawText(str2, -rect.left, -rect.top, paint);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e = e;
                    fileOutputStream = null;
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = null;
                }
                try {
                    createBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    callback.invoke(new Object[]{null, str5});
                } catch (FileNotFoundException e3) {
                    e = e3;
                    callback.invoke(new Object[]{e.getMessage()});
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e4) {
                    e = e4;
                    callback.invoke(new Object[]{e.getMessage()});
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th) {
                    fileOutputStream2 = fileOutputStream;
                    th = th;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    public String getName() {
        return REACT_CLASS;
    }
}
