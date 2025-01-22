package com.reactnativecommunity.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.scroll.OnScrollDispatchHelper;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.reactnativecommunity.webview.events.TopLoadingErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingFinishEvent;
import com.reactnativecommunity.webview.events.TopLoadingProgressEvent;
import com.reactnativecommunity.webview.events.TopLoadingStartEvent;
import com.reactnativecommunity.webview.events.TopMessageEvent;
import com.reactnativecommunity.webview.events.TopShouldStartLoadWithRequestEvent;
import com.subgraph.orchid.Cell;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = "RNCWebView")
/* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/RNCWebViewManager.class */
public class RNCWebViewManager extends SimpleViewManager<WebView> {
    protected static final String BLANK_URL = "about:blank";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_LOAD_URL = 7;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    protected static final String HTML_ENCODING = "UTF-8";
    protected static final String HTML_MIME_TYPE = "text/html";
    protected static final String HTTP_METHOD_POST = "POST";
    protected static final String JAVASCRIPT_INTERFACE = "ReactNativeWebView";
    protected static final String REACT_CLASS = "RNCWebView";
    protected boolean mAllowsFullscreenVideo;
    protected RNCWebChromeClient mWebChromeClient;
    protected WebViewConfig mWebViewConfig;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/RNCWebViewManager$RNCWebChromeClient.class */
    public static class RNCWebChromeClient extends WebChromeClient implements LifecycleEventListener {
        protected static final FrameLayout.LayoutParams FULLSCREEN_LAYOUT_PARAMS = new FrameLayout.LayoutParams(-1, -1, 17);

        @RequiresApi(api = 19)
        protected static final int FULLSCREEN_SYSTEM_UI_VISIBILITY = 7942;
        protected WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected ReactContext mReactContext;
        protected View mVideoView;
        protected View mWebView;

        public RNCWebChromeClient(ReactContext reactContext, WebView webView) {
            this.mReactContext = reactContext;
            this.mWebView = webView;
        }

        protected ViewGroup getRootView() {
            return (ViewGroup) this.mReactContext.getCurrentActivity().findViewById(android.R.id.content);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            callback.invoke(str, true, false);
        }

        public void onHostDestroy() {
        }

        public void onHostPause() {
        }

        public void onHostResume() {
            View view;
            if (Build.VERSION.SDK_INT < 19 || (view = this.mVideoView) == null || view.getSystemUiVisibility() == FULLSCREEN_SYSTEM_UI_VISIBILITY) {
                return;
            }
            this.mVideoView.setSystemUiVisibility(FULLSCREEN_SYSTEM_UI_VISIBILITY);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", webView.getId());
            createMap.putString(SettingsJsonConstants.PROMPT_TITLE_KEY, webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            createMap.putDouble("progress", i / 100.0f);
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingProgressEvent(webView.getId(), createMap));
        }

