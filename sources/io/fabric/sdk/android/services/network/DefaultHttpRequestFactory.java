package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.DefaultLogger;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/network/DefaultHttpRequestFactory.class */
public class DefaultHttpRequestFactory implements HttpRequestFactory {
    private static final String HTTPS = "https";
    private boolean attemptedSslInit;
    private final Logger logger;
    private PinningInfoProvider pinningInfo;
    private SSLSocketFactory sslSocketFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.fabric.sdk.android.services.network.DefaultHttpRequestFactory$1 */
    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/network/DefaultHttpRequestFactory$1.class */
    public static /* synthetic */ class C09211 {
        static final /* synthetic */ int[] $SwitchMap$io$fabric$sdk$android$services$network$HttpMethod = new int[HttpMethod.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:22:0x003e
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                io.fabric.sdk.android.services.network.HttpMethod[] r0 = io.fabric.sdk.android.services.network.HttpMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.fabric.sdk.android.services.network.DefaultHttpRequestFactory.C09211.$SwitchMap$io$fabric$sdk$android$services$network$HttpMethod = r0
                int[] r0 = io.fabric.sdk.android.services.network.DefaultHttpRequestFactory.C09211.$SwitchMap$io$fabric$sdk$android$services$network$HttpMethod     // Catch: java.lang.NoSuchFieldError -> L36
                io.fabric.sdk.android.services.network.HttpMethod r1 = io.fabric.sdk.android.services.network.HttpMethod.GET     // Catch: java.lang.NoSuchFieldError -> L36
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L36
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L36
            L14:
                int[] r0 = io.fabric.sdk.android.services.network.DefaultHttpRequestFactory.C09211.$SwitchMap$io$fabric$sdk$android$services$network$HttpMethod     // Catch: java.lang.NoSuchFieldError -> L36 java.lang.NoSuchFieldError -> L3a
                io.fabric.sdk.android.services.network.HttpMethod r1 = io.fabric.sdk.android.services.network.HttpMethod.POST     // Catch: java.lang.NoSuchFieldError -> L3a
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3a
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3a
            L1f:
                int[] r0 = io.fabric.sdk.android.services.network.DefaultHttpRequestFactory.C09211.$SwitchMap$io$fabric$sdk$android$services$network$HttpMethod     // Catch: java.lang.NoSuchFieldError -> L3a java.lang.NoSuchFieldError -> L3e
                io.fabric.sdk.android.services.network.HttpMethod r1 = io.fabric.sdk.android.services.network.HttpMethod.PUT     // Catch: java.lang.NoSuchFieldError -> L3e
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3e
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3e
            L2a:
                int[] r0 = io.fabric.sdk.android.services.network.DefaultHttpRequestFactory.C09211.$SwitchMap$io$fabric$sdk$android$services$network$HttpMethod     // Catch: java.lang.NoSuchFieldError -> L3e java.lang.NoSuchFieldError -> L42
                io.fabric.sdk.android.services.network.HttpMethod r1 = io.fabric.sdk.android.services.network.HttpMethod.DELETE     // Catch: java.lang.NoSuchFieldError -> L42
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L42
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L42
                return
            L36:
                r4 = move-exception
                goto L14
            L3a:
                r4 = move-exception
                goto L1f
            L3e:
                r4 = move-exception
                goto L2a
            L42:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.network.DefaultHttpRequestFactory.C09211.m4132clinit():void");
        }
    }

    public DefaultHttpRequestFactory() {
        this(new DefaultLogger());
    }

    public DefaultHttpRequestFactory(Logger logger) {
        this.logger = logger;
    }

    private SSLSocketFactory getSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory;
        synchronized (this) {
            if (this.sslSocketFactory == null && !this.attemptedSslInit) {
                this.sslSocketFactory = initSSLSocketFactory();
            }
            sSLSocketFactory = this.sslSocketFactory;
        }
        return sSLSocketFactory;
    }

    private SSLSocketFactory initSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory;
        synchronized (this) {
            this.attemptedSslInit = true;
            try {
                sSLSocketFactory = NetworkUtils.getSSLSocketFactory(this.pinningInfo);
                this.logger.mo2870d(Fabric.TAG, "Custom SSL pinning enabled");
            } catch (Exception e) {
                this.logger.mo2873e(Fabric.TAG, "Exception while validating pinned certs", e);
                return null;
            }
        }
        return sSLSocketFactory;
    }

    private boolean isHttps(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith(HTTPS);
    }

    private void resetSSLSocketFactory() {
        synchronized (this) {
            this.attemptedSslInit = false;
            this.sslSocketFactory = null;
        }
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public HttpRequest buildHttpRequest(HttpMethod httpMethod, String str) {
        return buildHttpRequest(httpMethod, str, Collections.emptyMap());
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public HttpRequest buildHttpRequest(HttpMethod httpMethod, String str, Map<String, String> map) {
        HttpRequest httpRequest;
        SSLSocketFactory sSLSocketFactory;
        int i = C09211.$SwitchMap$io$fabric$sdk$android$services$network$HttpMethod[httpMethod.ordinal()];
        if (i == 1) {
            httpRequest = HttpRequest.get((CharSequence) str, (Map<?, ?>) map, true);
        } else if (i == 2) {
            httpRequest = HttpRequest.post((CharSequence) str, (Map<?, ?>) map, true);
        } else if (i == 3) {
            httpRequest = HttpRequest.put(str);
        } else {
            if (i != 4) {
                throw new IllegalArgumentException("Unsupported HTTP method!");
            }
            httpRequest = HttpRequest.delete(str);
        }
        if (isHttps(str) && this.pinningInfo != null && (sSLSocketFactory = getSSLSocketFactory()) != null) {
            ((HttpsURLConnection) httpRequest.getConnection()).setSSLSocketFactory(sSLSocketFactory);
        }
        return httpRequest;
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public PinningInfoProvider getPinningInfoProvider() {
        return this.pinningInfo;
    }

    @Override // io.fabric.sdk.android.services.network.HttpRequestFactory
    public void setPinningInfoProvider(PinningInfoProvider pinningInfoProvider) {
        if (this.pinningInfo != pinningInfoProvider) {
            this.pinningInfo = pinningInfoProvider;
            resetSSLSocketFactory();
        }
    }
}
