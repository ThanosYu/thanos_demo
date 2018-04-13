package com.thanos.dateUtil;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Thanos Yu on 2017/9/21.
 */
public class ThisMonday {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static String getMonday(String time) throws Exception {
        int mondayPlus;
        Date date = sdf.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            mondayPlus = -6;
        } else if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        return sdf.format(currentDate.getTime());
    }

    private static String getSunday(String time) throws Exception {
        Calendar calendar = Calendar.getInstance();
        String monday = getMonday(time);
        calendar.setTime(sdf.parse(monday));
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] args) throws Exception {
        String time = "2017-09-01";
        String monday = getMonday(time);
        String sunday = getSunday(time);
        System.out.println(monday);
        System.out.println(sunday);
    }
}
