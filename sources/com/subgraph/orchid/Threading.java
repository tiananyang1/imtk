package com.subgraph.orchid;

import com.google.common.util.concurrent.CycleDetectingLockFactory;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Threading.class */
public class Threading {
    public static CycleDetectingLockFactory factory;
    private static CycleDetectingLockFactory.Policy policy;

    static {
        throwOnLockCycles();
    }

    public static CycleDetectingLockFactory.Policy getPolicy() {
        return policy;
    }

    public static void ignoreLockCycles() {
        setPolicy(CycleDetectingLockFactory.Policies.DISABLED);
    }

    public static ReentrantLock lock(String str) {
        return factory.newReentrantLock(str);
    }

    public static ExecutorService newPool(String str) {
        return Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(true).setNameFormat(str + "-%d").build());
    }

    public static ScheduledExecutorService newScheduledPool(String str) {
        return Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder().setDaemon(true).setNameFormat(str + "-%d").build());
    }

    public static ScheduledExecutorService newSingleThreadScheduledPool(String str) {
        return Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setDaemon(true).setNameFormat(str + "-%d").build());
    }

    public static void setPolicy(CycleDetectingLockFactory.Policy policy2) {
        policy = policy2;
        factory = CycleDetectingLockFactory.newInstance(policy2);
    }

    public static void throwOnLockCycles() {
        setPolicy(CycleDetectingLockFactory.Policies.THROW);
    }

    public static void warnOnLockCycles() {
        setPolicy(CycleDetectingLockFactory.Policies.WARN);
    }
}
