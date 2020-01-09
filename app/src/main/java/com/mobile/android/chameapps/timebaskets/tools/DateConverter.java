package com.mobile.android.chameapps.timebaskets.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {

    public static String roundToDate(String timestamp) {
        if(timestamp.isEmpty()) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date currenTimeZone = calendar.getTime();
        return sdf.format(currenTimeZone);
    }
}