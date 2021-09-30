package com.poly.mycalendar.utils;

import android.widget.DatePicker;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class GloabalUtils {
    public static LocalDate selectedDate;
    public static final int toDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    public static final int monthCurrent = Calendar.getInstance().get(Calendar.MONTH);
    public static final int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);

    public static String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }

    public static String getDate(int year, int month,int dayOfMonth) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        String sDate = sdf.format(calendar.getTime());
        return sDate;
    }

}
