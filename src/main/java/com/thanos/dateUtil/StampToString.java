package com.thanos.dateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Thanos Yu
 * @date 2017/11/29
 */
public class StampToString {

    private static String stampToString(long stamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp);
        return simpleDateFormat.format(date);
    }
    public static void main(String[] args) {
        String res = stampToString(1491459540);
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1491459540));
        System.out.println(s);
        String sub = s.substring(5);
        System.out.println(sub);
        String s1 = "01-18 14:17:39";
        String s2 = "01-18 14:17:40";
        System.out.println(s1.compareTo(sub));
        System.out.println(s2.compareTo(sub));
    }
}
