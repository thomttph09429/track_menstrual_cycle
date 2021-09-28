package com.poly.mycalendar.model;

public class CalendarItem {

    public static final int RED = 1;
    public static final int DEFAULT = 2;
    public static final int GREEN = 3;

    private int status;
    private long date;

    public CalendarItem(int status, long date) {
        this.status = status;
        this.date = date;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
