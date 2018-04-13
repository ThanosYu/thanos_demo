package com.yu;

import java.util.Arrays;

/**
 *
 * @author Thanos Yu
 * @date 2017/10/18
 */
public class DicSort {
    public static void main(String[] args) {
        String[] demos = {"bb66a01a93764ac69e6b39cce95b57c8", "1508490602", "5852052"};
        //方法一
        Arrays.sort(demos);

        for (String demo : demos) {
            System.out.println(demo);
        }
    }
}
