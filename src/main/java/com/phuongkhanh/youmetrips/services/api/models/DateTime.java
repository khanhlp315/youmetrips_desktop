package com.phuongkhanh.youmetrips.services.api.models;

public class DateTime {
    private int year;
    private String month;
    private int dayOfYear;
    private String dayOfWeek;
    private boolean leapYaer;
    private int dayOfMonth;
    private int monthValue;

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public int getYear() {
        return year;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getMonth() {
        return month;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public void setLeapYaer(boolean leapYaer) {
        this.leapYaer = leapYaer;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setMonthValue(int monthValue) {
        this.monthValue = monthValue;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
