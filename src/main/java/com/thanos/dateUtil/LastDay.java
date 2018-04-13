package com.thanos.dateUtil;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Thanos Yu on 2017/9/21.
 */
public class LastDay {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static Date getMondayDate(String time) throws Exception {
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
        return currentDate.getTime();
    }

    private static String getLastMonday(String time) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date thisMonday = getMondayDate(time);
        calendar.setTime(thisMonday);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date lastMonday = calendar.getTime();
        return sdf.format(lastMonday);
    }

    private static String getLastSunday(String time) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date thisMonday = getMondayDate(time);
        calendar.setTime(thisMonday);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date lastSunday = calendar.getTime();
        return sdf.format(lastSunday);
    }

    public static void main(String[] args) throws Exception {
        String time = "2017-09-10";
        System.out.println(getLastMonday(time));
        System.out.println(getLastSunday(time));
    }
}
