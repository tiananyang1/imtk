package io.intercom.android.sdk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationManager;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import io.intercom.android.sdk.api.Api;
import io.intercom.android.sdk.api.ApiFactory;
import io.intercom.android.sdk.api.DeDuper;
import io.intercom.android.sdk.api.TaggingSocketFactory;
import io.intercom.android.sdk.api.UserUpdateBatcher;
import io.intercom.android.sdk.commons.utilities.TimeProvider;
import io.intercom.android.sdk.conversation.SoundPlayer;
import io.intercom.android.sdk.errorreporting.ErrorReporter;
import io.intercom.android.sdk.exceptions.IntercomIntegrationException;
import io.intercom.android.sdk.identity.AppConfig;
import io.intercom.android.sdk.identity.AppIdentity;
import io.intercom.android.sdk.identity.UserIdentity;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.metrics.MetricTracker;
import io.intercom.android.sdk.metrics.MetricsStore;
import io.intercom.android.sdk.metrics.ops.OpsMetricTracker;
import io.intercom.android.sdk.nexus.NexusClient;
import io.intercom.android.sdk.overlay.OverlayPresenter;
import io.intercom.android.sdk.push.SystemNotificationManager;
import io.intercom.android.sdk.state.State;
import io.intercom.android.sdk.store.Store;
import io.intercom.android.sdk.store.StoreFactory;
import io.intercom.android.sdk.twig.Twig;
import io.intercom.android.sdk.utilities.ActivityFinisher;
import io.intercom.android.sdk.utilities.SystemSettings;
import io.intercom.android.sdk.utilities.UuidStringProvider;
import io.intercom.com.bumptech.glide.Glide;
import io.intercom.com.google.gson.Gson;
import io.intercom.com.squareup.otto.Bus;
import io.intercom.com.squareup.otto.ThreadEnforcer;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/Injector.class */
public class Injector {
    private static final int NEXUS_TRAFFIC_TAG = 63987;
    private static final Twig TWIG = LumberMill.getLogger();

    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    private static Injector instance;

    @Nullable
    private Api api;
    private final AppConfig appConfig;
    private final AppIdentity appIdentity;
    private final Application application;

    @Nullable
    private MainThreadBus bus;

    @Nullable
    private ErrorReporter errorReporter;

    @Nullable
    private Gson gson;

    @Nullable
    private LifecycleTracker lifecycleTracker;

    @Nullable
    private MetricTracker metricTracker;

    @Nullable
    private MetricsStore metricsStore;

    @Nullable
    private NexusWrapper nexusClient;

    @Nullable
    private OpsMetricTracker opsMetricTracker;

    @Nullable
    private OverlayPresenter overlayPresenter;

    @Nullable
    private ResetManager resetManager;

    @Nullable
    private Store<State> store;

    @Nullable
    private DeDuper superDeDuper;

    @Nullable
    private SystemNotificationManager systemNotificationManager;
    private final UserIdentity userIdentity;

    @Nullable
    private UserUpdateBatcher userUpdateBatcher;
    private final ActivityFinisher activityFinisher = new ActivityFinisher();
    private final Provider<AppConfig> appConfigProvider = new Provider<AppConfig>() { // from class: io.intercom.android.sdk.Injector.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.intercom.android.sdk.Provider
        public AppConfig get() {
            return Injector.this.appConfig;
        }
    };
    private final Provider<NexusClient> nexusClientProvider = new Provider<NexusClient>() { // from class: io.intercom.android.sdk.Injector.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.intercom.android.sdk.Provider
        public NexusClient get() {
            return Injector.this.getNexusClient();
        }
    };
    private final Provider<UserUpdateBatcher> userUpdateBatcherProvider = new Provider<UserUpdateBatcher>() { // from class: io.intercom.android.sdk.Injector.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.intercom.android.sdk.Provider
        public UserUpdateBatcher get() {
            return Injector.this.getUserUpdateBatcher();
        }
    };
    private final Provider<Api> apiProvider = new Provider<Api>() { // from class: io.intercom.android.sdk.Injector.4
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.intercom.android.sdk.Provider
        public Api get() {
            return Injector.this.getApi();
        }
    };
    private final Provider<OverlayPresenter> overlayManagerProvider = new Provider<OverlayPresenter>() { // from class: io.intercom.android.sdk.Injector.5
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.intercom.android.sdk.Provider
        public OverlayPresenter get() {
            return Injector.this.getOverlayPresenter();
        }
    };
    private final Provider<UserIdentity> userIdentityProvider = new Provider<UserIdentity>() { // from class: io.intercom.android.sdk.Injector.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.intercom.android.sdk.Provider
        public UserIdentity get() {
            return Injector.this.getUserIdentity();
        }
    };
    private final Provider<MetricTracker> metricTrackerProvider = new Provider<MetricTracker>() { // from class: io.intercom.android.sdk.Injector.7
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.intercom.android.sdk.Provider
        public MetricTracker get() {
            return Injector.this.getMetricTracker();
        }
    };

