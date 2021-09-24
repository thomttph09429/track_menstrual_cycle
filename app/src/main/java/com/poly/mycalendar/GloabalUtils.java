package com.poly.mycalendar;

import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GloabalUtils {
    public static final int toDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    public static final int monthCurrent = Calendar.getInstance().get(Calendar.MONTH);
    public static final int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);

    public static Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static String getDate(int year, int month,int dayOfMonth) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        String sDate = sdf.format(calendar.getTime());
        return sDate;
    }

}
