package com.nimbusds.jwt.util;

import java.util.Date;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/util/DateUtils.class */
public class DateUtils {
    private DateUtils() {
    }

    public static Date fromSecondsSinceEpoch(long j) {
        return new Date(j * 1000);
    }

    public static boolean isAfter(Date date, Date date2, long j) {
        return new Date(date.getTime() + (j * 1000)).after(date2);
    }

    public static boolean isBefore(Date date, Date date2, long j) {
        return new Date(date.getTime() - (j * 1000)).before(date2);
    }

    public static long toSecondsSinceEpoch(Date date) {
        return date.getTime() / 1000;
    }
}
