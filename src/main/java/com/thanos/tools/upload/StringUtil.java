package com.thanos.tools.upload;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author Thanos_Yu
 * @date 2018/3/28.
 */
public class StringUtil {

    private static final Random random = new Random();
    private static final String baseString = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int baseLength = baseString.length();

    public static String encodeMD5(String inStr) {
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes;
        try {
            md5Bytes = MessageDigest.getInstance("MD5").digest(byteArray);

            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(baseLength);
            sb.append(baseString.charAt(num));
        }
        return sb.toString();
    }
}
