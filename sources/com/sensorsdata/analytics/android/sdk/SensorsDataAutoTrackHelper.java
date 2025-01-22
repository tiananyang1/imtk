package com.sensorsdata.analytics.android.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataAutoTrackHelper.class */
public class SensorsDataAutoTrackHelper {
    private static HashMap<Integer, Long> eventTimestamp = new HashMap<>();

    private static boolean fragmentGetUserVisibleHint(Object obj) {
        try {
            Method method = obj.getClass().getMethod("getUserVisibleHint", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean fragmentIsHidden(Object obj) {
        try {
            Method method = obj.getClass().getMethod("isHidden", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean fragmentIsResumed(Object obj) {
        try {
            Method method = obj.getClass().getMethod("isResumed", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isDeBounceTrack(Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        Long l = eventTimestamp.get(Integer.valueOf(obj.hashCode()));
        boolean z = l != null && currentTimeMillis - l.longValue() < 500;
        eventTimestamp.put(Integer.valueOf(obj.hashCode()), Long.valueOf(currentTimeMillis));
        return z;
    }

    private static boolean isFragment(Object obj) {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3 = null;
        try {
            cls = Class.forName("android.app.Fragment");
        } catch (Exception e) {
            cls = null;
        }
        try {
            cls2 = Class.forName("android.support.v4.app.Fragment");
        } catch (Exception e2) {
            cls2 = null;
        }
        try {
            cls3 = Class.forName("androidx.fragment.app.Fragment");
        } catch (Exception e3) {
        }
        if (cls2 == null && cls3 == null && cls == null) {
            return false;
        }
        if (cls2 != null) {
            try {
                if (cls2.isInstance(obj)) {
                    return true;
                }
            } catch (Exception e4) {
                return false;
            }
        }
        if (cls3 != null && cls3.isInstance(obj)) {
            return true;
        }
        if (cls != null) {
            return cls.isInstance(obj);
        }
        return false;
    }

    public static void onFragmentViewCreated(Object obj, View view, Bundle bundle) {
        try {
            if (isFragment(obj)) {
                String name = obj.getClass().getName();
                view.setTag(C0198R.id.sensors_analytics_tag_view_fragment_name, name);
                if (view instanceof ViewGroup) {
                    traverseView(name, (ViewGroup) view);
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static void track(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = null;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    jSONObject = new JSONObject(str2);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                    jSONObject = null;
                }
            }
            SensorsDataAPI.sharedInstance().track(str, jSONObject);
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:68:0x01fe
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    public static void trackDialog(android.content.DialogInterface r7, int r8) {
        /*
            Method dump skipped, instructions count: 523
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper.trackDialog(android.content.DialogInterface, int):void");
    }

    public static void trackDrawerClosed(View view) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AopConstants.ELEMENT_CONTENT, "Close");
            SensorsDataAPI.sharedInstance().setViewProperties(view, jSONObject);
            trackViewOnClick(view);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static void trackDrawerOpened(View view) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AopConstants.ELEMENT_CONTENT, "Open");
            SensorsDataAPI.sharedInstance().setViewProperties(view, jSONObject);
            trackViewOnClick(view);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static void trackExpandableListViewOnChildClick(ExpandableListView expandableListView, View view, int i, int i2) {
        Context context;
        JSONObject sensorsChildItemTrackProperties;
        try {
            if (!SensorsDataAPI.sharedInstance().isAutoTrackEnabled() || SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) || (context = expandableListView.getContext()) == null) {
                return;
            }
            Activity activityFromContext = AopUtil.getActivityFromContext(context, expandableListView);
            if ((activityFromContext != null && SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) || AopUtil.isViewIgnored(ExpandableListView.class) || AopUtil.isViewIgnored(expandableListView) || AopUtil.isViewIgnored(view)) {
                return;
            }
            JSONObject jSONObject = (JSONObject) view.getTag(C0198R.id.sensors_analytics_tag_view_properties);
            JSONObject jSONObject2 = jSONObject;
            if (jSONObject == null) {
                jSONObject2 = new JSONObject();
            }
            jSONObject2.put(AopConstants.ELEMENT_POSITION, String.format(Locale.CHINA, "%d:%d", Integer.valueOf(i), Integer.valueOf(i2)));
            ExpandableListAdapter expandableListAdapter = expandableListView.getExpandableListAdapter();
            if (expandableListAdapter != null && (expandableListAdapter instanceof SensorsExpandableListViewItemTrackProperties) && (sensorsChildItemTrackProperties = ((SensorsExpandableListViewItemTrackProperties) expandableListAdapter).getSensorsChildItemTrackProperties(i, i2)) != null) {
                AopUtil.mergeJSONObject(sensorsChildItemTrackProperties, jSONObject2);
            }
            AopUtil.addViewPathProperties(activityFromContext, view, jSONObject2);
            if (activityFromContext != null) {
                SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject2);
            }
            String viewId = AopUtil.getViewId(expandableListView);
            if (!TextUtils.isEmpty(viewId)) {
                jSONObject2.put(AopConstants.ELEMENT_ID, viewId);
            }
            jSONObject2.put(AopConstants.ELEMENT_TYPE, "ExpandableListView");
            String str = null;
            String str2 = null;
            if (view instanceof ViewGroup) {
                try {
                    String traverseView = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                    str = traverseView;
                    if (!TextUtils.isEmpty(traverseView)) {
                        str2 = traverseView;
                        str = traverseView.substring(0, traverseView.length() - 1);
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                    str = str2;
                }
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject2.put(AopConstants.ELEMENT_CONTENT, str);
            }
            AopUtil.getFragmentNameFromView(expandableListView, jSONObject2, activityFromContext);
            JSONObject jSONObject3 = (JSONObject) view.getTag(C0198R.id.sensors_analytics_tag_view_properties);
            if (jSONObject3 != null) {
                AopUtil.mergeJSONObject(jSONObject3, jSONObject2);
            }
            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject2);
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
        }
    }

    public static void trackExpandableListViewOnGroupClick(ExpandableListView expandableListView, View view, int i) {
        Context context;
        try {
            if (!SensorsDataAPI.sharedInstance().isAutoTrackEnabled() || SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) || (context = expandableListView.getContext()) == null) {
                return;
            }
            String str = null;
            String str2 = null;
            Activity activity = context instanceof Activity ? (Activity) context : null;
            if ((activity != null && SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) || AopUtil.isViewIgnored(ExpandableListView.class) || AopUtil.isViewIgnored(expandableListView)) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            AopUtil.addViewPathProperties(activity, view, jSONObject);
            if (activity != null) {
                SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activity), jSONObject);
            }
            String viewId = AopUtil.getViewId(expandableListView);
            if (!TextUtils.isEmpty(viewId)) {
                jSONObject.put(AopConstants.ELEMENT_ID, viewId);
            }
            jSONObject.put(AopConstants.ELEMENT_POSITION, String.format(Locale.CHINA, "%d", Integer.valueOf(i)));
            jSONObject.put(AopConstants.ELEMENT_TYPE, "ExpandableListView");
            if (view instanceof ViewGroup) {
                try {
                    String traverseView = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                    str = traverseView;
                    if (!TextUtils.isEmpty(traverseView)) {
                        str2 = traverseView;
                        str = traverseView.substring(0, traverseView.length() - 1);
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                    str = str2;
                }
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
            }
            AopUtil.getFragmentNameFromView(expandableListView, jSONObject, activity);
            JSONObject jSONObject2 = (JSONObject) view.getTag(C0198R.id.sensors_analytics_tag_view_properties);
            if (jSONObject2 != null) {
                AopUtil.mergeJSONObject(jSONObject2, jSONObject);
            }
            ExpandableListAdapter expandableListAdapter = expandableListView.getExpandableListAdapter();
            if (expandableListAdapter != null && (expandableListAdapter instanceof SensorsExpandableListViewItemTrackProperties)) {
                try {
                    JSONObject sensorsGroupItemTrackProperties = ((SensorsExpandableListViewItemTrackProperties) expandableListAdapter).getSensorsGroupItemTrackProperties(i);
                    if (sensorsGroupItemTrackProperties != null) {
                        AopUtil.mergeJSONObject(sensorsGroupItemTrackProperties, jSONObject);
                    }
                } catch (JSONException e2) {
                    SALog.printStackTrace(e2);
                }
            }
            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
        } catch (Exception e3) {
            SALog.printStackTrace(e3);
        }
    }

    private static void trackFragmentAppViewScreen(Object obj) {
        try {
            if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && !"com.bumptech.glide.manager.SupportRequestManagerFragment".equals(obj.getClass().getCanonicalName()) && SensorsDataAPI.sharedInstance().isFragmentAutoTrackAppViewScreen(obj.getClass())) {
                JSONObject jSONObject = new JSONObject();
                AopUtil.getScreenNameAndTitleFromFragment(jSONObject, obj, null);
                if (obj instanceof ScreenAutoTracker) {
                    ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) obj;
                    String screenUrl = screenAutoTracker.getScreenUrl();
                    JSONObject trackProperties = screenAutoTracker.getTrackProperties();
                    if (trackProperties != null) {
                        SensorsDataUtils.mergeJSONObject(trackProperties, jSONObject);
                    }
                    SensorsDataAPI.sharedInstance().trackViewScreen(screenUrl, jSONObject);
                    return;
                }
                SensorsDataAutoTrackAppViewScreenUrl sensorsDataAutoTrackAppViewScreenUrl = (SensorsDataAutoTrackAppViewScreenUrl) obj.getClass().getAnnotation(SensorsDataAutoTrackAppViewScreenUrl.class);
                if (sensorsDataAutoTrackAppViewScreenUrl == null) {
                    SensorsDataAPI.sharedInstance().track("$AppViewScreen", jSONObject);
                    return;
                }
                String url = sensorsDataAutoTrackAppViewScreenUrl.url();
                String str = url;
                if (TextUtils.isEmpty(url)) {
                    str = obj.getClass().getCanonicalName();
                }
                SensorsDataAPI.sharedInstance().trackViewScreen(str, jSONObject);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static void trackFragmentResume(Object obj) {
        if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && isFragment(obj)) {
            try {
                Method method = obj.getClass().getMethod("getParentFragment", new Class[0]);
                if (method != null) {
                    Object invoke = method.invoke(obj, new Object[0]);
                    if (invoke == null) {
                        if (fragmentIsHidden(obj) || !fragmentGetUserVisibleHint(obj)) {
                            return;
                        }
                        trackFragmentAppViewScreen(obj);
                        return;
                    }
                    if (fragmentIsHidden(obj) || !fragmentGetUserVisibleHint(obj) || fragmentIsHidden(invoke) || !fragmentGetUserVisibleHint(invoke)) {
                        return;
                    }
                    trackFragmentAppViewScreen(obj);
                }
            } catch (Exception e) {
            }
        }
    }

    public static void trackFragmentSetUserVisibleHint(Object obj, boolean z) {
        Object obj2;
        if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && isFragment(obj)) {
            try {
                Method method = obj.getClass().getMethod("getParentFragment", new Class[0]);
                obj2 = null;
                if (method != null) {
                    obj2 = method.invoke(obj, new Object[0]);
                }
            } catch (Exception e) {
                obj2 = null;
            }
            if (obj2 == null) {
                if (z && fragmentIsResumed(obj) && !fragmentIsHidden(obj)) {
                    trackFragmentAppViewScreen(obj);
                    return;
                }
                return;
            }
            if (z && fragmentGetUserVisibleHint(obj2) && fragmentIsResumed(obj) && fragmentIsResumed(obj2) && !fragmentIsHidden(obj) && !fragmentIsHidden(obj2)) {
                trackFragmentAppViewScreen(obj);
            }
        }
    }

    public static void trackListView(AdapterView<?> adapterView, View view, int i) {
        Context context;
        if (view == null) {
            return;
        }
        try {
            if (!SensorsDataAPI.sharedInstance().isAutoTrackEnabled() || SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) || (context = view.getContext()) == null) {
                return;
            }
            Activity activityFromContext = AopUtil.getActivityFromContext(context, view);
            if ((activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) && !AopUtil.isViewIgnored(adapterView)) {
                JSONObject jSONObject = new JSONObject();
                if (adapterView instanceof ListView) {
                    jSONObject.put(AopConstants.ELEMENT_TYPE, "ListView");
                    if (AopUtil.isViewIgnored(ListView.class)) {
                        return;
                    }
                } else if (adapterView instanceof GridView) {
                    jSONObject.put(AopConstants.ELEMENT_TYPE, "GridView");
                    if (AopUtil.isViewIgnored(GridView.class)) {
                        return;
                    }
                } else if (adapterView instanceof Spinner) {
                    jSONObject.put(AopConstants.ELEMENT_TYPE, "Spinner");
                    if (AopUtil.isViewIgnored(Spinner.class)) {
                        return;
                    }
                }
                String viewId = AopUtil.getViewId(adapterView);
                if (!TextUtils.isEmpty(viewId)) {
                    jSONObject.put(AopConstants.ELEMENT_ID, viewId);
                }
                Object adapter = adapterView.getAdapter();
                Object obj = adapter;
                if (adapter instanceof HeaderViewListAdapter) {
                    obj = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
                }
                if (obj instanceof SensorsAdapterViewItemTrackProperties) {
                    try {
                        JSONObject sensorsItemTrackProperties = ((SensorsAdapterViewItemTrackProperties) obj).getSensorsItemTrackProperties(i);
                        if (sensorsItemTrackProperties != null) {
                            AopUtil.mergeJSONObject(sensorsItemTrackProperties, jSONObject);
                        }
                    } catch (JSONException e) {
                        SALog.printStackTrace(e);
                    }
                }
                AopUtil.addViewPathProperties(activityFromContext, view, jSONObject);
                if (activityFromContext != null) {
                    SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                }
                jSONObject.put(AopConstants.ELEMENT_POSITION, String.valueOf(i));
                String str = null;
                String str2 = null;
                if (view instanceof ViewGroup) {
                    try {
                        String traverseView = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                        str = traverseView;
                        if (!TextUtils.isEmpty(traverseView)) {
                            str2 = traverseView;
                            str = traverseView.substring(0, traverseView.length() - 1);
                        }
                    } catch (Exception e2) {
                        SALog.printStackTrace(e2);
                        str = str2;
                    }
                } else if (view instanceof TextView) {
                    str = ((TextView) view).getText().toString();
                }
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
                }
                AopUtil.getFragmentNameFromView(adapterView, jSONObject, activityFromContext);
                JSONObject jSONObject2 = (JSONObject) view.getTag(C0198R.id.sensors_analytics_tag_view_properties);
                if (jSONObject2 != null) {
                    AopUtil.mergeJSONObject(jSONObject2, jSONObject);
                }
                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
            }
        } catch (Exception e3) {
            SALog.printStackTrace(e3);
        }
    }

    public static void trackMenuItem(MenuItem menuItem) {
        trackMenuItem(null, menuItem);
    }

    public static void trackMenuItem(Object obj, MenuItem menuItem) {
        try {
            if (!SensorsDataAPI.sharedInstance().isAutoTrackEnabled() || SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) || AopUtil.isViewIgnored(MenuItem.class) || isDeBounceTrack(menuItem)) {
                return;
            }
            Context context = (obj == null || !(obj instanceof Context)) ? null : (Context) obj;
            Activity activityFromContext = context != null ? AopUtil.getActivityFromContext(context, null) : null;
            if (activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) {
                String str = null;
                if (context != null) {
                    try {
                        str = context.getResources().getResourceEntryName(menuItem.getItemId());
                    } catch (Exception e) {
                        SALog.printStackTrace(e);
                        str = null;
                    }
                }
                JSONObject jSONObject = new JSONObject();
                if (activityFromContext != null) {
                    SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                }
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put(AopConstants.ELEMENT_ID, str);
                }
                if (!TextUtils.isEmpty(menuItem.getTitle())) {
                    jSONObject.put(AopConstants.ELEMENT_CONTENT, menuItem.getTitle());
                }
                jSONObject.put(AopConstants.ELEMENT_TYPE, "MenuItem");
                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
            }
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
        }
    }

    public static void trackOnHiddenChanged(Object obj, boolean z) {
        Object obj2;
        if (!SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN) && SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && isFragment(obj)) {
            try {
                Method method = obj.getClass().getMethod("getParentFragment", new Class[0]);
                obj2 = null;
                if (method != null) {
                    obj2 = method.invoke(obj, new Object[0]);
                }
            } catch (Exception e) {
                obj2 = null;
            }
            if (obj2 == null) {
                if (!z && fragmentIsResumed(obj) && fragmentGetUserVisibleHint(obj)) {
                    trackFragmentAppViewScreen(obj);
                    return;
                }
                return;
            }
            if (!z && !fragmentIsHidden(obj2) && fragmentIsResumed(obj) && fragmentIsResumed(obj2) && fragmentGetUserVisibleHint(obj) && fragmentGetUserVisibleHint(obj2)) {
                trackFragmentAppViewScreen(obj);
            }
        }
    }

    public static void trackRN(Object obj, int i, int i2, boolean z) {
        Method method;
        Object invoke;
        try {
            if (SensorsDataAPI.sharedInstance().isReactNativeAutoTrackEnabled() && SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AopConstants.ELEMENT_TYPE, "RNView");
                if (obj != null && (method = Class.forName("com.facebook.react.uimanager.NativeViewHierarchyManager").getMethod("resolveView", Integer.TYPE)) != null && (invoke = method.invoke(obj, Integer.valueOf(i))) != null) {
                    View view = (View) invoke;
                    Activity activityFromContext = AopUtil.getActivityFromContext(view.getContext(), view);
                    if (activityFromContext != null) {
                        SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                    }
                    if (view instanceof CompoundButton) {
                        return;
                    }
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        if (!(view instanceof EditText) && !TextUtils.isEmpty(textView.getText())) {
                            jSONObject.put(AopConstants.ELEMENT_CONTENT, textView.getText().toString());
                        }
                    } else if (view instanceof ViewGroup) {
                        String traverseView = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                        String str = traverseView;
                        if (!TextUtils.isEmpty(traverseView)) {
                            str = traverseView.substring(0, traverseView.length() - 1);
                        }
                        jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
                    }
                }
                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static void trackRadioGroup(RadioGroup radioGroup, int i) {
        Context context;
        try {
            if (radioGroup.findViewById(i).isPressed() && SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) && (context = radioGroup.getContext()) != null) {
                Activity activityFromContext = AopUtil.getActivityFromContext(context, radioGroup);
                if ((activityFromContext == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) && !AopUtil.isViewIgnored(radioGroup)) {
                    JSONObject jSONObject = new JSONObject();
                    String viewId = AopUtil.getViewId(radioGroup);
                    if (!TextUtils.isEmpty(viewId)) {
                        jSONObject.put(AopConstants.ELEMENT_ID, viewId);
                    }
                    if (activityFromContext != null) {
                        SensorsDataUtils.mergeJSONObject(AopUtil.buildTitleAndScreenName(activityFromContext), jSONObject);
                    }
                    View findViewById = radioGroup.findViewById(i);
                    jSONObject.put(AopConstants.ELEMENT_TYPE, findViewById != null ? AopUtil.getViewType(findViewById.getClass().getCanonicalName(), "RadioButton") : "RadioButton");
                    int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                    if (activityFromContext != null) {
                        try {
                            RadioButton radioButton = (RadioButton) activityFromContext.findViewById(checkedRadioButtonId);
                            if (radioButton != null) {
                                if (!TextUtils.isEmpty(radioButton.getText())) {
                                    String charSequence = radioButton.getText().toString();
                                    if (!TextUtils.isEmpty(charSequence)) {
                                        jSONObject.put(AopConstants.ELEMENT_CONTENT, charSequence);
                                    }
                                }
                                AopUtil.addViewPathProperties(activityFromContext, radioButton, jSONObject);
                            }
                        } catch (Exception e) {
                            SALog.printStackTrace(e);
                        }
                    }
                    AopUtil.getFragmentNameFromView(radioGroup, jSONObject, activityFromContext);
                    JSONObject jSONObject2 = (JSONObject) radioGroup.getTag(C0198R.id.sensors_analytics_tag_view_properties);
                    if (jSONObject2 != null) {
                        AopUtil.mergeJSONObject(jSONObject2, jSONObject);
                    }
                    SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                }
            }
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
        }
    }

    public static void trackTabHost(String str) {
        try {
            if (!SensorsDataAPI.sharedInstance().isAutoTrackEnabled() || SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK) || AopUtil.isViewIgnored(TabHost.class)) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AopConstants.ELEMENT_CONTENT, str);
            jSONObject.put(AopConstants.ELEMENT_TYPE, "TabHost");
            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:158:0x00bd, code lost:            r10 = (android.app.Activity) r0;     */
    /* JADX WARN: Removed duplicated region for block: B:125:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x012e A[Catch: Exception -> 0x02d2, TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x02d2, blocks: (B:2:0x0000, B:6:0x000a, B:22:0x004a, B:27:0x0058, B:30:0x0061, B:33:0x0069, B:35:0x0070, B:39:0x0112, B:42:0x0121, B:44:0x012e, B:59:0x0183, B:62:0x0199, B:64:0x01a7, B:121:0x02b7, B:55:0x02bd, B:134:0x0148, B:161:0x0101, B:68:0x01b5, B:71:0x01db, B:91:0x024c, B:95:0x0259, B:100:0x028d, B:102:0x0296, B:104:0x02ab, B:107:0x0264, B:109:0x027a, B:112:0x0270, B:116:0x01c5), top: B:1:0x0000, inners: #12 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0183 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void trackTabLayoutSelected(java.lang.Object r5, java.lang.Object r6) {
        /*
            Method dump skipped, instructions count: 772
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper.trackTabLayoutSelected(java.lang.Object, java.lang.Object):void");
    }

    public static void trackViewOnClick(View view) {
        trackViewOnClick(view, view.isPressed());
    }

    public static void trackViewOnClick(View view, boolean z) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_CLICK)) {
                Activity activityFromContext = AopUtil.getActivityFromContext(view.getContext(), view);
                if ((activityFromContext != null && SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activityFromContext.getClass())) || AopUtil.isViewIgnored(view) || SensorsDataUtils.isDoubleClick(view)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                if (AopUtil.injectClickInfo(view, jSONObject, z)) {
                    SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private static void traverseView(String str, ViewGroup viewGroup) {
        try {
            if (TextUtils.isEmpty(str) || viewGroup == null) {
                return;
            }
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= childCount) {
                    return;
                }
                View childAt = viewGroup.getChildAt(i2);
                childAt.setTag(C0198R.id.sensors_analytics_tag_view_fragment_name, str);
                if ((childAt instanceof ViewGroup) && !(childAt instanceof ListView) && !(childAt instanceof GridView) && !(childAt instanceof Spinner) && !(childAt instanceof RadioGroup)) {
                    traverseView(str, (ViewGroup) childAt);
                }
                i = i2 + 1;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }
}
