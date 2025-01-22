package com.sensorsdata.analytics.android.sdk.internal;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/internal/IFragmentAPI.class */
public interface IFragmentAPI {
    void enableAutoTrackFragment(Class<?> cls);

    void enableAutoTrackFragments(List<Class<?>> list);

    void ignoreAutoTrackFragment(Class<?> cls);

    void ignoreAutoTrackFragments(List<Class<?>> list);

    boolean isFragmentAutoTrackAppViewScreen(Class<?> cls);

    boolean isTrackFragmentAppViewScreenEnabled();

    void resumeIgnoredAutoTrackFragment(Class<?> cls);

    void resumeIgnoredAutoTrackFragments(List<Class<?>> list);

    void trackFragmentAppViewScreen();
}
