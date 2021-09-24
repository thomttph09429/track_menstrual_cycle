package com.poly.mycalendar.model;
import static com.poly.mycalendar.GloabalUtils.toDay;

public class DataUser {
    private int cycle=28;
    private int period=5;
    private int dayStart = toDay;
    private int yearOfBirth;



    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDayStart() {
        return dayStart;
    }

    public void setDayStart(int dayStart) {
        this.dayStart = dayStart;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public DataUser(int cycle, int period, int dayStart, int yearOfBirth) {
        this.cycle = cycle;
        this.period = period;
        this.dayStart = dayStart;
        this.yearOfBirth = yearOfBirth;
    }

}
