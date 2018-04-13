package com.thanos.dateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Thanos Yu
 * @date 2017/12/5
 */
public class DateFormat {


    public static void main(String[] args) {
//        SimpleDateFormat format = new SimpleDateFormat("MMdd");
//        String s = format.format(new Date());
//        System.out.println(s);
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list2 = list;
        list = list.subList(0, 3);
        System.out.println(list2.size());
        System.out.println(list2.get(0));
        System.out.println(list2.get(1));
        System.out.println(list2.get(2));
        System.out.println(list2.get(3));
    }
}