        @Override // android.webkit.WebChromeClient
        @TargetApi(21)
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            String[] acceptTypes = fileChooserParams.getAcceptTypes();
            boolean z = true;
            if (fileChooserParams.getMode() != 1) {
                z = false;
            }
            return RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, fileChooserParams.createIntent(), acceptTypes, z);
        }

        protected void openFileChooser(ValueCallback<Uri> valueCallback) {
            RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, "");
        }

        protected void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
            RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, str);
        }

        protected void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/RNCWebViewManager$RNCWebView.class */
    public static class RNCWebView extends WebView implements LifecycleEventListener {
        protected boolean hasScrollEvent;

        @Nullable
        protected String injectedJS;
        private OnScrollDispatchHelper mOnScrollDispatchHelper;

        @Nullable
        protected RNCWebViewClient mRNCWebViewClient;
        protected boolean messagingEnabled;
        protected boolean sendContentSizeChangeEvents;

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/RNCWebViewManager$RNCWebView$RNCWebViewBridge.class */
        public class RNCWebViewBridge {
            RNCWebView mContext;

            RNCWebViewBridge(RNCWebView rNCWebView) {
                this.mContext = rNCWebView;
            }

            @JavascriptInterface
            public void postMessage(String str) {
                this.mContext.onMessage(str);
            }
        }

        public RNCWebView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.messagingEnabled = false;
            this.sendContentSizeChangeEvents = false;
            this.hasScrollEvent = false;
        }

        public void callInjectedJavaScript() {
            String str;
            if (!getSettings().getJavaScriptEnabled() || (str = this.injectedJS) == null || TextUtils.isEmpty(str)) {
                return;
            }
            evaluateJavascriptWithFallback("(function() {\n" + this.injectedJS + ";\n})();");
        }

        protected void cleanupCallbacksAndDestroy() {
            setWebViewClient(null);
            destroy();
        }

        protected RNCWebViewBridge createRNCWebViewBridge(RNCWebView rNCWebView) {
            return new RNCWebViewBridge(rNCWebView);
        }

        protected void evaluateJavascriptWithFallback(String str) {
            if (Build.VERSION.SDK_INT >= 19) {
                evaluateJavascript(str, null);
                return;
            }
            try {
                loadUrl("javascript:" + URLEncoder.encode(str, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        @Nullable
        public RNCWebViewClient getRNCWebViewClient() {
            return this.mRNCWebViewClient;
        }

        public void onHostDestroy() {
            cleanupCallbacksAndDestroy();
        }

        public void onHostPause() {
        }

        public void onHostResume() {
        }

        public void onMessage(String str) {
            RNCWebViewManager.dispatchEvent(this, new TopMessageEvent(getId(), str));
        }

        @Override // android.webkit.WebView, android.view.View
        protected void onScrollChanged(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
            if (this.hasScrollEvent) {
                if (this.mOnScrollDispatchHelper == null) {
                    this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
                }
                if (this.mOnScrollDispatchHelper.onScrollChanged(i, i2)) {
                    RNCWebViewManager.dispatchEvent(this, ScrollEvent.obtain(getId(), ScrollEventType.SCROLL, i, i2, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity(), computeHorizontalScrollRange(), computeVerticalScrollRange(), getWidth(), getHeight()));
                }
            }
        }

        @Override // android.webkit.WebView, android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (this.sendContentSizeChangeEvents) {
                RNCWebViewManager.dispatchEvent(this, new ContentSizeChangeEvent(getId(), i, i2));
            }
        }

        public void setHasScrollEvent(boolean z) {
            this.hasScrollEvent = z;
        }

        public void setInjectedJavaScript(@Nullable String str) {
            this.injectedJS = str;
        }

        @SuppressLint({"AddJavascriptInterface"})
        public void setMessagingEnabled(boolean z) {
            if (this.messagingEnabled == z) {
                return;
            }
            this.messagingEnabled = z;
            if (z) {
                addJavascriptInterface(createRNCWebViewBridge(this), RNCWebViewManager.JAVASCRIPT_INTERFACE);
            } else {
                removeJavascriptInterface(RNCWebViewManager.JAVASCRIPT_INTERFACE);
            }
        }

        public void setSendContentSizeChangeEvents(boolean z) {
            this.sendContentSizeChangeEvents = z;
        }

        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.mRNCWebViewClient = (RNCWebViewClient) webViewClient;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/RNCWebViewManager$RNCWebViewClient.class */
    public static class RNCWebViewClient extends WebViewClient {
        protected boolean mLastLoadFailed = false;

        @Nullable
        protected ReadableArray mUrlPrefixesForDefaultIntent;

        protected RNCWebViewClient() {
        }

        protected WritableMap createWebViewEvent(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", webView.getId());
            createMap.putString("url", str);
            createMap.putBoolean("loading", (this.mLastLoadFailed || webView.getProgress() == 100) ? false : true);
            createMap.putString(SettingsJsonConstants.PROMPT_TITLE_KEY, webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }

        protected void emitFinishEvent(WebView webView, String str) {
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.mLastLoadFailed) {
                return;
            }
            ((RNCWebView) webView).callInjectedJavaScript();
            emitFinishEvent(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.mLastLoadFailed = false;
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.mLastLoadFailed = true;
            emitFinishEvent(webView, str2);
            WritableMap createWebViewEvent = createWebViewEvent(webView, str2);
            createWebViewEvent.putDouble("code", i);
            createWebViewEvent.putString("description", str);
            RNCWebViewManager.dispatchEvent(webView, new TopLoadingErrorEvent(webView.getId(), createWebViewEvent));
        }

        public void setUrlPrefixesForDefaultIntent(ReadableArray readableArray) {
            this.mUrlPrefixesForDefaultIntent = readableArray;
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            RNCWebViewManager.dispatchEvent(webView, new TopShouldStartLoadWithRequestEvent(webView.getId(), createWebViewEvent(webView, str)));
            return true;
        }
    }

    public RNCWebViewManager() {
        this.mWebChromeClient = null;
        this.mAllowsFullscreenVideo = false;
        this.mWebViewConfig = new WebViewConfig() { // from class: com.reactnativecommunity.webview.RNCWebViewManager.1
            @Override // com.reactnativecommunity.webview.WebViewConfig
            public void configWebView(WebView webView) {
            }
        };
    }

    public RNCWebViewManager(WebViewConfig webViewConfig) {
        this.mWebChromeClient = null;
        this.mAllowsFullscreenVideo = false;
        this.mWebViewConfig = webViewConfig;
    }

    protected static void dispatchEvent(WebView webView, Event event) {
        webView.getContext().getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(event);
    }

    public static RNCWebViewModule getModule(ReactContext reactContext) {
        return reactContext.getNativeModule(RNCWebViewModule.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, WebView webView) {
        webView.setWebViewClient(new RNCWebViewClient());
    }

    protected RNCWebView createRNCWebViewInstance(ThemedReactContext themedReactContext) {
        return new RNCWebView(themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @TargetApi(21)
    public WebView createViewInstance(final ThemedReactContext themedReactContext) {
        RNCWebView createRNCWebViewInstance = createRNCWebViewInstance(themedReactContext);
        setupWebChromeClient(themedReactContext, createRNCWebViewInstance);
        themedReactContext.addLifecycleEventListener(createRNCWebViewInstance);
        this.mWebViewConfig.configWebView(createRNCWebViewInstance);
        WebSettings settings = createRNCWebViewInstance.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            setAllowUniversalAccessFromFileURLs(createRNCWebViewInstance, false);
        }
        setMixedContentMode(createRNCWebViewInstance, "never");
        createRNCWebViewInstance.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        createRNCWebViewInstance.setDownloadListener(new DownloadListener() { // from class: com.reactnativecommunity.webview.RNCWebViewManager.2
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                RNCWebViewModule module = RNCWebViewManager.getModule(themedReactContext);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
                String guessFileName = URLUtil.guessFileName(str, str3, str4);
                String str5 = "Downloading " + guessFileName;
                try {
                    URL url = new URL(str);
                    String cookie = CookieManager.getInstance().getCookie(url.getProtocol() + "://" + url.getHost());
                    request.addRequestHeader("Cookie", cookie);
                    System.out.println("Got cookie for DownloadManager: " + cookie);
                } catch (MalformedURLException e) {
                    System.out.println("Error getting cookie for DownloadManager: " + e.toString());
                    e.printStackTrace();
                }
                request.addRequestHeader("User-Agent", str2);
                request.setTitle(guessFileName);
                request.setDescription(str5);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, guessFileName);
                module.setDownloadRequest(request);
                if (module.grantFileDownloaderPermissions()) {
                    module.downloadFile();
                }
            }
        });
        return createRNCWebViewInstance;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("goBack", 1, "goForward", 2, "reload", 3, "stopLoading", 4, "postMessage", 5, "injectJavaScript", 6, "loadUrl", 7);
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        Map exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        Map map = exportedCustomDirectEventTypeConstants;
        if (exportedCustomDirectEventTypeConstants == null) {
            map = MapBuilder.newHashMap();
        }
        map.put(TopLoadingProgressEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingProgress"));
        map.put(TopShouldStartLoadWithRequestEvent.EVENT_NAME, MapBuilder.of("registrationName", "onShouldStartLoadWithRequest"));
        map.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll"));
        return map;
    }

    public String getName() {
        return "RNCWebView";
    }

    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance(webView);
        RNCWebView rNCWebView = (RNCWebView) webView;
        webView.getContext().removeLifecycleEventListener(rNCWebView);
        rNCWebView.cleanupCallbacksAndDestroy();
    }

    public void receiveCommand(WebView webView, int i, @Nullable ReadableArray readableArray) {
        switch (i) {
            case 1:
                webView.goBack();
                return;
            case 2:
                webView.goForward();
                return;
            case 3:
                webView.reload();
                return;
            case 4:
                webView.stopLoading();
                return;
            case 5:
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("data", readableArray.getString(0));
                    ((RNCWebView) webView).evaluateJavascriptWithFallback("(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            case 6:
                ((RNCWebView) webView).evaluateJavascriptWithFallback(readableArray.getString(0));
                return;
            case 7:
                if (readableArray == null) {
                    throw new RuntimeException("Arguments for loading an url are null!");
                }
                webView.loadUrl(readableArray.getString(0));
                return;
            default:
                return;
        }
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(WebView webView, @Nullable Boolean bool) {
        webView.getSettings().setAllowFileAccess(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(z);
    }

    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(WebView webView, @Nullable Boolean bool) {
        this.mAllowsFullscreenVideo = bool != null && bool.booleanValue();
        setupWebChromeClient((ReactContext) webView.getContext(), webView);
    }

    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(WebView webView, boolean z) {
        if (!z) {
            webView.getSettings().setCacheMode(2);
            webView.getSettings().setAppCacheEnabled(false);
            return;
        }
        Context context = webView.getContext();
        if (context != null) {
            webView.getSettings().setAppCachePath(context.getCacheDir().getAbsolutePath());
            webView.getSettings().setCacheMode(-1);
            webView.getSettings().setAppCacheEnabled(true);
        }
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(WebView webView, boolean z) {
        webView.getSettings().setDomStorageEnabled(z);
    }

    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(WebView webView, @Nullable Boolean bool) {
        webView.getSettings().setGeolocationEnabled(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "androidHardwareAccelerationDisabled")
    public void setHardwareAccelerationDisabled(WebView webView, boolean z) {
        if (z) {
            webView.setLayerType(1, null);
        } else {
            webView.setLayerType(0, null);
        }
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(WebView webView, @Nullable String str) {
        ((RNCWebView) webView).setInjectedJavaScript(str);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptEnabled(z);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    @TargetApi(17)
    public void setMediaPlaybackRequiresUserAction(WebView webView, boolean z) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(WebView webView, boolean z) {
        ((RNCWebView) webView).setMessagingEnabled(z);
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(WebView webView, @Nullable String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (str == null || "never".equals(str)) {
                webView.getSettings().setMixedContentMode(1);
            } else if ("always".equals(str)) {
                webView.getSettings().setMixedContentMode(0);
            } else if ("compatibility".equals(str)) {
                webView.getSettings().setMixedContentMode(2);
            }
        }
    }

    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(WebView webView, boolean z) {
        ((RNCWebView) webView).setSendContentSizeChangeEvents(z);
    }

    @ReactProp(name = "onScroll")
    public void setOnScroll(WebView webView, boolean z) {
        ((RNCWebView) webView).setHasScrollEvent(z);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(WebView webView, String str) {
        boolean z;
        int hashCode = str.hashCode();
        if (hashCode == -1414557169) {
            if (str.equals("always")) {
                z = 2;
            }
            z = -1;
        } else if (hashCode != 104712844) {
            if (hashCode == 951530617 && str.equals("content")) {
                z = true;
            }
            z = -1;
        } else {
            if (str.equals("never")) {
                z = false;
            }
            z = -1;
        }
        webView.setOverScrollMode((z ? !z ? 0 : 1 : 2).intValue());
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(WebView webView, boolean z) {
        webView.getSettings().setSaveFormData(!z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(WebView webView, boolean z) {
        webView.getSettings().setLoadWithOverviewMode(z);
        webView.getSettings().setUseWideViewPort(z);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(WebView webView, boolean z) {
        webView.setHorizontalScrollBarEnabled(z);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(WebView webView, boolean z) {
        webView.setVerticalScrollBarEnabled(z);
    }

    @ReactProp(name = "source")
    public void setSource(WebView webView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                webView.loadDataWithBaseURL(readableMap.hasKey("baseUrl") ? readableMap.getString("baseUrl") : "", readableMap.getString("html"), HTML_MIME_TYPE, "UTF-8", null);
                return;
            }
            if (readableMap.hasKey("uri")) {
                String string = readableMap.getString("uri");
                String url = webView.getUrl();
                if (url == null || !url.equals(string)) {
                    if (readableMap.hasKey("method") && readableMap.getString("method").equalsIgnoreCase("POST")) {
                        byte[] bArr = null;
                        if (readableMap.hasKey("body")) {
                            String string2 = readableMap.getString("body");
                            try {
                                bArr = string2.getBytes("UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                bArr = string2.getBytes();
                            }
                        }
                        byte[] bArr2 = bArr;
                        if (bArr == null) {
                            bArr2 = new byte[0];
                        }
                        webView.postUrl(string, bArr2);
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    if (readableMap.hasKey("headers")) {
                        ReadableMap map = readableMap.getMap("headers");
                        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            if (!"user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                                hashMap.put(nextKey, map.getString(nextKey));
                            } else if (webView.getSettings() != null) {
                                webView.getSettings().setUserAgentString(map.getString(nextKey));
                            }
                        }
                    }
                    webView.loadUrl(string, hashMap);
                    return;
                }
                return;
            }
        }
        webView.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "textZoom")
    public void setTextZoom(WebView webView, int i) {
        webView.getSettings().setTextZoom(i);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(WebView webView, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, z);
        }
    }

    @ReactProp(name = "urlPrefixesForDefaultIntent")
    public void setUrlPrefixesForDefaultIntent(WebView webView, @Nullable ReadableArray readableArray) {
        RNCWebViewClient rNCWebViewClient = ((RNCWebView) webView).getRNCWebViewClient();
        if (rNCWebViewClient == null || readableArray == null) {
            return;
        }
        rNCWebViewClient.setUrlPrefixesForDefaultIntent(readableArray);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(WebView webView, @Nullable String str) {
        if (str != null) {
            webView.getSettings().setUserAgentString(str);
        }
    }

    protected void setupWebChromeClient(ReactContext reactContext, WebView webView) {
        if (this.mAllowsFullscreenVideo) {
            this.mWebChromeClient = new RNCWebChromeClient(reactContext, webView) { // from class: com.reactnativecommunity.webview.RNCWebViewManager.3
                @Override // android.webkit.WebChromeClient
                public void onHideCustomView() {
                    if (this.mVideoView == null) {
                        return;
                    }
                    this.mVideoView.setVisibility(8);
                    getRootView().removeView(this.mVideoView);
                    this.mCustomViewCallback.onCustomViewHidden();
                    this.mVideoView = null;
                    this.mCustomViewCallback = null;
                    this.mWebView.setVisibility(0);
                    if (Build.VERSION.SDK_INT >= 19) {
                        this.mReactContext.getCurrentActivity().getWindow().clearFlags(Cell.CELL_LEN);
                    }
                    this.mReactContext.removeLifecycleEventListener(this);
                }

                @Override // android.webkit.WebChromeClient
                public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                    if (this.mVideoView != null) {
                        customViewCallback.onCustomViewHidden();
                        return;
                    }
                    this.mVideoView = view;
                    this.mCustomViewCallback = customViewCallback;
                    if (Build.VERSION.SDK_INT >= 19) {
                        this.mVideoView.setSystemUiVisibility(7942);
                        this.mReactContext.getCurrentActivity().getWindow().setFlags(Cell.CELL_LEN, Cell.CELL_LEN);
                    }
                    this.mVideoView.setBackgroundColor(-16777216);
                    getRootView().addView(this.mVideoView, FULLSCREEN_LAYOUT_PARAMS);
                    this.mWebView.setVisibility(8);
                    this.mReactContext.addLifecycleEventListener(this);
                }
            };
            webView.setWebChromeClient(this.mWebChromeClient);
            return;
        }
        RNCWebChromeClient rNCWebChromeClient = this.mWebChromeClient;
        if (rNCWebChromeClient != null) {
            rNCWebChromeClient.onHideCustomView();
        }
        this.mWebChromeClient = new RNCWebChromeClient(reactContext, webView);
        webView.setWebChromeClient(this.mWebChromeClient);
    }
}
