package com.yu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by Thanos Yu on 2017/10/18.
 */
public class HashTextTest {

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String formatSignature(){

        List<String> list = new ArrayList<>();
        list.add("bb66a01a93764ac69e6b39cce95b57c8");
        list.add("1508490602");
        list.add("5852052");
        Collections.sort(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(sha1("15084906025852052bb66a01a93764ac69e6b39cce95b57c8"));
        formatSignature();
        long newlong=new Random().nextLong();
        System.out.println(newlong);
    }
}
