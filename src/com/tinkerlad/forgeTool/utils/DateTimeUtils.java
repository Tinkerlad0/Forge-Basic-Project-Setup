package com.tinkerlad.forgeTool.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Michael Brock on 12/21/2014.
 */
public class DateTimeUtils {

    public static void main(String[] args) {

        DateTimeUtils obj = new DateTimeUtils();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

        try {

            Date date1 = simpleDateFormat.parse("10/10/2013 11:30:10");
            Date date2 = simpleDateFormat.parse("13/10/2013 20:35:55");

            obj.getDifference(date1, date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public String getDifference(Date startDate, Date endDate) {

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

//        System.out.println("startDate : " + startDate);
//        System.out.println("endDate : "+ endDate);
//        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String duration = "";

        if ((int) elapsedDays != 0) {
            duration += (int) elapsedDays + " Days ";
        }

        if ((int) elapsedHours != 0) {
            duration += (int) elapsedDays + " Hours ";
        }

        if ((int) elapsedMinutes != 0) {
            duration += (int) elapsedDays + " Minutes ";
        }


        if ((int) elapsedSeconds != 0) {
            duration += (int) elapsedSeconds + " Seconds ";
        }

        return duration;

    }

}
