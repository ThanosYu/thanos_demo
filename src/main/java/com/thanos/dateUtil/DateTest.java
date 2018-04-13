package com.thanos.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Thanos Yu
 * @date 2017/12/14
 */
public class DateTest {
    public static void main(String[] args) {
        try {
            String time = "2016-12-14";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(time));
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DATE);
            System.out.println(year);
            System.out.println(month);
            System.out.println(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
