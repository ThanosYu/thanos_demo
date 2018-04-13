package com.thanos.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Yingjie_Li
 * @date 2015/8/7
 */
public class ZLHttpClient {
    private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private static CloseableHttpClient client = null;
    private static RequestConfig requestConfig = null;
    private static volatile boolean shutdown = false;

    public static CloseableHttpClient getClientInstance() {
        if (client == null) {
            synchronized (ZLHttpClient.class) {
                cm.setMaxTotal(ConstInfo.HTTP_MAX_CONNECT);
                // Increase default max connection per route to 20
                cm.setDefaultMaxPerRoute(ConstInfo.HTTP_MAX_CONNECT / 10);
                cm.setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setMalformedInputAction(CodingErrorAction.IGNORE)
                        .setUnmappableInputAction(CodingErrorAction.IGNORE)
                        .setCharset(Consts.UTF_8).build());
                requestConfig = RequestConfig.custom()
                        .setSocketTimeout(ConstInfo.HTTP_TIMEOUT)
                        .setConnectTimeout(ConstInfo.HTTP_TIMEOUT)
                        .setConnectionRequestTimeout(ConstInfo.HTTP_TIMEOUT)
                        .build();

                HttpClientBuilder builder = HttpClients.custom();
                //builder.setConnectionManager(connManager)
                if (!StringUtils.isEmpty(ConstInfo.HTTP_PROXY_HOST) && ConstInfo.HTTP_PROXY_PORT > 0) {
                    builder.setRoutePlanner(new DefaultProxyRoutePlanner(
                            new HttpHost(ConstInfo.HTTP_PROXY_HOST,
                                    ConstInfo.HTTP_PROXY_PORT)));
                }

                client = builder.setConnectionManager(cm).build();
                Console.info("HttpClient initial complete...");
                startIdleConnectionMonitorThread();
            }
        }
        return client;
    }

    private static void startIdleConnectionMonitorThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (!shutdown) {
                        synchronized (this) {
                            wait(300000);
                            // Close expired connections
                            cm.closeExpiredConnections();
                            // Optionally, close connections
                            // that have been idle longer than 30 sec
                            cm.closeIdleConnections(5, TimeUnit.MINUTES);
                            Console.info("Clear HttpClient Idle Connection...");
                        }
                    }
                } catch (InterruptedException ex) {
                    // terminate
                }
            }

        }.start();
    }

    public static <T> T get(String url, Class<T> cls) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpGet post = new HttpGet(url);
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
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
                }
            } finally {
                if (response != null) response.close();
            }
            return null;
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static <T> T get(String url, TypeReference cls) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpGet post = new HttpGet(url);
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
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
                }
            } finally {
                if (response != null) response.close();
            }
            return null;
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static <T> String get(String url) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpGet post = new HttpGet(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }


    public static Boolean getCheck(String url) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpGet post = new HttpGet(url);
            BufferedReader bf = null;
            post.setConfig(requestConfig);
            CloseableHttpResponse response = null;
            StatusLine statusLine = null;
            HttpEntity entity = null;
            InputStream instream = null;
            Integer statusCode = null;
            try {
                response = httpClient.execute(post);
                if (null != response) {
                    statusLine = response.getStatusLine();
                    entity = response.getEntity();
                } else {
                    Console.warn("response is null!");
                    return false;
                }
//                if (null != entity) {
//                    instream = entity.getContent();
//                } else {
//                    Console.warn("entitiy is null!");
//                    return false;
//                }
                if (null == entity) {
                    Console.warn("entitiy is null!");
                    return false;
                }
                if (null != statusLine) {
                    statusCode = statusLine.getStatusCode();
                    Console.info("responseStatus is " + statusCode);
                } else {
                    Console.warn("statusLine is null!");
                    return false;
                }
                if (200 != statusCode) {
                    return false;
                }
//                if (null != instream) {
//                    Console.warn("instream is null!");
//                    return false;
//                }
                return true;
            } finally {
                if (null != response) response.close();
            }
        } catch (Exception e) {
            Console.error(e.getMessage());
            throw e;
        }
    }

    public static <T> String get(String url, String encode) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpGet post = new HttpGet(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, encode));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    /**
     * modify by: toni_zhang
     * modify date：2016-08-22
     * for：用于解析百度api同步的应用数据
     * do: 1、增加用于百度应用接口数据的解析方法getStrList()
     */

    //modify beigin: for  用于解析百度api同步的应用数据
    public static List<String> getStrList(String url, String encode) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpGet post = new HttpGet(url);
            BufferedReader bf = null;
            List<String> strList = new ArrayList<>();
            post.setConfig(requestConfig);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    if (!StringUtils.isEmpty(encode)) {
                        bf = new BufferedReader(new InputStreamReader(instream, encode));
                    } else {
                        bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    }
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            strList.add(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return strList;
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }
    //modify end: for  用于解析百度api同步的应用数据

    public static <T> String get(String url, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpGet post = new HttpGet(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);

            for (Entry<String, String> entry : headers.entrySet()) {
//                Console.info("Kevin log Key = " + entry.getKey() + ", value = " + entry.getValue());
                post.addHeader(entry.getKey(), entry.getValue());
            }
            Header[] list = post.getAllHeaders();
//            Console.info("header size " + list.length);
//            for (Header header : list) {
//                Console.info("header Key : " + header.getName() + ", value : " + header.getValue());
//            }

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static <T> String post(String url, Map<String, String> params, String contentType) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpPost post = new HttpPost(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();

            while (it.hasNext()) {
                Entry<String, String> entry = it.next();
//                Console.info(entry.getKey() + ":" + entry.getValue());
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            post.setConfig(requestConfig);
            post.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
            post.setHeader("Content-Type", contentType);
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }


    public static <T> String post(String url, Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpPost post = new HttpPost(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();

            while (it.hasNext()) {
                Entry<String, String> entry = it.next();
                Console.info(entry.getKey() + ":" + entry.getValue());
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            post.setConfig(requestConfig);
            post.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static String post(String url) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpPost post = new HttpPost(url);
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);
            BufferedReader bf = null;

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
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
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static String post(String url, int time) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpPost post = new HttpPost(url);
            StringBuilder r = new StringBuilder();
            RequestConfig config = RequestConfig.copy(requestConfig).setConnectionRequestTimeout(time).setConnectTimeout(time).setSocketTimeout(time).build();
            post.setConfig(config);
            BufferedReader bf = null;

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
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
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static <T> String postStr(String url, String postStr) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpPost post = new HttpPost(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();

            post.setConfig(requestConfig);
            post.setEntity(new StringEntity(postStr, Consts.UTF_8));

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static <T> String postStr(String url, String postStr, String header) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpPost post = new HttpPost(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();

            post.setConfig(requestConfig);
            post.setEntity(new StringEntity(postStr, Consts.UTF_8));
            post.setHeader("Content-Type", header);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static <T> String postJsonStr(String url, String json) throws Exception {
        CloseableHttpClient httpClient = getClientInstance();
        try {
            HttpPost post = new HttpPost(url);
            BufferedReader bf = null;
            StringBuilder r = new StringBuilder();

            String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);

            post.addHeader(HTTP.CONTENT_TYPE, UTF8MediaType.JSON);

            StringEntity se = new StringEntity(encoderJson);
            se.setContentType(UTF8MediaType.JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, UTF8MediaType.JSON));

            post.setConfig(requestConfig);
            post.setEntity(new StringEntity(json, Consts.UTF_8));

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                    try {
                        String str = "";
                        while ((str = bf.readLine()) != null) {
                            r.append(str);
                        }
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                if (response != null) response.close();
            }
            return r.toString();
        } catch (Exception e) {
            Console.writeErr(e.getMessage());
            throw e;
        }
    }

    public static String testHttp(String name, String valid, String code) throws Exception {
        /*HttpClient client = HttpClientFactory4.getClientInstance();
        // GetMethod get = new
		// GetMethod("http://www.nmec.org.cn/ViewScore/ViewScoreBS.aspx");
		StringBuilder r = new StringBuilder();
		PostMethod post = new UTF8PostMethod(
				"http://www.nmec.org.cn/ViewScore/ViewScoreBS.aspx");
		//post.addRequestHeader("Content-Type", "text/html;charset=UTF-8");
		post.addParameter("__EVENTARGUMENT", "");
		post.addParameter("__EVENTTARGET", "");
		post.addParameter("__EVENTVALIDATION","/wEWBQKXhvvrBwKu1/C5AgK047EHAqfj5cgLAvqc4c8EBBCQVr6ASnCf+tLb+eSGucuC0a8=");
		post.addParameter("__VIEWSTATE","/wEPDwUIMjcyNTAxMDZkZLdcmar+EKnVHbHEq3Je7NpkDk8b");
		post.addParameter("btnView", "查	询");
		post.addParameter("txtXINGMING", name);
		post.addParameter("txtSFZH", valid);
		post.addParameter("txtZKZH", code);

//		post.addParameter("txtXINGMING", "蒋文洁");
//		post.addParameter("txtSFZH", "360481198707143629");
//		post.addParameter("txtZKZH", "3302092100425");

		BufferedReader bf = null;
		// get.setQueryString(params);
		// Log.debug(get.getURI() + " " + get.getQueryString());
		int status = 0;
		try {
			if ((status = client.executeMethod(post)) == HttpStatus.SC_OK) {
				bf = new BufferedReader(new InputStreamReader(post
						.getResponseBodyAsStream(), post.getResponseCharSet()));
				String str = "";
				// 第一次提交后得到的结果,这里要分析出rowids,例如从document.apiForm.rowids.value=",3027768,3028009,2860531,3028653";
				// 这一行里面得到,3027768,3028009,2860531,3028653
				while ((str = bf.readLine()) != null) {
					System.out.print(str);
					r.append(str);
				}
			}else{
				r.append("查询失败,返回状态:"+status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
			if (bf != null) {
				bf.close();
			}
		}
		return r.toString();*/
        return null;
    }

    public static String postString(String url, String postStr) throws Exception {
        try {
            HttpPost post = new HttpPost(url);
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(postStr, Consts.UTF_8));
            excuteRequest(post, r);
            return r.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static String postXmlStr(String url, String params, Map<String, String> headerParams) {

        try {
            HttpPost post = new HttpPost(url);
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);
            Set entries = headerParams.entrySet();
            if (entries != null) {
                Iterator iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry entry = (Entry) iterator.next();
                    String key = (String) entry.getKey();
                    String value = (String) entry.getValue();
                    post.setHeader(key, value);
                }
            }
            post.setEntity(new StringEntity(params, Consts.UTF_8));
            excuteRequest(post, r);
            return r.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void excuteRequest(Object obj, StringBuilder r) {
        HttpGet get = null;
        HttpPost post = null;
        if (obj instanceof HttpGet) {
            get = (HttpGet) obj;
        } else if (obj instanceof HttpPost) {
            post = (HttpPost) obj;
        }
        BufferedReader bf;
        CloseableHttpClient httpClient = getClientInstance();
        CloseableHttpResponse response = null;
        try {
            response = get != null ? httpClient.execute(get) : httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                bf = new BufferedReader(new InputStreamReader(instream, Consts.UTF_8));
                try {
                    String str = "";
                    while ((str = bf.readLine()) != null) {
                        r.append(str);
                    }
                } finally {
                    instream.close();
                }
            }
        } catch (IOException e) {

        } finally {
            try {
                if (response != null) response.close();
            } catch (IOException e) {

            }
        }
    }

    public static void main(String[] args) throws Exception {
        //ClientFactory.testHttp();
//        String url = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=2Dle0dl9uD0B87CXq4PHUthP&client_secret=PhEz3YImHnAPnNPpMApGcU1tCjS22Cjz";
//        String response = get(url);
//        System.out.println(response);
        String url = "http://117.131.17.78:8080/opencontent/playurl";
        String req = "{\"deviceId\":\"sdfsdfdsf-847rgr\",\"partnerId\":\"5770cdd7c3891cbff829667f\",\"resourceId\":\"619071215\",\"userNumber\":\"13372021863\"}";
        String result = postString(url, req);
        System.out.println(result);
//        String url = "https://shengshier.asus.com.cn/zenlife_app/app";
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("isFull", "true");
////        hashMap.put("isFull", "true");
//        String response = get(url);
//        System.out.println(response);
    }
}
