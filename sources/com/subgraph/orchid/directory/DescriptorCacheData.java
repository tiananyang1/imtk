package com.subgraph.orchid.directory;

import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.misc.GuardedBy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DescriptorCacheData.class */
public class DescriptorCacheData<T extends Descriptor> {
    private static final long EXPIRY_PERIOD = 604800000;

    @GuardedBy("this")
    private final Map<HexDigest, T> descriptorMap = new HashMap();

    @GuardedBy("this")
    private final List<T> allDescriptors = new ArrayList();

    private Set<T> getExpiredSet() {
        long currentTimeMillis = System.currentTimeMillis();
        HashSet hashSet = new HashSet();
        for (T t : this.allDescriptors) {
            if (isExpired(t, currentTimeMillis)) {
                hashSet.add(t);
            }
        }
        return hashSet;
    }

    private boolean isExpired(T t, long j) {
        return t.getLastListed() != 0 && t.getLastListed() < j - EXPIRY_PERIOD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addDescriptor(T t) {
        synchronized (this) {
            if (this.descriptorMap.containsKey(t.getDescriptorDigest())) {
                return false;
            }
            this.descriptorMap.put(t.getDescriptorDigest(), t);
            this.allDescriptors.add(t);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int cleanExpired() {
        synchronized (this) {
            Set<T> expiredSet = getExpiredSet();
            int i = 0;
            if (expiredSet.isEmpty()) {
                return 0;
            }
            clear();
            for (T t : this.allDescriptors) {
                if (expiredSet.contains(t)) {
                    i += t.getBodyLength();
                } else {
                    addDescriptor(t);
                }
            }
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        synchronized (this) {
            this.descriptorMap.clear();
            this.allDescriptors.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public T findByDigest(HexDigest hexDigest) {
        T t;
        synchronized (this) {
            t = this.descriptorMap.get(hexDigest);
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<T> getAllDescriptors() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.allDescriptors);
        }
        return arrayList;
    }
}
