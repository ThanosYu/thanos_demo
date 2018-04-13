package com.yu.test.annotion;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author Thanos_Yu
 * @date 2018/3/15.
 */
/**
 *解析注解：
 *
 * */
public class UserCaseTest {

    public static void trackUseCases(List<Integer> list, Class<?> cl) {
        //对类中的方法进行循环
        for (Method m : cl.getDeclaredMethods()) {
            //获得注解的对象
            UseCase.UseCases uc = m.getAnnotation(UseCase.UseCases.class);
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
                list.remove(new Integer(uc.id()));
            }
        }
        for (int i : list) {
            System.out.println("Warning: Missing use case " + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4);
        trackUseCases(list, PasswordUtils.class);
    }
}