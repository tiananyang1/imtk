package com.subgraph.orchid.geoip;

import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.subgraph.orchid.data.IPv4Address;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/geoip/CountryCodeService.class */
public class CountryCodeService {
    private static final int COUNTRY_BEGIN = 16776960;
    private static final String DATABASE_FILENAME = "GeoIP.dat";
    private static final int MAX_RECORD_LENGTH = 4;
    private static final int STANDARD_RECORD_LENGTH = 3;
    private final byte[] database = loadDatabase();
    private static final Logger logger = Logger.getLogger(CountryCodeService.class.getName());
    private static final CountryCodeService DEFAULT_INSTANCE = new CountryCodeService();
    private static final String[] COUNTRY_CODES = {"--", "AP", "EU", "AD", "AE", "AF", "AG", "AI", "AL", "AM", "CW", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BM", "BN", "BO", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO", "FR", "SX", "GA", "GB", "GD", "GE", "GF", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IN", "IO", "IQ", "IR", "IS", "IT", "JM", "JO", "JP", "KE", ExpandedProductParsedResult.KILOGRAM, "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", ExpandedProductParsedResult.POUND, "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "ST", "SV", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TM", "TN", "TO", "TL", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU", "WF", "WS", "YE", "YT", "RS", "ZA", "ZM", "ME", "ZW", "A1", "A2", "O1", "AX", "GG", "IM", "JE", "BL", "MF", "BQ", "SS", "O1"};

    private static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        int i = 0;
        while (true) {
            int i2 = i;
            int read = inputStream.read(bArr);
            if (read == -1) {
                return i2;
            }
            outputStream.write(bArr, 0, read);
            i = i2 + read;
        }
    }

    public static CountryCodeService getInstance() {
        return DEFAULT_INSTANCE;
    }

    private static byte[] loadDatabase() {
        InputStream openDatabaseStream = openDatabaseStream();
        if (openDatabaseStream == null) {
            logger.warning("Failed to open 'GeoIP.dat' database file for country code lookups");
            return null;
        }
        try {
            try {
                byte[] loadEntireStream = loadEntireStream(openDatabaseStream);
                try {
                    openDatabaseStream.close();
                    return loadEntireStream;
                } catch (IOException e) {
                    return loadEntireStream;
                }
            } catch (Throwable th) {
                try {
                    openDatabaseStream.close();
                } catch (IOException e2) {
                }
                throw th;
            }
        } catch (IOException e3) {
            logger.warning("IO error reading database file for country code lookups");
            try {
                openDatabaseStream.close();
                return null;
            } catch (IOException e4) {
                return null;
            }
        }
    }

    private static byte[] loadEntireStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void loadRecord(int i, byte[] bArr) {
        System.arraycopy(this.database, i * 6, bArr, 0, bArr.length);
    }

    private static InputStream openDatabaseStream() {
        InputStream tryResourceOpen = tryResourceOpen();
        return tryResourceOpen != null ? tryResourceOpen : tryFilesystemOpen();
    }

    private int seekCountry(IPv4Address iPv4Address) {
        if (this.database == null) {
            return 0;
        }
        byte[] bArr = new byte[8];
        int[] iArr = new int[2];
        long addressData = iPv4Address.getAddressData();
        int i = 0;
        for (int i2 = 31; i2 >= 0; i2--) {
            loadRecord(i, bArr);
            iArr[0] = unpackRecordValue(bArr, 0);
            iArr[1] = unpackRecordValue(bArr, 1);
            i = (((long) (1 << i2)) & (addressData & 4294967295L)) > 0 ? iArr[1] : iArr[0];
            if (i >= COUNTRY_BEGIN) {
                int i3 = i - COUNTRY_BEGIN;
                if (i3 >= 0 && i3 <= COUNTRY_CODES.length) {
                    return i3;
                }
                logger.warning("Invalid index calculated looking up country code record for (" + iPv4Address + ") idx = " + i3);
                return 0;
            }
        }
        logger.warning("No record found looking up country code record for (" + iPv4Address + ")");
        return 0;
    }

    private static InputStream tryFilesystemOpen() {
        File file = new File(new File(System.getProperty("user.dir"), "data"), DATABASE_FILENAME);
        if (!file.canRead()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private static InputStream tryResourceOpen() {
        return CountryCodeService.class.getResourceAsStream("/data/GeoIP.dat");
    }

    private int unpackRecordValue(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 3; i3++) {
            i2 += (bArr[(i * 3) + i3] & 255) << (i3 * 8);
        }
        return i2;
    }

    public String getCountryCodeForAddress(IPv4Address iPv4Address) {
        return COUNTRY_CODES[seekCountry(iPv4Address)];
    }
}
