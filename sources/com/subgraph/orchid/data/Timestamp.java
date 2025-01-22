package com.subgraph.orchid.data;

import com.subgraph.orchid.TorParsingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/Timestamp.class */
public class Timestamp {
    private final Date date;

    public Timestamp(Date date) {
        this.date = date;
    }

    public static Timestamp createFromDateAndTimeString(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormat.setLenient(false);
        try {
            return new Timestamp(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            throw new TorParsingException("Could not parse timestamp string: " + str);
        }
    }

    public Date getDate() {
        return new Date(this.date.getTime());
    }

    public long getTime() {
        return this.date.getTime();
    }

    public boolean hasPassed() {
        return this.date.before(new Date());
    }

    public boolean isBefore(Timestamp timestamp) {
        return this.date.before(timestamp.getDate());
    }

    public String toString() {
        return this.date.toString();
    }
}
