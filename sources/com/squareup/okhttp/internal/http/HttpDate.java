package com.squareup.okhttp.internal.http;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/http/HttpDate.class */
public final class HttpDate {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final ThreadLocal<DateFormat> STANDARD_DATE_FORMAT = new ThreadLocal<DateFormat>() { // from class: com.squareup.okhttp.internal.http.HttpDate.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(HttpDate.GMT);
            return simpleDateFormat;
        }
    };
    private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];

    private HttpDate() {
    }

    public static String format(Date date) {
        return STANDARD_DATE_FORMAT.get().format(date);
    }

    public static Date parse(String str) {
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = STANDARD_DATE_FORMAT.get().parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return parse;
        }
        synchronized (BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS) {
            int length = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return null;
                }
                DateFormat dateFormat = BROWSER_COMPATIBLE_DATE_FORMATS[i2];
                DateFormat dateFormat2 = dateFormat;
                if (dateFormat == null) {
                    dateFormat2 = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[i2], Locale.US);
                    dateFormat2.setTimeZone(GMT);
                    BROWSER_COMPATIBLE_DATE_FORMATS[i2] = dateFormat2;
                }
                parsePosition.setIndex(0);
                Date parse2 = dateFormat2.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return parse2;
                }
                i = i2 + 1;
            }
        }
    }
}
