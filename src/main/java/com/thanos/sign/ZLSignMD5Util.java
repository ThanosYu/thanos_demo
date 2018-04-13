package com.thanos.sign;

import java.security.MessageDigest;

/**
 * Created by Yingjie_Li on 2015/11/26.
 */
public class ZLSignMD5Util {
    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    public static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String encodeMD5(String origin){
        String resultString = null;
        try {

            resultString=new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");

            resultString.trim();

            resultString=byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
        }catch (Exception ex){}
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println(encodeMD5("asus_partner"));
    }
}
