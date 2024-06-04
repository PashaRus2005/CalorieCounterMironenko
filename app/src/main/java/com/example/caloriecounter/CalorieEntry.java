package com.example.caloriecounter;

import java.util.Date;

public class CalorieEntry {
    private final Date date;
    private final int calories;

    public CalorieEntry(Date date, int calories) {
        this.date = date;
        this.calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public int getCalories() {
        return calories;
    }
}