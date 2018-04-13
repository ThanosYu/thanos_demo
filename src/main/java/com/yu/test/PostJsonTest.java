package com.yu.test;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author Thanos Yu
 * @date 2017/9/27
 */
public class PostJsonTest {

    public static String post(String strURL, String params) {
        System.out.println(strURL);
        System.out.println(params);
        try {
            // 创建连接
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                System.out.println(result);
                return result;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    public static void main(String[] args) {
        String url = "http://open.snssdk.com/log/app_log_for_partner/v1/?signature=4f6e13b861ded4b743b01d5f86724c6ab9b68bad&timestamp=1477036774&nonce=5852052&partner=huashuo_fyp_api&access_token=132f4e3d111b2507bf13a3fda4cbb3190015";
        String s = "events=[{\"category\":\"open\",\"tag\":\"go_detail\",\"datetime\":\"2017-10-15 17:18:11\",\"value\":6441863686033244685,\"label\":\"click_news_sports\"}]";
        post(url,s);
    }
}