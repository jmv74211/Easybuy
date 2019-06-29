package com.jmv74211.easybuy.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 *  Author: @jmv74211
 *  Date: 06/2019
 *  Date class formatter
 * */

public class Date {

    private static Date instance;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Date() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        return formatter.format(calendar.getTime());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getDate() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        return formatter.format(calendar.getTime());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Date getInstance() {

        if( instance == null){
            instance = new Date();
        }

        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void setInstance(Date instance) {
        Date.instance = instance;
    }


    public static boolean compareDatetime(String date1, String time1, String date2, String time2) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        java.util.Date d1 = format.parse(date1);
        java.util.Date d2 = format.parse(date2);

        boolean newest; // Más actual

        if (d1.compareTo(d2) < 0) {
           newest = false;
        }
        else if(d1.compareTo(d2) > 0){
            newest = true;
        }
        else{

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            java.util.Date t1 = sdf.parse(time1);
            java.util.Date t2 = sdf.parse(time2);

            long elapsed = t1.getTime() - t2.getTime();

            if(elapsed > 0){
                newest = true;
            }
            else{
                newest  = false;
            }

        }

        return newest;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
