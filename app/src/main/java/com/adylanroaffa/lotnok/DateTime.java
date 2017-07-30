package com.adylanroaffa.lotnok;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v4.app.NotificationCompat;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by ffahleraz on 7/20/17.
 */

public class DateTime {

    // four digit year e.g. 2017
    private int year = 0;

    // month
    private int month = 0;

    // day
    private int day = 0;

    // hour in 24-hour format
    private int hour = 0;

    // minute
    private int minute = 0;

    // for Date formatting
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // set and get methods
    public void setYear(int year) { this.year = year; }
    public int getYear() { return this.year; }

    public void setMonth(int month) { this.month = month; }
    public int getMonth() { return this.month; }

    public void setDay(int day) { this.day = day; }
    public int getDay() { return this.day; }

    public void setHour(int hour) { this.hour = hour; }
    public int getHour() { return this.hour; }

    public void setMinute(int minute) { this.minute = minute; }
    public int getMinute() { return this.minute; }

    // set values by passing Date
    public void setByDate(Date date) {

        String yearFormat = "yyyy";
        SimpleDateFormat yearSDF = new SimpleDateFormat(yearFormat);
        this.year = Integer.parseInt(yearSDF.format(date));

        String monthFormat = "MM";
        SimpleDateFormat monthSDF = new SimpleDateFormat(monthFormat);
        this.month = Integer.parseInt(monthSDF.format(date));

        String dayFormat = "dd";
        SimpleDateFormat daySDF = new SimpleDateFormat(dayFormat);
        this.day = Integer.parseInt(daySDF.format(date));

        String hourFormat = "HH";
        SimpleDateFormat hourSDF = new SimpleDateFormat(hourFormat);
        this.hour = Integer.parseInt(hourSDF.format(date));

        String minuteFormat = "mm";
        SimpleDateFormat minuteSDF = new SimpleDateFormat(minuteFormat);
        this.minute = Integer.parseInt(minuteSDF.format(date));

    }

    // get values as a Date
    public Date getByDate() {

        String formatted = String.format("%04d", this.year) + "-" + String.format("%02d", this.month) +
                "-" + String.format("%02d", this.day) + " " + String.format("%02d", this.hour) + ":" +
                String.format("%02d", this.minute);

        Date date = null;
        try {
            date = formatter.parse(formatted);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date;

    }

    public String print() {

        return formatter.format(this.getByDate());

    }

}
