package com.poly.mycalendar.model;

import java.time.LocalDate;

public class DayItem {
    public static final int RED = 1;
    public static final int DEFAULT = 2;
    public static final int PASS = 3;
    public DayItem(int status, LocalDate date, boolean fist) {
        this.status = status;
        this.date = date;
        this.fist = fist;
    }

    private int status;
    private LocalDate date;

    private boolean fist;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isFist() {
        return fist;
    }

    public void setFist(boolean fist) {
        this.fist = fist;
    }

    public DayItem( LocalDate date) {
        this.date = date;
    }

    public DayItem() {
    }

}
