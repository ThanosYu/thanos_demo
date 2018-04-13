package com.yu.test;

import com.thanos.model.o2o.O2OBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thanos_Yu
 * @date 2018/3/15.
 */
public class Test {

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println("-------------------------before："+i);
            if (i>3){
                if (i==5) {
                    continue;
                }
            }
            System.out.println("----------------------------after："+i);
        }
    }
}
