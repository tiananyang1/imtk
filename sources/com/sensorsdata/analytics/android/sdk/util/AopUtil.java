package com.sensorsdata.analytics.android.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.C0198R;
import com.sensorsdata.analytics.android.sdk.Pathfinder;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.ScreenAutoTracker;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/AopUtil.class */
public class AopUtil {
    private static ArrayList<String> sOSViewPackage = new ArrayList<String>() { // from class: com.sensorsdata.analytics.android.sdk.util.AopUtil.1
        {
            add("android##widget");
            add("android##support##v7##widget");
            add("android##support##design##widget");
            add("android##support##text##emoji##widget");
            add("androidx##appcompat##widget");
            add("androidx##emoji##widget");
            add("androidx##cardview##widget");
            add("com##google##android##material");
        }
    };

    public static void addViewPathProperties(Activity activity, View view, JSONObject jSONObject) {
        ViewParent parent;
        try {
            if (!SensorsDataAPI.sharedInstance().isHeatMapEnabled() && !SensorsDataAPI.sharedInstance().isVisualizedAutoTrackEnabled()) {
                return;
            }
            if ((activity != null && !SensorsDataAPI.sharedInstance().isHeatMapActivity(activity.getClass()) && !SensorsDataAPI.sharedInstance().isVisualizedAutoTrackActivity(activity.getClass())) || view == null) {
                return;
            }
            JSONObject jSONObject2 = jSONObject;
            if (jSONObject == null) {
                jSONObject2 = new JSONObject();
            }
            ArrayList arrayList = new ArrayList();
            do {
                parent = view.getParent();
                arrayList.add(view.getClass().getCanonicalName() + "[" + getChildIndex(parent, view) + "]");
                if (parent instanceof ViewGroup) {
                    view = (ViewGroup) parent;
                }
            } while (parent instanceof ViewGroup);
            Collections.reverse(arrayList);
            StringBuilder sb = new StringBuilder();
            int i = 1;
            while (true) {
                int i2 = i;
                if (i2 >= arrayList.size()) {
                    jSONObject2.put("$element_selector", sb.toString());
                    return;
                }
                sb.append((String) arrayList.get(i2));
                if (i2 != arrayList.size() - 1) {
                    sb.append("/");
                }
                i = i2 + 1;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static JSONObject buildTitleAndScreenName(Activity activity) {
        JSONObject trackProperties;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
            String activityTitle = getActivityTitle(activity);
            if (!TextUtils.isEmpty(activityTitle)) {
                jSONObject.put(AopConstants.TITLE, activityTitle);
            }
            if ((activity instanceof ScreenAutoTracker) && (trackProperties = ((ScreenAutoTracker) activity).getTrackProperties()) != null) {
                if (trackProperties.has(AopConstants.SCREEN_NAME)) {
                    jSONObject.put(AopConstants.SCREEN_NAME, trackProperties.optString(AopConstants.SCREEN_NAME));
                }
                if (trackProperties.has(AopConstants.TITLE)) {
                    jSONObject.put(AopConstants.TITLE, trackProperties.optString(AopConstants.TITLE));
                }
            }
            return jSONObject;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return new JSONObject();
        }
    }

    public static Activity getActivityFromContext(Context context, View view) {
        Object tag;
        Activity activity;
        if (context == null) {
            return null;
        }
        try {
            if (context instanceof Activity) {
                activity = (Activity) context;
            } else if (context instanceof ContextWrapper) {
                while (!(context instanceof Activity) && (context instanceof ContextWrapper)) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (!(context instanceof Activity)) {
                    return null;
                }
                activity = (Activity) context;
            } else {
                if (view == null || (tag = view.getTag(C0198R.id.sensors_analytics_tag_view_activity)) == null || !(tag instanceof Activity)) {
                    return null;
                }
                activity = (Activity) tag;
            }
            return activity;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return null;
        }
    }

    public static Activity getActivityFromFragment(Object obj) {
        Activity activity = null;
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                Method method = obj.getClass().getMethod("getActivity", new Class[0]);
                activity = null;
                if (method != null) {
                    activity = (Activity) method.invoke(obj, new Object[0]);
                }
            } catch (Exception e) {
                return null;
            }
        }
        return activity;
    }

