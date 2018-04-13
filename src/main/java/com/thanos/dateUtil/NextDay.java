package com.thanos.dateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Thanos Yu on 2017/9/22.
 */
public class NextDay {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args)throws Exception {
        String time  = "2017-07-31";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(time));
        calendar.add(Calendar.DAY_OF_YEAR, 1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        String endTime = sdf.format(calendar.getTime());
        System.out.println(endTime);
    }

}
