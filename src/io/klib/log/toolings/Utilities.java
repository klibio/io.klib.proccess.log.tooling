package io.klib.log.toolings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Utilities {
	public static String getDateTimeStamp(String format) {
        DateFormat df   = new SimpleDateFormat(format);
        Date       date = Date.from(Instant.now());
        String     ts   = df.format(date);
        return ts;
    }
}
