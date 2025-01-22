package com.xiaomi.push;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* renamed from: com.xiaomi.push.jr */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jr.class */
public final class C0745jr {

    /* renamed from: a */
    private static final Comparator f2302a = new a();

    /* renamed from: com.xiaomi.push.jr$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jr$a.class */
    private static class a implements Comparator {
        private a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            return obj instanceof List ? C0745jr.m2322a((List) obj, (List) obj2) : obj instanceof Set ? C0745jr.m2324a((Set) obj, (Set) obj2) : obj instanceof Map ? C0745jr.m2323a((Map) obj, (Map) obj2) : obj instanceof byte[] ? C0745jr.m2327a((byte[]) obj, (byte[]) obj2) : C0745jr.m2319a((Comparable) obj, (Comparable) obj2);
        }
    }

    /* renamed from: a */
    public static int m2315a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        return b2 < b ? 1 : 0;
    }

    /* renamed from: a */
    public static int m2316a(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        return d2 < d ? 1 : 0;
    }

    /* renamed from: a */
    public static int m2317a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i2 < i ? 1 : 0;
    }

    /* renamed from: a */
    public static int m2318a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j2 < j ? 1 : 0;
    }

    /* renamed from: a */
    public static int m2319a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    /* renamed from: a */
    public static int m2320a(String str, String str2) {
        return str.compareTo(str2);
    }

    /* renamed from: a */
    public static int m2321a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i, remaining);
        return remaining;
    }

    /* renamed from: a */
    public static int m2322a(List list, List list2) {
        int m2317a = m2317a(list.size(), list2.size());
        if (m2317a != 0) {
            return m2317a;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return 0;
            }
            int compare = f2302a.compare(list.get(i2), list2.get(i2));
            if (compare != 0) {
                return compare;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static int m2323a(Map map, Map map2) {
        int m2317a = m2317a(map.size(), map2.size());
        if (m2317a != 0) {
            return m2317a;
        }
        TreeMap treeMap = new TreeMap(f2302a);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        TreeMap treeMap2 = new TreeMap(f2302a);
        treeMap2.putAll(map2);
        Iterator it2 = treeMap2.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            int compare = f2302a.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            int compare2 = f2302a.compare(entry.getValue(), entry2.getValue());
            if (compare2 != 0) {
                return compare2;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m2324a(Set set, Set set2) {
        int m2317a = m2317a(set.size(), set2.size());
        if (m2317a != 0) {
            return m2317a;
        }
        TreeSet treeSet = new TreeSet(f2302a);
        treeSet.addAll(set);
        TreeSet treeSet2 = new TreeSet(f2302a);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compare = f2302a.compare(it.next(), it2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m2325a(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        return s2 < s ? 1 : 0;
    }

    /* renamed from: a */
    public static int m2326a(boolean z, boolean z2) {
        return Boolean.valueOf(z).compareTo(Boolean.valueOf(z2));
    }

    /* renamed from: a */
    public static int m2327a(byte[] bArr, byte[] bArr2) {
        int m2317a = m2317a(bArr.length, bArr2.length);
        if (m2317a != 0) {
            return m2317a;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr.length) {
                return 0;
            }
            int m2315a = m2315a(bArr[i2], bArr2[i2]);
            if (m2315a != 0) {
                return m2315a;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static String m2328a(byte b) {
        return Integer.toHexString((b | 256) & 511).toUpperCase().substring(1);
    }

    /* renamed from: a */
    public static ByteBuffer m2329a(ByteBuffer byteBuffer) {
        return m2331a(byteBuffer) ? byteBuffer : ByteBuffer.wrap(m2332a(byteBuffer));
    }

    /* renamed from: a */
    public static void m2330a(ByteBuffer byteBuffer, StringBuilder sb) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int limit = byteBuffer.limit();
        int i = limit - arrayOffset > 128 ? arrayOffset + 128 : limit;
        int i2 = arrayOffset;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            if (i3 > arrayOffset) {
                sb.append(" ");
            }
            sb.append(m2328a(array[i3]));
            i2 = i3 + 1;
        }
        if (limit != i) {
            sb.append("...");
        }
    }

    /* renamed from: a */
    public static boolean m2331a(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    /* renamed from: a */
    public static byte[] m2332a(ByteBuffer byteBuffer) {
        if (m2331a(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        m2321a(byteBuffer, bArr, 0);
        return bArr;
    }
}
