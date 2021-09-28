package com.poly.mycalendar.model;

public class DataUser {
    private int id;
    private int cycle;

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    private int period;
    private String dayStart ;
    private int yearOfBirth;

    public DataUser(int id, int cycle, int period, String dayStart, int yearOfBirth) {
        this.id = id;
        this.cycle = cycle;
        this.period = period;
        this.dayStart = dayStart;
        this.yearOfBirth = yearOfBirth;
    }

    public DataUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




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


    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }



}
