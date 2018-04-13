package com.thanos.api.toutiao;

import com.thanos.common.ZLHttpClient;
import com.thanos.common.ZLJsonUtil;
import com.thanos.model.toutiao.ToutiaoToken;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Thanos Yu
 * @date 2017/11/24
 */
public class ToutiaoApi {

    private static String SECURE_KEY = "bb66a01a93764ac69e6b39cce95b57c8";

    private static String TOKEN_URL = "http://open.snssdk.com/auth/access/device/";
    private static String SIGNATURE = "";
    private static String TIMESTAMP = "";
    private static String NOUNCE = "";
    private static String PARTNER = "huashuo_fyp_api";
    private static String UDID = "99001017001978";
    private static String OPENUDID = "358407080059566";
    private static String OS = "Android";
    private static String OS_VERSION = "7.0";
    private static String DEVICE_MODEL = "ASUS_X00KD";

    private static String formatSignature(String timestamp, String nounce) throws Exception {
        List<String> list = new ArrayList<>();
        list.add(SECURE_KEY);
        list.add(timestamp);
        list.add(nounce);
        Collections.sort(list);
        String s = list.get(0) + list.get(1) + list.get(2);

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(s.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


    private static String formatTokenUrl(String timestamp, String nonce) throws Exception {
        TIMESTAMP = timestamp;
        NOUNCE = nonce;
        SIGNATURE = formatSignature(TIMESTAMP, NOUNCE);
        StringBuilder sb = new StringBuilder();
        sb.append(TOKEN_URL);
        sb.append("?signature=");
        sb.append(SIGNATURE);
        sb.append("&timestamp=");
        sb.append(TIMESTAMP);
        sb.append("&nonce=");
        sb.append(NOUNCE);
        sb.append("&partner=");
        sb.append(PARTNER);
        sb.append("&udid=");
        sb.append(UDID);
        sb.append("&openudid=");
        sb.append(OPENUDID);
        sb.append("&os=");
        sb.append(OS);
        sb.append("&os_version=");
        sb.append(OS_VERSION);
        sb.append("&device_model=");
        sb.append(DEVICE_MODEL);
        System.out.println("---------------------------url: " + sb.toString());
        return sb.toString();
    }

    private static String postResponse(String url) {
        String result = null;
        try {
            result = ZLHttpClient.post(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------result: " + result);
        return result;
    }

    public static ToutiaoToken getToken(String timestamp, String nonce) throws Exception {
        ToutiaoToken model = ZLJsonUtil.fromJson(postResponse(formatTokenUrl(timestamp, nonce)), ToutiaoToken.class);
        return model;
    }

    public static void main(String[] args) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String nounce = String.valueOf(new Random().nextLong());
            ToutiaoToken token = getToken(timestamp, nounce);
            System.out.println(token.getData().getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
