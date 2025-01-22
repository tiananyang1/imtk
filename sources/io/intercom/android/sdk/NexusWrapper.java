package io.intercom.android.sdk;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import io.intercom.android.sdk.actions.Actions;
import io.intercom.android.sdk.conversation.events.AdminIsTypingEvent;
import io.intercom.android.sdk.models.events.realtime.UserContentSeenByAdminEvent;
import io.intercom.android.sdk.nexus.NexusClient;
import io.intercom.android.sdk.nexus.NexusConfig;
import io.intercom.android.sdk.nexus.NexusEvent;
import io.intercom.android.sdk.nexus.NexusEventType;
import io.intercom.android.sdk.nexus.NexusListener;
import io.intercom.android.sdk.state.State;
import io.intercom.android.sdk.store.Store;
import io.intercom.android.sdk.twig.Twig;
import io.intercom.com.squareup.otto.Bus;
import io.intercom.okhttp3.OkHttpClient;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/NexusWrapper.class */
class NexusWrapper extends NexusClient implements NexusListener {
    private static final String ADMIN_AVATAR = "adminAvatar";
    private static final String ADMIN_ID = "adminId";
    private static final String ADMIN_NAME = "adminName";
    private static final String CONVERSATION_ID = "conversationId";

    @Nullable
    private ScheduledFuture actionFuture;
    private final Bus bus;
    private final long debouncePeriodMs;
    private final ScheduledExecutorService executor;
    private final Store<State> store;
    private final Twig twig;

    /* renamed from: io.intercom.android.sdk.NexusWrapper$3 */
    /* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/NexusWrapper$3.class */
    static /* synthetic */ class C09433 {
        static final /* synthetic */ int[] $SwitchMap$io$intercom$android$nexus$NexusEventType = new int[NexusEventType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:22:0x003e
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                io.intercom.android.sdk.nexus.NexusEventType[] r0 = io.intercom.android.sdk.nexus.NexusEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.intercom.android.sdk.NexusWrapper.C09433.$SwitchMap$io$intercom$android$nexus$NexusEventType = r0
                int[] r0 = io.intercom.android.sdk.NexusWrapper.C09433.$SwitchMap$io$intercom$android$nexus$NexusEventType     // Catch: java.lang.NoSuchFieldError -> L36
                io.intercom.android.sdk.nexus.NexusEventType r1 = io.intercom.android.sdk.nexus.NexusEventType.AdminIsTyping     // Catch: java.lang.NoSuchFieldError -> L36
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L36
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L36
            L14:
                int[] r0 = io.intercom.android.sdk.NexusWrapper.C09433.$SwitchMap$io$intercom$android$nexus$NexusEventType     // Catch: java.lang.NoSuchFieldError -> L36 java.lang.NoSuchFieldError -> L3a
                io.intercom.android.sdk.nexus.NexusEventType r1 = io.intercom.android.sdk.nexus.NexusEventType.NewComment     // Catch: java.lang.NoSuchFieldError -> L3a
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3a
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3a
            L1f:
                int[] r0 = io.intercom.android.sdk.NexusWrapper.C09433.$SwitchMap$io$intercom$android$nexus$NexusEventType     // Catch: java.lang.NoSuchFieldError -> L3a java.lang.NoSuchFieldError -> L3e
                io.intercom.android.sdk.nexus.NexusEventType r1 = io.intercom.android.sdk.nexus.NexusEventType.UserContentSeenByAdmin     // Catch: java.lang.NoSuchFieldError -> L3e
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3e
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3e
            L2a:
                int[] r0 = io.intercom.android.sdk.NexusWrapper.C09433.$SwitchMap$io$intercom$android$nexus$NexusEventType     // Catch: java.lang.NoSuchFieldError -> L3e java.lang.NoSuchFieldError -> L42
                io.intercom.android.sdk.nexus.NexusEventType r1 = io.intercom.android.sdk.nexus.NexusEventType.ConversationSeen     // Catch: java.lang.NoSuchFieldError -> L42
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
            throw new UnsupportedOperationException("Method not decompiled: io.intercom.android.sdk.NexusWrapper.C09433.m4148clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NexusWrapper(Twig twig, OkHttpClient okHttpClient, Bus bus, Store<State> store, long j) {
        super(twig, okHttpClient);
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.twig = twig;
        this.bus = bus;
        this.store = store;
        this.debouncePeriodMs = j;
    }

    private void logKnownEvent(NexusEvent nexusEvent) {
        this.twig.internal("Nexus", "Received " + nexusEvent.getEventType() + " event");
    }

    private void removeCallbacks() {
        ScheduledFuture scheduledFuture = this.actionFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public void connect(final NexusConfig nexusConfig, final boolean z) {
        if (nexusConfig.getEndpoints().isEmpty()) {
            this.twig.w("No realtime endpoints present so we can't connect", new Object[0]);
        } else {
            removeCallbacks();
            this.actionFuture = this.executor.schedule(new Runnable() { // from class: io.intercom.android.sdk.NexusWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    NexusWrapper.this.connectNow(nexusConfig, z);
                }
            }, this.debouncePeriodMs, TimeUnit.MILLISECONDS);
        }
    }

    @VisibleForTesting
    void connectNow(NexusConfig nexusConfig, boolean z) {
        if (isConnected()) {
            return;
        }
        super.connect(nexusConfig, z);
        setTopics(Collections.singletonList("*"));
        addEventListener(this);
    }

    public void disconnect() {
        removeCallbacks();
        this.actionFuture = this.executor.schedule(new Runnable() { // from class: io.intercom.android.sdk.NexusWrapper.2
            @Override // java.lang.Runnable
            public void run() {
                NexusWrapper.this.disconnectNow();
            }
        }, this.debouncePeriodMs, TimeUnit.MILLISECONDS);
    }

    @VisibleForTesting
    void disconnectNow() {
        removeEventListener(this);
        super.disconnect();
    }

    public void notifyEvent(NexusEvent nexusEvent) {
        String optString = nexusEvent.getEventData().optString(CONVERSATION_ID);
        int i = C09433.$SwitchMap$io$intercom$android$nexus$NexusEventType[nexusEvent.getEventType().ordinal()];
        if (i == 1) {
            logKnownEvent(nexusEvent);
            this.bus.post(new AdminIsTypingEvent(nexusEvent.getEventData().optString(ADMIN_ID), optString, nexusEvent.getEventData().optString(ADMIN_NAME), nexusEvent.getEventData().optString(ADMIN_AVATAR)));
            return;
        }
        if (i == 2) {
            logKnownEvent(nexusEvent);
            this.store.dispatch(Actions.newCommentEventReceived(optString));
            return;
        }
        if (i == 3) {
            logKnownEvent(nexusEvent);
            this.bus.post(new UserContentSeenByAdminEvent(optString));
        } else {
            if (i == 4) {
                logKnownEvent(nexusEvent);
                this.store.dispatch(Actions.conversationMarkedAsRead(optString));
                return;
            }
            this.twig.internal("Nexus", "Unexpected event: " + nexusEvent.getEventType());
        }
    }

    public void onConnect() {
    }

    public void onConnectFailed() {
    }

    public void onShutdown() {
    }
}
