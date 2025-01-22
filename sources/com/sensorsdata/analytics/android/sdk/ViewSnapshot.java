package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64OutputStream;
import android.util.DisplayMetrics;
import android.util.JsonWriter;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

@TargetApi(16)
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ViewSnapshot.class */
public class ViewSnapshot {
    private static final int MAX_CLASS_NAME_CACHE_SIZE = 255;
    private static final String TAG = "SA.Snapshot";
    private final List<PropertyDescription> mProperties;
    private final ResourceIds mResourceIds;
    private String[] mLastImageHashArray = null;
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private final RootViewFinder mRootViewFinder = new RootViewFinder();
    private final ClassNameCache mClassnameCache = new ClassNameCache(255);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ViewSnapshot$CachedBitmap.class */
    public static class CachedBitmap {
        private String mImageHash = "";
        private final Paint mPaint = new Paint(2);
        private Bitmap mCached = null;

        private String toHex(byte[] bArr) {
            String str = "";
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= bArr.length) {
                    return str;
                }
                str = (str + "0123456789ABCDEF".charAt((bArr[i2] >> 4) & 15)) + "0123456789ABCDEF".charAt(bArr[i2] & 15);
                i = i2 + 1;
            }
        }

        public String getImageHash() {
            return this.mImageHash;
        }

        public void recreate(int i, int i2, int i3, Bitmap bitmap) {
            synchronized (this) {
                if (this.mCached == null || this.mCached.getWidth() != i || this.mCached.getHeight() != i2) {
                    try {
                        this.mCached = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
                    } catch (OutOfMemoryError e) {
                        this.mCached = null;
                    }
                    if (this.mCached != null) {
                        this.mCached.setDensity(i3);
                    }
                }
                if (this.mCached != null) {
                    new Canvas(this.mCached).drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        this.mCached.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        this.mImageHash = toHex(MessageDigest.getInstance("MD5").digest(byteArrayOutputStream.toByteArray()));
                    } catch (Exception e2) {
                        SALog.m55i(ViewSnapshot.TAG, "CachedBitmap.recreate;Create image_hash error=" + e2);
                    }
                }
            }
        }

        public void writeBitmapJSON(Bitmap.CompressFormat compressFormat, int i, OutputStream outputStream) throws IOException {
            synchronized (this) {
                if (this.mCached != null && this.mCached.getWidth() != 0 && this.mCached.getHeight() != 0) {
                    outputStream.write(34);
                    Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, 2);
                    this.mCached.compress(Bitmap.CompressFormat.PNG, 100, base64OutputStream);
                    base64OutputStream.flush();
                    outputStream.write(34);
                }
                outputStream.write("null".getBytes());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ViewSnapshot$ClassNameCache.class */
    public static class ClassNameCache extends LruCache<Class<?>, String> {
        public ClassNameCache(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public String create(Class<?> cls) {
            return cls.getCanonicalName();
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ViewSnapshot$RootViewFinder.class */
    private static class RootViewFinder implements Callable<List<RootViewInfo>> {
        private UIThreadSet<Activity> mLiveActivities;
        private final int mClientDensity = 160;
        private final DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        private final List<RootViewInfo> mRootViews = new ArrayList();
        private final CachedBitmap mCachedBitmap = new CachedBitmap();

        private void scaleBitmap(RootViewInfo rootViewInfo, Bitmap bitmap) {
            float f = 1.0f;
            float f2 = 1.0f;
            if (bitmap != null) {
                int density = bitmap.getDensity();
                if (density != 0) {
                    f = 160.0f / density;
                }
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int width2 = (int) ((bitmap.getWidth() * f) + 0.5d);
                int height2 = (int) ((bitmap.getHeight() * f) + 0.5d);
                f2 = f;
                if (width > 0) {
                    f2 = f;
                    if (height > 0) {
                        f2 = f;
                        if (width2 > 0) {
                            f2 = f;
                            if (height2 > 0) {
                                this.mCachedBitmap.recreate(width2, height2, 160, bitmap);
                                f2 = f;
                            }
                        }
                    }
                }
            }
            rootViewInfo.scale = f2;
            rootViewInfo.screenshot = this.mCachedBitmap;
        }

        private void takeScreenshot(RootViewInfo rootViewInfo) {
            View view = rootViewInfo.rootView;
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                    view.draw(new Canvas(createBitmap));
                    scaleBitmap(rootViewInfo, createBitmap);
                    return;
                } catch (RuntimeException e) {
                    SALog.m56i(ViewSnapshot.TAG, "Can't take a bitmap snapshot of view " + view + ", skipping for now.", e);
                    return;
                }
            }
            try {
                Boolean valueOf = Boolean.valueOf(view.isDrawingCacheEnabled());
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache(true);
                scaleBitmap(rootViewInfo, view.getDrawingCache());
                if (valueOf == null || valueOf.booleanValue()) {
                    return;
                }
                view.setDrawingCacheEnabled(false);
            } catch (RuntimeException e2) {
                SALog.m56i(ViewSnapshot.TAG, "Can't take a bitmap snapshot of view " + view + ", skipping for now.", e2);
            }
        }

        @Override // java.util.concurrent.Callable
        public List<RootViewInfo> call() throws Exception {
            this.mRootViews.clear();
            for (Activity activity : this.mLiveActivities.getAll()) {
                View rootView = activity.getWindow().getDecorView().getRootView();
                if (rootView.getWidth() != 0 && rootView.getHeight() != 0) {
                    String canonicalName = activity.getClass().getCanonicalName();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(this.mDisplayMetrics);
                    this.mRootViews.add(new RootViewInfo(canonicalName, rootView));
                }
            }
            int size = this.mRootViews.size();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= size) {
                    return this.mRootViews;
                }
                takeScreenshot(this.mRootViews.get(i2));
                i = i2 + 1;
            }
        }

        public void findInActivities(UIThreadSet<Activity> uIThreadSet) {
            this.mLiveActivities = uIThreadSet;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ViewSnapshot$RootViewInfo.class */
    public static class RootViewInfo {
        public final String activityName;
        public final View rootView;
        public CachedBitmap screenshot = null;
        public float scale = 1.0f;

        public RootViewInfo(String str, View view) {
            this.activityName = str;
            this.rootView = view;
        }
    }

    public ViewSnapshot(List<PropertyDescription> list, ResourceIds resourceIds) {
        this.mProperties = list;
        this.mResourceIds = resourceIds;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b6, code lost:            if ((r6.getParent() instanceof android.widget.ListView) != false) goto L29;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void addProperties(android.util.JsonWriter r5, android.view.View r6) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 469
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.ViewSnapshot.addProperties(android.util.JsonWriter, android.view.View):void");
    }

    private int getChildIndex(ViewParent viewParent, View view) {
        if (!(viewParent instanceof ViewGroup)) {
            return -1;
        }
        ViewGroup viewGroup = (ViewGroup) viewParent;
        String resName = getResName(view);
        String str = this.mClassnameCache.get(view.getClass());
        int i = 0;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (Pathfinder.hasClassName(childAt, str)) {
                String resName2 = getResName(childAt);
                if ((resName == null || resName.equals(resName2)) && childAt == view) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    private String getResName(View view) {
        int id = view.getId();
        if (-1 == id) {
            return null;
        }
        return this.mResourceIds.nameForId(id);
    }

    private boolean isAssignableFromClass(View view, String str) {
        try {
            if (Class.forName(str).isAssignableFrom(view.getClass())) {
                return true;
            }
            Object parent = view.getParent();
            if (parent != null && (parent instanceof View)) {
                return isAssignableFromClass((View) parent, str);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isForbiddenClick(View view) {
        if ((view instanceof ListView) || (view instanceof Spinner) || (view instanceof EditText) || isNavigationMenuItemView(view)) {
            return true;
        }
        return isOtherForbiddenClick(view);
    }

    private boolean isNavigationMenuItemView(View view) {
        try {
            return Class.forName("android.support.design.internal.NavigationMenuItemView").isAssignableFrom(view.getClass());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isOtherForbiddenClick(View view) {
        if (isAssignableFromClass(view, "android.support.v7.widget.Toolbar")) {
            return true;
        }
        return isAssignableFromClass(view, "android.support.design.widget.TabLayout");
    }

    private boolean isSnapShotUpdated(String str) {
        String[] strArr;
        if (str == null || str.length() <= 0 || (strArr = this.mLastImageHashArray) == null || strArr.length <= 0) {
            return true;
        }
        int length = strArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return true;
            }
            if (strArr[i2].equals(str)) {
                return false;
            }
            i = i2 + 1;
        }
    }

    private void snapshotView(JsonWriter jsonWriter, View view) throws IOException {
        float f;
        Class<? super Object> superclass;
        jsonWriter.beginObject();
        jsonWriter.name("hashCode").value(view.hashCode());
        jsonWriter.name("id").value(view.getId());
        jsonWriter.name("index").value(getChildIndex(view.getParent(), view));
        jsonWriter.name("sa_id_name").value(getResName(view));
        try {
            String str = (String) view.getTag(C0198R.id.sensors_analytics_tag_view_id);
            if (!TextUtils.isEmpty(str)) {
                jsonWriter.name("sa_id_name").value(str);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        jsonWriter.name("top").value(view.getTop());
        jsonWriter.name("left").value(view.getLeft());
        jsonWriter.name(SettingsJsonConstants.ICON_WIDTH_KEY).value(view.getWidth());
        jsonWriter.name(SettingsJsonConstants.ICON_HEIGHT_KEY).value(view.getHeight());
        jsonWriter.name("scrollX").value(view.getScrollX());
        jsonWriter.name("scrollY").value(view.getScrollY());
        jsonWriter.name("visibility").value(view.getVisibility());
        float f2 = 0.0f;
        if (Build.VERSION.SDK_INT >= 11) {
            f2 = view.getTranslationX();
            f = view.getTranslationY();
        } else {
            f = 0.0f;
        }
        jsonWriter.name("translationX").value(f2);
        jsonWriter.name("translationY").value(f);
        jsonWriter.name("classes");
        jsonWriter.beginArray();
        Class<?> cls = view.getClass();
        do {
            jsonWriter.value(this.mClassnameCache.get(cls));
            superclass = cls.getSuperclass();
            if (superclass == Object.class) {
                break;
            } else {
                cls = superclass;
            }
        } while (superclass != null);
        jsonWriter.endArray();
        addProperties(jsonWriter, view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            int[] rules = ((RelativeLayout.LayoutParams) layoutParams).getRules();
            jsonWriter.name("layoutRules");
            jsonWriter.beginArray();
            int length = rules.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                jsonWriter.value(rules[i2]);
                i = i2 + 1;
            }
            jsonWriter.endArray();
        }
        jsonWriter.name("subviews");
        jsonWriter.beginArray();
        boolean z = view instanceof ViewGroup;
        if (z) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= childCount) {
                    break;
                }
                if (viewGroup.getChildAt(i4) != null) {
                    jsonWriter.value(r0.hashCode());
                }
                i3 = i4 + 1;
            }
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
        if (!z) {
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) view;
        int childCount2 = viewGroup2.getChildCount();
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= childCount2) {
                return;
            }
            View childAt = viewGroup2.getChildAt(i6);
            if (childAt != null) {
                snapshotView(jsonWriter, childAt);
            }
            i5 = i6 + 1;
        }
    }

    private void snapshotViewHierarchy(JsonWriter jsonWriter, View view) throws IOException {
        jsonWriter.beginArray();
        snapshotView(jsonWriter, view);
        jsonWriter.endArray();
    }

    public String snapshots(UIThreadSet<Activity> uIThreadSet, OutputStream outputStream) throws IOException {
        this.mRootViewFinder.findInActivities(uIThreadSet);
        FutureTask futureTask = new FutureTask(this.mRootViewFinder);
        this.mMainThreadHandler.post(futureTask);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        List emptyList = Collections.emptyList();
        outputStreamWriter.write("[");
        try {
            emptyList = (List) futureTask.get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            SALog.m56i(TAG, "Screenshot interrupted, no screenshot will be sent.", e);
        } catch (ExecutionException e2) {
            SALog.m56i(TAG, "Exception thrown during screenshot attempt", e2);
        } catch (TimeoutException e3) {
            SALog.m56i(TAG, "Screenshot took more than 1 second to be scheduled and executed. No screenshot will be sent.", e3);
        }
        int size = emptyList.size();
        String str = null;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                outputStreamWriter.write("]");
                outputStreamWriter.flush();
                return str;
            }
            RootViewInfo rootViewInfo = (RootViewInfo) emptyList.get(i2);
            if (i2 > 0) {
                outputStreamWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            if (isSnapShotUpdated(rootViewInfo.screenshot.getImageHash())) {
                outputStreamWriter.write("{");
                outputStreamWriter.write("\"activity\":");
                str = rootViewInfo.activityName;
                outputStreamWriter.write(JSONObject.quote(rootViewInfo.activityName));
                outputStreamWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
                outputStreamWriter.write("\"scale\":");
                outputStreamWriter.write(String.format("%s", Float.valueOf(rootViewInfo.scale)));
                outputStreamWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
                outputStreamWriter.write("\"serialized_objects\":");
                JsonWriter jsonWriter = new JsonWriter(outputStreamWriter);
                jsonWriter.beginObject();
                jsonWriter.name("rootObject").value(rootViewInfo.rootView.hashCode());
                jsonWriter.name("objects");
                snapshotViewHierarchy(jsonWriter, rootViewInfo.rootView);
                jsonWriter.endObject();
                jsonWriter.flush();
                outputStreamWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
                outputStreamWriter.write("\"image_hash\":");
                outputStreamWriter.write(JSONObject.quote(rootViewInfo.screenshot.getImageHash()));
                outputStreamWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
                outputStreamWriter.write("\"screenshot\":");
                outputStreamWriter.flush();
                rootViewInfo.screenshot.writeBitmapJSON(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStreamWriter.write("}");
            } else {
                outputStreamWriter.write("{}");
            }
            i = i2 + 1;
        }
    }

    public void updateLastImageHashArray(String str) {
        if (str == null || str.length() <= 0) {
            this.mLastImageHashArray = null;
        } else {
            this.mLastImageHashArray = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
    }
}
