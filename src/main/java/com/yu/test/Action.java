package com.yu.test;

/**
 *
 * @author Thanos Yu
 */
public class Action {
    public static void main(String[] args) {
        String relId = "share#22;openid#192.168.168.80";
        String[] infos = relId.split(";");
        String infoId = infos[0].substring(infos[0].indexOf("#")+1);
        System.out.println(infoId);
    }
}
