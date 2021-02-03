package com.wugui.datax.admin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateFormatUtils {

    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DATETIME_FORMAT_T = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIMESTAMP = "Timestamp";

    public static final List<String> formatList() {
        List<String> formatList = new ArrayList<>();
        formatList.add(DATE_FORMAT);
        formatList.add(TIME_FORMAT);
        formatList.add(DATETIME_FORMAT);
        formatList.add(TIMESTAMP);
        return formatList;
    }
    /**
     * @description: 加减时间 type 类型 参考 java.util.Calendar
     **/
    public static Date addTime(Date date, int type, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.add(type, num);
        return calendar.getTime();
    }

    /**
     * format date. like "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDateTime(Date date) {
        return format(date, DATETIME_FORMAT_T);
    }

    /**
     * format date
     *
     * @param date
     * @param patten
     * @return
     * @throws ParseException
     */
    public static String format(Date date, String patten) {
        return getDateFormat(patten).format(date);
    }
    private static final ThreadLocal<Map<String, DateFormat>> dateFormatThreadLocal = new ThreadLocal<Map<String, DateFormat>>();
    private static DateFormat getDateFormat(String pattern) {
        if (pattern==null || pattern.trim().length()==0) {
            throw new IllegalArgumentException("pattern cannot be empty.");
        }

        Map<String, DateFormat> dateFormatMap = dateFormatThreadLocal.get();
        if(dateFormatMap!=null && dateFormatMap.containsKey(pattern)){
            return dateFormatMap.get(pattern);
        }

        synchronized (dateFormatThreadLocal) {
            if (dateFormatMap == null) {
                dateFormatMap = new HashMap<>();
            }
            dateFormatMap.put(pattern, new SimpleDateFormat(pattern));
            dateFormatThreadLocal.set(dateFormatMap);
        }

        return dateFormatMap.get(pattern);
    }

}