    private static String getActivityTitle(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            String charSequence = !TextUtils.isEmpty(activity.getTitle()) ? activity.getTitle().toString() : null;
            String str = charSequence;
            if (Build.VERSION.SDK_INT >= 11) {
                String toolbarTitle = SensorsDataUtils.getToolbarTitle(activity);
                str = charSequence;
                if (!TextUtils.isEmpty(toolbarTitle)) {
                    str = toolbarTitle;
                }
            }
            String str2 = str;
            if (TextUtils.isEmpty(str)) {
                PackageManager packageManager = activity.getPackageManager();
                str2 = str;
                if (packageManager != null) {
                    ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 0);
                    str2 = str;
                    if (!TextUtils.isEmpty(activityInfo.loadLabel(packageManager))) {
                        str2 = activityInfo.loadLabel(packageManager).toString();
                    }
                }
            }
            return str2;
        } catch (Exception e) {
            return null;
        }
    }

    private static int getChildIndex(ViewParent viewParent, View view) {
        try {
            if (!(viewParent instanceof ViewGroup)) {
                return -1;
            }
            ViewGroup viewGroup = (ViewGroup) viewParent;
            String viewId = getViewId(view);
            String canonicalName = view.getClass().getCanonicalName();
            int i = 0;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (Pathfinder.hasClassName(childAt, canonicalName)) {
                    String viewId2 = getViewId(childAt);
                    if ((viewId == null || viewId.equals(viewId2)) && childAt == view) {
                        return i;
                    }
                    i++;
                }
            }
            return -1;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return -1;
        }
    }

    private static Class<?> getClassByName(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static String getCompoundButtonText(View view) {
        try {
            return (String) (((CompoundButton) view).isChecked() ? view.getClass().getMethod("getTextOn", new Class[0]) : view.getClass().getMethod("getTextOff", new Class[0])).invoke(view, new Object[0]);
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    public static void getFragmentNameFromView(View view, JSONObject jSONObject, Activity activity) {
        Object newInstance;
        if (view != null) {
            try {
                String str = (String) view.getTag(C0198R.id.sensors_analytics_tag_view_fragment_name);
                String str2 = (String) view.getTag(C0198R.id.sensors_analytics_tag_view_fragment_name2);
                if (TextUtils.isEmpty(str2)) {
                    str2 = str;
                }
                if (TextUtils.isEmpty(str2) || (newInstance = Class.forName(str2).newInstance()) == null) {
                    return;
                }
                getScreenNameAndTitleFromFragment(jSONObject, newInstance, activity);
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a9, code lost:            if (r0 != false) goto L31;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void getScreenNameAndTitleFromFragment(org.json.JSONObject r7, java.lang.Object r8, android.app.Activity r9) {
        /*
            Method dump skipped, instructions count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.util.AopUtil.getScreenNameAndTitleFromFragment(org.json.JSONObject, java.lang.Object, android.app.Activity):void");
    }

    public static String getViewGroupTypeByReflect(View view) {
        String canonicalName = view.getClass().getCanonicalName();
        Class<?> classByName = getClassByName("android.support.v7.widget.CardView");
        if (classByName != null && classByName.isInstance(view)) {
            return getViewType(canonicalName, "CardView");
        }
        Class<?> classByName2 = getClassByName("androidx.cardview.widget.CardView");
        if (classByName2 != null && classByName2.isInstance(view)) {
            return getViewType(canonicalName, "CardView");
        }
        Class<?> classByName3 = getClassByName("android.support.design.widget.NavigationView");
        if (classByName3 != null && classByName3.isInstance(view)) {
            return getViewType(canonicalName, "NavigationView");
        }
        Class<?> classByName4 = getClassByName("com.google.android.material.navigation.NavigationView");
        return (classByName4 == null || !classByName4.isInstance(view)) ? canonicalName : getViewType(canonicalName, "NavigationView");
    }

    public static String getViewId(View view) {
        String str;
        String str2;
        try {
            str2 = (String) view.getTag(C0198R.id.sensors_analytics_tag_view_id);
            str = str2;
        } catch (Exception e) {
            str = null;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                str = str2;
                if (view.getId() != -1) {
                    return view.getContext().getResources().getResourceEntryName(view.getId());
                }
            }
            return str;
        } catch (Exception e2) {
            return str2;
        }
    }

    public static String getViewText(View view) {
        Class<?> cls;
        CharSequence contentDescription;
        if (view instanceof EditText) {
            return "";
        }
        try {
            cls = Class.forName("android.support.v7.widget.SwitchCompat");
        } catch (Exception e) {
            cls = null;
        }
        Class<?> cls2 = cls;
        if (cls == null) {
            try {
                cls2 = Class.forName("androidx.appcompat.widget.SwitchCompat");
            } catch (Exception e2) {
                cls2 = cls;
            }
        }
        try {
            if (view instanceof CheckBox) {
                contentDescription = ((CheckBox) view).getText();
            } else if (cls2 != null && cls2.isInstance(view)) {
                contentDescription = (String) (((CompoundButton) view).isChecked() ? view.getClass().getMethod("getTextOn", new Class[0]) : view.getClass().getMethod("getTextOff", new Class[0])).invoke(view, new Object[0]);
            } else if (view instanceof RadioButton) {
                contentDescription = ((RadioButton) view).getText();
            } else if (view instanceof ToggleButton) {
                ToggleButton toggleButton = (ToggleButton) view;
                contentDescription = toggleButton.isChecked() ? toggleButton.getTextOn() : toggleButton.getTextOff();
            } else if (view instanceof Button) {
                contentDescription = ((Button) view).getText();
            } else if (view instanceof CheckedTextView) {
                contentDescription = ((CheckedTextView) view).getText();
            } else if (view instanceof TextView) {
                contentDescription = ((TextView) view).getText();
            } else if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                contentDescription = null;
                if (!TextUtils.isEmpty(imageView.getContentDescription())) {
                    contentDescription = imageView.getContentDescription().toString();
                }
            } else {
                contentDescription = view.getContentDescription();
            }
            CharSequence charSequence = contentDescription;
            if (TextUtils.isEmpty(contentDescription)) {
                charSequence = contentDescription;
                if (view instanceof TextView) {
                    charSequence = ((TextView) view).getHint();
                }
            }
            return !TextUtils.isEmpty(charSequence) ? charSequence.toString() : "";
        } catch (Exception e3) {
            SALog.printStackTrace(e3);
            return "";
        }
    }

    public static String getViewType(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (!TextUtils.isEmpty(str2) && isOSViewByPackage(str)) {
            return str2;
        }
        return str;
    }

    public static String getViewTypeByReflect(View view) {
        String canonicalName = view.getClass().getCanonicalName();
        Class<?> classByName = getClassByName("android.widget.Switch");
        if (classByName != null && classByName.isInstance(view)) {
            return getViewType(canonicalName, "Switch");
        }
        Class<?> classByName2 = getClassByName("android.support.v7.widget.SwitchCompat");
        if (classByName2 != null && classByName2.isInstance(view)) {
            return getViewType(canonicalName, "SwitchCompat");
        }
        Class<?> classByName3 = getClassByName("androidx.appcompat.widget.SwitchCompat");
        return (classByName3 == null || !classByName3.isInstance(view)) ? canonicalName : getViewType(canonicalName, "SwitchCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x029f A[Catch: JSONException -> 0x02eb, TryCatch #2 {JSONException -> 0x02eb, blocks: (B:8:0x000a, B:10:0x0027, B:13:0x0035, B:15:0x003f, B:17:0x004c, B:22:0x0059, B:24:0x027a, B:28:0x0284, B:30:0x028b, B:33:0x0297, B:35:0x029f, B:37:0x02a5, B:41:0x02af, B:43:0x02b6, B:45:0x02c5, B:47:0x02e4, B:51:0x006e, B:56:0x007b, B:57:0x0090, B:62:0x009d, B:63:0x00af, B:68:0x00bc, B:69:0x00ca, B:71:0x00d1, B:72:0x00e6, B:74:0x00ed, B:75:0x0102, B:77:0x0109, B:78:0x011e, B:80:0x0125, B:82:0x013c, B:84:0x0147, B:86:0x0159, B:91:0x0166, B:92:0x017e, B:94:0x0185, B:95:0x019d, B:97:0x01a4, B:99:0x01ae, B:99:0x01ae, B:100:0x01b1, B:103:0x01c5, B:105:0x01cd, B:110:0x01f3, B:115:0x0202, B:117:0x0209, B:121:0x0226, B:124:0x023d, B:126:0x0245, B:131:0x026e), top: B:7:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02b6 A[Catch: JSONException -> 0x02eb, TryCatch #2 {JSONException -> 0x02eb, blocks: (B:8:0x000a, B:10:0x0027, B:13:0x0035, B:15:0x003f, B:17:0x004c, B:22:0x0059, B:24:0x027a, B:28:0x0284, B:30:0x028b, B:33:0x0297, B:35:0x029f, B:37:0x02a5, B:41:0x02af, B:43:0x02b6, B:45:0x02c5, B:47:0x02e4, B:51:0x006e, B:56:0x007b, B:57:0x0090, B:62:0x009d, B:63:0x00af, B:68:0x00bc, B:69:0x00ca, B:71:0x00d1, B:72:0x00e6, B:74:0x00ed, B:75:0x0102, B:77:0x0109, B:78:0x011e, B:80:0x0125, B:82:0x013c, B:84:0x0147, B:86:0x0159, B:91:0x0166, B:92:0x017e, B:94:0x0185, B:95:0x019d, B:97:0x01a4, B:99:0x01ae, B:99:0x01ae, B:100:0x01b1, B:103:0x01c5, B:105:0x01cd, B:110:0x01f3, B:115:0x0202, B:117:0x0209, B:121:0x0226, B:124:0x023d, B:126:0x0245, B:131:0x026e), top: B:7:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02e4 A[Catch: JSONException -> 0x02eb, TRY_ENTER, TryCatch #2 {JSONException -> 0x02eb, blocks: (B:8:0x000a, B:10:0x0027, B:13:0x0035, B:15:0x003f, B:17:0x004c, B:22:0x0059, B:24:0x027a, B:28:0x0284, B:30:0x028b, B:33:0x0297, B:35:0x029f, B:37:0x02a5, B:41:0x02af, B:43:0x02b6, B:45:0x02c5, B:47:0x02e4, B:51:0x006e, B:56:0x007b, B:57:0x0090, B:62:0x009d, B:63:0x00af, B:68:0x00bc, B:69:0x00ca, B:71:0x00d1, B:72:0x00e6, B:74:0x00ed, B:75:0x0102, B:77:0x0109, B:78:0x011e, B:80:0x0125, B:82:0x013c, B:84:0x0147, B:86:0x0159, B:91:0x0166, B:92:0x017e, B:94:0x0185, B:95:0x019d, B:97:0x01a4, B:99:0x01ae, B:99:0x01ae, B:100:0x01b1, B:103:0x01c5, B:105:0x01cd, B:110:0x01f3, B:115:0x0202, B:117:0x0209, B:121:0x0226, B:124:0x023d, B:126:0x0245, B:131:0x026e), top: B:7:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean injectClickInfo(android.view.View r5, org.json.JSONObject r6, boolean r7) {
        /*
            Method dump skipped, instructions count: 754
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.util.AopUtil.injectClickInfo(android.view.View, org.json.JSONObject, boolean):boolean");
    }

    private static boolean isOSViewByPackage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String replace = str.replace(".", "##");
        Iterator<String> it = sOSViewPackage.iterator();
        while (it.hasNext()) {
            if (replace.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isViewIgnored(View view) {
        if (view == null) {
            return true;
        }
        try {
            List<Class> ignoredViewTypeList = SensorsDataAPI.sharedInstance().getIgnoredViewTypeList();
            if (ignoredViewTypeList != null) {
                Iterator<Class> it = ignoredViewTypeList.iterator();
                do {
                    if (it.hasNext()) {
                    }
                } while (!it.next().isAssignableFrom(view.getClass()));
                return true;
            }
            return "1".equals(view.getTag(C0198R.id.sensors_analytics_tag_view_ignored));
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return true;
        }
    }

    public static boolean isViewIgnored(Class cls) {
        if (cls == null) {
            return true;
        }
        try {
            List<Class> ignoredViewTypeList = SensorsDataAPI.sharedInstance().getIgnoredViewTypeList();
            if (ignoredViewTypeList.isEmpty()) {
                return false;
            }
            Iterator<Class> it = ignoredViewTypeList.iterator();
            while (it.hasNext()) {
                if (it.next().isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static void mergeJSONObject(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof Date) {
                    jSONObject2.put(next, DateFormatUtils.formatDate((Date) obj));
                } else {
                    jSONObject2.put(next, obj);
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static String traverseView(StringBuilder sb, ViewGroup viewGroup) {
        StringBuilder sb2 = sb;
        if (sb == null) {
            try {
                sb2 = new StringBuilder();
            } catch (Exception e) {
                SALog.printStackTrace(e);
                return sb != null ? sb.toString() : "";
            }
        }
        if (viewGroup == null) {
            return sb2.toString();
        }
        StringBuilder sb3 = sb2;
        int childCount = viewGroup.getChildCount();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= childCount) {
                return sb2.toString();
            }
            View childAt = viewGroup.getChildAt(i2);
            StringBuilder sb4 = sb2;
            if (childAt.getVisibility() == 0) {
                if (childAt instanceof ViewGroup) {
                    StringBuilder sb5 = sb2;
                    traverseView(sb2, (ViewGroup) childAt);
                } else if (!isViewIgnored(childAt)) {
                    String viewText = getViewText(childAt);
                    StringBuilder sb6 = sb2;
                    if (!TextUtils.isEmpty(viewText)) {
                        StringBuilder sb7 = sb2;
                        sb2.append(viewText);
                        StringBuilder sb8 = sb2;
                        sb2.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                    }
                }
            }
            i = i2 + 1;
        }
    }
}
