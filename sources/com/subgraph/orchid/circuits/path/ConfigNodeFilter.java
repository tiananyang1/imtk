package com.subgraph.orchid.circuits.path;

import com.subgraph.orchid.Router;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/ConfigNodeFilter.class */
public class ConfigNodeFilter implements RouterFilter {
    private final List<RouterFilter> filterList;
    private static final Pattern NETMASK_PATTERN = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)/(\\d+)$");
    private static final Pattern ADDRESS_BITS_PATTERN = Pattern.compile("^(\\d+\\.\\d+\\.\\d+\\.\\d+)/(\\d+)$");
    private static final Pattern IDENTITY_PATTERN = Pattern.compile("^[A-Fa-f0-9]{40}$");
    private static final Pattern COUNTRYCODE_PATTERN = Pattern.compile("^\\{([A-Za-z]{2})\\}$");
    private static final Pattern ROUTERNAME_PATTERN = Pattern.compile("^\\w{1,19}$");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/ConfigNodeFilter$CountryCodeFilter.class */
    public static class CountryCodeFilter implements RouterFilter {
        private final String countryCode;

        public CountryCodeFilter(String str) {
            this.countryCode = str;
        }

        @Override // com.subgraph.orchid.circuits.path.RouterFilter
        public boolean filter(Router router) {
            return this.countryCode.equalsIgnoreCase(router.getCountryCode());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/ConfigNodeFilter$IdentityFilter.class */
    public static class IdentityFilter implements RouterFilter {
        private final HexDigest identity;

        IdentityFilter(HexDigest hexDigest) {
            this.identity = hexDigest;
        }

        @Override // com.subgraph.orchid.circuits.path.RouterFilter
        public boolean filter(Router router) {
            return router.getIdentityHash().equals(this.identity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/ConfigNodeFilter$MaskFilter.class */
    public static class MaskFilter implements RouterFilter {
        private final int bits;
        private final int mask;
        private final int network;

        MaskFilter(IPv4Address iPv4Address, int i) {
            this.bits = i;
            this.mask = createMask(i);
            this.network = iPv4Address.getAddressData() & this.mask;
        }

        static int createMask(int i) {
            if (i == 0) {
                return 0;
            }
            return Integer.MIN_VALUE >> (i - 1);
        }

        @Override // com.subgraph.orchid.circuits.path.RouterFilter
        public boolean filter(Router router) {
            return (router.getAddress().getAddressData() & this.mask) == this.network;
        }

        public String toString() {
            return new IPv4Address(this.network).toString() + "/" + this.bits;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/ConfigNodeFilter$NameFilter.class */
    public static class NameFilter implements RouterFilter {
        private final String name;

        NameFilter(String str) {
            this.name = str;
        }

        @Override // com.subgraph.orchid.circuits.path.RouterFilter
        public boolean filter(Router router) {
            return this.name.equals(router.getNickname());
        }
    }

    private ConfigNodeFilter(List<RouterFilter> list) {
        this.filterList = list;
    }

    private static RouterFilter createAddressFilter(String str) {
        Matcher matcher = ADDRESS_BITS_PATTERN.matcher(str);
        if (matcher.matches()) {
            return new MaskFilter(IPv4Address.createFromString(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        throw new IllegalArgumentException();
    }

    private static RouterFilter createCountryCodeFilter(String str) {
        Matcher matcher = COUNTRYCODE_PATTERN.matcher(str);
        if (matcher.matches()) {
            return new CountryCodeFilter(matcher.group(1));
        }
        throw new IllegalArgumentException();
    }

    static RouterFilter createFilterFor(String str) {
        if (isAddressString(str)) {
            return createAddressFilter(str);
        }
        if (isCountryCodeString(str)) {
            return createCountryCodeFilter(str);
        }
        if (isIdentityString(str)) {
            return createIdentityFilter(str);
        }
        if (isNameString(str)) {
            return createNameFilter(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigNodeFilter createFromStrings(List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            RouterFilter createFilterFor = createFilterFor(it.next());
            if (createFilterFor != null) {
                arrayList.add(createFilterFor);
            }
        }
        return new ConfigNodeFilter(arrayList);
    }

    private static RouterFilter createIdentityFilter(String str) {
        if (isIdentityString(str)) {
            throw new IllegalArgumentException();
        }
        return new IdentityFilter(HexDigest.createFromString(str));
    }

    private static RouterFilter createNameFilter(String str) {
        if (isNameString(str)) {
            return new NameFilter(str);
        }
        throw new IllegalArgumentException();
    }

    static boolean isAddressString(String str) {
        Matcher matcher = NETMASK_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= 5) {
                return isValidMaskValue(matcher.group(5));
            }
            try {
                if (!isValidOctetString(matcher.group(i2))) {
                    return false;
                }
                i = i2 + 1;
            } catch (NumberFormatException e) {
                return false;
            }
            return false;
        }
    }

    static boolean isCountryCodeString(String str) {
        return COUNTRYCODE_PATTERN.matcher(str).matches();
    }

    static boolean isIdentityString(String str) {
        return IDENTITY_PATTERN.matcher(str).matches();
    }

    static boolean isNameString(String str) {
        return ROUTERNAME_PATTERN.matcher(str).matches();
    }

    private static boolean isValidMaskValue(String str) {
        int parseInt = Integer.parseInt(str);
        return parseInt > 0 && parseInt <= 32;
    }

    private static boolean isValidOctetString(String str) {
        int parseInt = Integer.parseInt(str);
        return parseInt >= 0 && parseInt <= 255;
    }

    @Override // com.subgraph.orchid.circuits.path.RouterFilter
    public boolean filter(Router router) {
        Iterator<RouterFilter> it = this.filterList.iterator();
        while (it.hasNext()) {
            if (it.next().filter(router)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.filterList.isEmpty();
    }
}
