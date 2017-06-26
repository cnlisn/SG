package com.lisn.sg.View.SelectDateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/6/26.
 */

public class DateUtils {
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static String formateStringH(String dateStr, String pattren) {
        Date date = parseDate(dateStr, pattren);
        try {
            String str = dateToString(date, DateUtils.yyyyMMddHHmm);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return dateStr;
        }
    }

    public static Date parseDate(String dateStr, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String dateToString(Date date, String pattern)
            throws Exception {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date stringToDate(String dateStr, String pattern)
            throws Exception {
        return new SimpleDateFormat(pattern).parse(dateStr);
    }
}
