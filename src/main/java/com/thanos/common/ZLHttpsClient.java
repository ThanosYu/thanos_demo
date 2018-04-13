package com.thanos.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.springframework.util.StringUtils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Yingjie_Li
 * @date 2015/8/7
 */
public class ZLHttpsClient {
    private static final int TIME_OUT = 10 * 1000;
    private static final String CHARSET = "UTF-8";
    public static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";

    public static String get(String urlStr) {
        return get(urlStr, DEFAULT_CONTENT_TYPE);
    }

    public static String get(String urlStr, String contentType) {
        StringBuilder r = new StringBuilder();
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", contentType);
            BufferedReader bf = null;

            InputStream instream = conn.getInputStream();
            bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
            try {
                String str = "";
                while ((str = bf.readLine()) != null) {
                    r.append(str);
                }
            } finally {
                if (instream != null) {
                    instream.close();
                }
            }
            // Create a response handler

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return r.toString();
    }

    public static <T> T get(String urlStr, Class<T> cls) {
        StringBuilder r = new StringBuilder();
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            BufferedReader bf = null;

            InputStream instream = conn.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return mapper.readValue(instream, cls);
            } catch (Exception e) {
                Console.writeErr(e.getMessage());
            } finally {
                if (instream != null) {
                    instream.close();
                }
            }
            // Create a response handler

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static <T> T get(String urlStr, TypeReference cls) {
        StringBuilder r = new StringBuilder();
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            BufferedReader bf = null;

            InputStream instream = conn.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return mapper.readValue(instream, cls);
            } catch (Exception e) {
                Console.writeErr(e.getMessage());
            } finally {
                if (instream != null) {
                    instream.close();
                }
            }
            // Create a response handler

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static String post(String urlStr) {
        return post(urlStr, "");
    }

    public static String post(String urlStr, String body) {
        StringBuilder r = new StringBuilder();
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if(StringUtils.isEmpty(body)) {
                conn.setRequestProperty("Content-Length", null);
                body = "";
            } else {
                conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
            }

            BufferedReader bf = null;
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(body);
            out.flush();
            out.close();
            InputStream instream = conn.getInputStream();
            bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
            try {
                String str = "";
                while ((str = bf.readLine()) != null) {
                    r.append(str);
                }
            } finally {
                if (instream != null) {
                    instream.close();
                }
            }
            // Create a response handler

        } catch (Exception ex) {
            ex.printStackTrace();
            Console.writeErr(ex.getMessage());

        }
        return r.toString();
    }

    public static String post(String urlStr, String body, Map<String, String> headerParams) {
        StringBuilder r = new StringBuilder();
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            Set entries = headerParams.entrySet();
            if (entries != null) {
                Iterator iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
//                    String keyStr=key.toString();
//                    String valueStr=value.toString();
                    Console.debug("Rabbit_Debug_keyStr------>"+key);
                    Console.debug("Rabbit_Debug_valueStr------>"+value);
                    conn.setRequestProperty(key+"",value+"");
                }
            }
            if(StringUtils.isEmpty(body)) {
                conn.setRequestProperty("Content-Length", null);
                body = "";
            } else {
                conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
            }

            BufferedReader bf = null;
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(body);
            out.flush();
            out.close();
            InputStream instream = conn.getInputStream();
            bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
            try {
                String str = "";
                while ((str = bf.readLine()) != null) {
                    r.append(str);
                }
            } finally {
                if (instream != null) {
                    instream.close();
                }
            }
            // Create a response handler

        } catch (Exception ex) {
            ex.printStackTrace();
            Console.writeErr(ex.getMessage());

        }
        return r.toString();
    }

    public static String postJsonStr(String urlStr, String jsonData, Map<String, String> headerParams) {
        StringBuilder r = new StringBuilder();


//        String commonParam = "";
//        String channel = "";
//        String timestamp = "";
//        String sign = "";
//        if (con.containsKey("commonParam")) {
//            commonParam = con.get("commonParam");
//        }
//        if (con.containsKey("channel")) {
//            channel = con.get("channel");
//        }
//        if (con.containsKey("timestamp")) {
//            timestamp = con.get("timestamp");
//        }
//        if (con.containsKey("sign")) {
//            sign = con.get("sign");
//        }
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Content-Length", null);

            Set entries = headerParams.entrySet();
            if (entries != null) {
                Iterator iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
//                    String keyStr=key.toString();
//                    String valueStr=value.toString();
                    Console.debug("Rabbit_Debug_keyStr------>"+key);
                    Console.debug("Rabbit_Debug_valueStr------>"+value);
                    conn.setRequestProperty(key+"",value+"");
                }
            }
//            conn.setRequestProperty("A-Common-Param", commonParam);
//            conn.setRequestProperty("A-Channel", channel);
//            conn.setRequestProperty("A-Timestamp", timestamp);
//            conn.setRequestProperty("A-Sign", sign);
            BufferedReader bf = null;
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(jsonData);
            out.flush();
            out.close();
            InputStream instream = conn.getInputStream();
            bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
            try {
                String str = "";
                while ((str = bf.readLine()) != null) {
                    r.append(str);
                }
            } finally {
                if (instream != null) {
                    instream.close();
                }
            }
            // Create a response handler

        } catch (Exception ex) {
            ex.printStackTrace();
            Console.writeErr(ex.getMessage());

        }
        return r.toString();
    }


    public static void main(String[] args) throws Exception {

        String url = "https://shengshier.asus.com.cn/zenlife_app/app";
//        ZLApiResult<ZlApp> apiResult = new ZLApiResult<>();
//        apiResult = get(url, apiResult.getClass());
//        System.out.println(apiResult.getMsg()[0].getTitle());
    }

    /**
     * Created by will on 14-10-10.
     */
    public static class FakeX509TrustManager implements X509TrustManager {

        private static final X509Certificate[] _AcceptedIssuers = new
                X509Certificate[]{};
        private static TrustManager[] trustManagers;

        public static void allowAllSSL(HttpsURLConnection connection) {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    // TODO Auto-generated method stub
                    return true;
                }

            });

            SSLContext context = null;
            if (trustManagers == null) {
                trustManagers = new TrustManager[]{new FakeX509TrustManager()};
            }

            try {
                context = SSLContext.getInstance("TLS");
                context.init(null, trustManagers, new SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean isClientTrusted(X509Certificate[] chain) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] chain) {
            return true;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return _AcceptedIssuers;
        }

    }

}
