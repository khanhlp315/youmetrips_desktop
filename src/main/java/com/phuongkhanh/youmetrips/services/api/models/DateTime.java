package com.phuongkhanh.youmetrips.services.api.models;

import java.time.LocalDate;

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

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(int monthValue) {
        this.monthValue = monthValue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setLeapYaer(boolean leapYaer) {
        this.leapYaer = leapYaer;
    }

    @Override
    public String toString() {
        return dayOfMonth + "/" + monthValue + "/" + year;
    }
}