    protected Injector(Application application, AppIdentity appIdentity, AppConfig appConfig, UserIdentity userIdentity) {
        this.application = application;
        this.appIdentity = appIdentity;
        this.appConfig = appConfig;
        this.userIdentity = userIdentity;
    }

    public static Injector get() {
        Injector injector;
        synchronized (Injector.class) {
            try {
                if (instance == null) {
                    throw new IntercomIntegrationException("Intercom was not initialized correctly, Intercom.initialize() needs to be called in onCreate() in your Application class.");
                }
                injector = instance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return injector;
    }

    public static void initIfCachedCredentials(Application application) {
        synchronized (Injector.class) {
            try {
                if (instance != null) {
                    return;
                }
                AppIdentity loadFromDevice = AppIdentity.loadFromDevice(application);
                String apiKey = loadFromDevice.apiKey();
                String appId = loadFromDevice.appId();
                if (!appId.isEmpty() && !apiKey.isEmpty()) {
                    initWithAppCredentials(application, apiKey, appId);
                }
            } finally {
            }
        }
    }

    public static void initWithAppCredentials(Application application, String str, String str2) {
        synchronized (Injector.class) {
            try {
                if (instance != null) {
                    return;
                }
                TWIG.internal("Injector", "Initializing");
                AppIdentity create = AppIdentity.create(str, str2);
                create.persist(application);
                instance = new Injector(application, create, new AppConfig(application), new UserIdentity(application));
                application.registerActivityLifecycleCallbacks(instance.getLifecycleTracker());
            } finally {
            }
        }
    }

    public static boolean isNotInitialised() {
        boolean z;
        synchronized (Injector.class) {
            try {
                z = instance == null;
            } finally {
            }
        }
        return z;
    }

    @VisibleForTesting
    static void setSharedInstance(Injector injector) {
        instance = injector;
    }

    public ActivityFinisher getActivityFinisher() {
        return this.activityFinisher;
    }

    public Api getApi() {
        Api api;
        synchronized (this) {
            if (this.api == null) {
                this.api = ApiFactory.create(getApplication(), this.appIdentity, this.userIdentity, getBus(), getStore(), ApiFactory.getHostname(this.appIdentity), this.appConfigProvider, getGson());
            }
            this.api.updateMaxRequests();
            api = this.api;
        }
        return api;
    }

    public Provider<Api> getApiProvider() {
        return this.apiProvider;
    }

    public Provider<AppConfig> getAppConfigProvider() {
        return this.appConfigProvider;
    }

    public AppIdentity getAppIdentity() {
        return this.appIdentity;
    }

    public Application getApplication() {
        return this.application;
    }

    public Bus getBus() {
        MainThreadBus mainThreadBus;
        synchronized (this) {
            if (this.bus == null) {
                this.bus = new MainThreadBus(ThreadEnforcer.ANY);
            }
            mainThreadBus = this.bus;
        }
        return mainThreadBus;
    }

    public DeDuper getDeDuper() {
        DeDuper deDuper;
        synchronized (this) {
            if (this.superDeDuper == null) {
                this.superDeDuper = new DeDuper(this.appConfigProvider, this.application.getSharedPreferences("INTERCOM_DEDUPER_PREFS", 0));
                this.superDeDuper.readPersistedCachedAttributes();
            }
            deDuper = this.superDeDuper;
        }
        return deDuper;
    }

    public ErrorReporter getErrorReporter() {
        ErrorReporter errorReporter;
        synchronized (this) {
            if (this.errorReporter == null) {
                this.errorReporter = ErrorReporter.create(this.application, getGson(), this.apiProvider);
            }
            errorReporter = this.errorReporter;
        }
        return errorReporter;
    }

    public Gson getGson() {
        Gson gson;
        synchronized (this) {
            if (this.gson == null) {
                this.gson = new Gson();
            }
            gson = this.gson;
        }
        return gson;
    }

    public LifecycleTracker getLifecycleTracker() {
        LifecycleTracker lifecycleTracker;
        synchronized (this) {
            if (this.lifecycleTracker == null) {
                this.lifecycleTracker = LifecycleTracker.create(getSystemNotificationManager(), getMetricsStore(), getErrorReporter(), getDeDuper(), getTimeProvider(), getUserUpdateBatcher(), getStore(), getResetManager(), SystemSettings.getTransitionScale(this.application));
            }
            lifecycleTracker = this.lifecycleTracker;
        }
        return lifecycleTracker;
    }

    public MetricTracker getMetricTracker() {
        MetricTracker metricTracker;
        synchronized (this) {
            if (this.metricTracker == null) {
                this.metricTracker = new MetricTracker(this.userIdentity, getMetricsStore(), getApplication());
            }
            metricTracker = this.metricTracker;
        }
        return metricTracker;
    }

    public MetricsStore getMetricsStore() {
        MetricsStore metricsStore;
        synchronized (this) {
            if (this.metricsStore == null) {
                this.metricsStore = new MetricsStore(getApplication(), getApiProvider(), this.appConfigProvider);
            }
            metricsStore = this.metricsStore;
        }
        return metricsStore;
    }

    public NexusClient getNexusClient() {
        NexusWrapper nexusWrapper;
        synchronized (this) {
            if (this.nexusClient == null) {
                this.nexusClient = new NexusWrapper(LumberMill.getNexusTwig(), NexusClient.defaultOkHttpClientBuilder().socketFactory(new TaggingSocketFactory(SocketFactory.getDefault(), NEXUS_TRAFFIC_TAG)).build(), getBus(), getStore(), getNexusDebouncePeriod());
            }
            nexusWrapper = this.nexusClient;
        }
        return nexusWrapper;
    }

    protected long getNexusDebouncePeriod() {
        return TimeUnit.SECONDS.toMillis(1L);
    }

    public OpsMetricTracker getOpsMetricTracker() {
        OpsMetricTracker opsMetricTracker;
        synchronized (this) {
            if (this.opsMetricTracker == null) {
                this.opsMetricTracker = new OpsMetricTracker(getMetricsStore(), getTimeProvider(), UuidStringProvider.SYSTEM);
            }
            opsMetricTracker = this.opsMetricTracker;
        }
        return opsMetricTracker;
    }

    public OverlayPresenter getOverlayPresenter() {
        OverlayPresenter overlayPresenter;
        synchronized (this) {
            if (this.overlayPresenter == null) {
                this.overlayPresenter = new OverlayPresenter(getApplication(), getBus(), getStore(), this.appConfigProvider, getMetricTracker(), this.userIdentity, Glide.with(this.application));
            }
            overlayPresenter = this.overlayPresenter;
        }
        return overlayPresenter;
    }

    public ResetManager getResetManager() {
        ResetManager resetManager;
        synchronized (this) {
            if (this.resetManager == null) {
                this.resetManager = new ResetManager(getApiProvider(), getUserIdentity(), getOverlayPresenter(), this.appConfigProvider, getStore(), getUserUpdateBatcher(), this.application, this.activityFinisher);
            }
            resetManager = this.resetManager;
        }
        return resetManager;
    }

    public Store<State> getStore() {
        Store<State> store;
        synchronized (this) {
            if (this.store == null) {
                this.store = StoreFactory.createStore(this.apiProvider, this.appConfigProvider, this.nexusClientProvider, this.overlayManagerProvider, this.userUpdateBatcherProvider, new SoundPlayer(this.application, this.appConfigProvider), this.userIdentityProvider, this.application, LumberMill.getLogger(), getBus(), this.metricTrackerProvider);
            }
            store = this.store;
        }
        return store;
    }

    public SystemNotificationManager getSystemNotificationManager() {
        SystemNotificationManager systemNotificationManager;
        synchronized (this) {
            if (this.systemNotificationManager == null) {
                this.systemNotificationManager = new SystemNotificationManager((NotificationManager) this.application.getSystemService("notification"));
            }
            systemNotificationManager = this.systemNotificationManager;
        }
        return systemNotificationManager;
    }

    public TimeProvider getTimeProvider() {
        return TimeProvider.SYSTEM;
    }

    public UserIdentity getUserIdentity() {
        return this.userIdentity;
    }

    public UserUpdateBatcher getUserUpdateBatcher() {
        UserUpdateBatcher userUpdateBatcher;
        synchronized (this) {
            if (this.userUpdateBatcher == null) {
                this.userUpdateBatcher = UserUpdateBatcher.create(getApiProvider(), this.appConfigProvider, getStore());
            }
            userUpdateBatcher = this.userUpdateBatcher;
        }
        return userUpdateBatcher;
    }

    public Provider<UserUpdateBatcher> getUserUpdateBatcherProvider() {
        return this.userUpdateBatcherProvider;
    }
}
