package com.subgraph.orchid.circuits;

import com.subgraph.orchid.TorInitializationListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/TorInitializationTracker.class */
public class TorInitializationTracker {
    private static final Logger logger = Logger.getLogger(TorInitializationTracker.class.getName());
    private static final Map<Integer, String> messageMap = new HashMap();
    private final List<TorInitializationListener> listeners = new ArrayList();
    private final Object stateLock = new Object();
    private int bootstrapState = 0;

    static {
        messageMap.put(0, "Starting");
        messageMap.put(5, "Connecting to directory server");
        messageMap.put(10, "Finishing handshake with directory server");
        messageMap.put(15, "Establishing an encrypted directory connection");
        messageMap.put(20, "Asking for network status consensus");
        messageMap.put(25, "Loading network status consensus");
        messageMap.put(35, "Asking for authority key certs");
        messageMap.put(40, "Loading authority key certs");
        messageMap.put(45, "Asking for relay descriptors");
        messageMap.put(50, "Loading relay descriptors");
        messageMap.put(80, "Connecting to the Tor network");
        messageMap.put(85, "Finished Handshake with first hop");
        messageMap.put(90, "Establishing a Tor circuit");
        messageMap.put(100, "Done");
    }

    private List<TorInitializationListener> getListeners() {
        ArrayList arrayList;
        synchronized (this.listeners) {
            arrayList = new ArrayList(this.listeners);
        }
        return arrayList;
    }

    private String getMessageForCode(int i) {
        return messageMap.containsKey(Integer.valueOf(i)) ? messageMap.get(Integer.valueOf(i)) : "Unknown state";
    }

    private void notifyListeners(int i) {
        String messageForCode = getMessageForCode(i);
        for (TorInitializationListener torInitializationListener : getListeners()) {
            try {
                torInitializationListener.initializationProgress(messageForCode, i);
                if (i >= 100) {
                    torInitializationListener.initializationCompleted();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception occurred in TorInitializationListener callback: " + e.getMessage(), (Throwable) e);
            }
        }
    }

    public void addListener(TorInitializationListener torInitializationListener) {
        synchronized (this.listeners) {
            if (!this.listeners.contains(torInitializationListener)) {
                this.listeners.add(torInitializationListener);
            }
        }
    }

    public int getBootstrapState() {
        return this.bootstrapState;
    }

    public void notifyEvent(int i) {
        synchronized (this.stateLock) {
            if (i <= this.bootstrapState || i > 100) {
                return;
            }
            this.bootstrapState = i;
            notifyListeners(i);
        }
    }

    public void removeListener(TorInitializationListener torInitializationListener) {
        synchronized (this.listeners) {
            this.listeners.remove(torInitializationListener);
        }
    }

    public void start() {
        synchronized (this.stateLock) {
            this.bootstrapState = 0;
            notifyListeners(0);
        }
    }
}
