package com.thanos.sign;

import com.thanos.common.ZLData;
import org.apache.log4j.Logger;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Yingjie_Li on 2015/11/26.
 */
public class ZLSignUtil {
    private static Logger logger = Logger.getLogger(ZLSignUtil.class);

    public static final String PRIVATE_KEY;
    public static final String SHENGSHIER_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJgRG10bwfmIImow9xL0N2PY1hT+3FgT99eoVuYQskmRKceDr8p1DhhqWimGpIAjeFd2SD2zevTB0Y8Tqma/Pm+Q2oMBfTSZVFPxjKz74P58TBbkC7K4R3tY0yOGV9yqsV77Hr/Sk5QdxgoS4yYvD4ogtAc1StpH3rqPn8Pii537AgMBAAECgYBiGc8kXu7hcuB4z0Pll2VQZaeefdhafe9K0g/Vdfn+mcZxLtQucRt5xsqJtZHUFI3jJqxuGaZ91bfCbLZmeIPm5awyhlpHouwjD9AYY3jGYRTiDFpty3BG8YPLB0OatkK3GQbs66z/pF7VZoMN4PYMdq6fEb3JMyYMFUfGSjBK0QJBAOc7rUyDVkOMC6KCI3RfCk5CZP36mONvKilFTXyOLm0jGdllFO5vtJJcvEYuULoMB5zCV5MPJx0ptekBWHAaShkCQQCoWriBnlvu3E52ARWJOF/KWkGQivmxNGle6XOESHLV5CueEifmPeCpmtXXdRQEdRyITBCSg+gCZC0e307QJxMzAkADtU5LKtQIU0egfIvLAvcsWB48gHdRNmgrr0PV2oXeFDEG7nsOc9Ymfrw6N+XtheHdXrR+1EBhVFjpeq8LZTCJAkBeueT08bCSnpz7IrMGaZTkW5JmjPvfbDkZsUtRFB6hIOg14ctXbluZssg9md+wfmCWhA4TEmsZ5VnJ9CX5UvMnAkACkjc1U1gFdIM7t2ITXabgcag16mCUiAMMh9G93kvQn0l/N7k1WO70OxrmpQjZyIkVnsnpwS5w1wlPq0eU/XUz";
    public static final String SC_PRIVATE_KEY = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAtmsjEhvLS899pNklFoxm06Glgn2NxfDEiN+lkpuLN7F5RsRd2WorfZ1Ohh16iiw8Tc0d+zugoaGpIdDUYRNWWwIDAQABAkB4p2Y77PxK+UM+TI2WW2kLo0gIhijGelhTu7EUxiKPw4dH125o17PZuG9Mf0Y9xB9CF4exIJavc+IJyUVJN+YpAiEA99pKuO9CZ3HpBb0fyC8fyT7I3w7W7VtqC4BC+mw2Mr8CIQC8ajWDWCUUmUbwEXI87ITVb8vyYUYjg6uCQ/Hjyu/vZQIhAJih9pgyx5r6vDzLfhICazm8j2lGMzeSrxiMETM0hp/zAiEAuxhkZzbCK4BBNua8H89l4GQRi3AYq+92AfbiXicItxkCIQCxVrxkxUIp+nv6s3fQ4u8IeLpZ8d0BzYTNPf9rySW/2g==";
    private static String SECRET = "asus.secret";
    private static String ENCODING = "UTF-8";
    static {
        if (ZLData.SC_API_FLAG){
            PRIVATE_KEY = SC_PRIVATE_KEY;
        }else {
            PRIVATE_KEY = SHENGSHIER_PRIVATE_KEY;
        }
    }

    public static String getSign(Map<String, String> parameters) {
        return getSign(PRIVATE_KEY, parameters);
    }

    /**
     * 通过map参数和私钥获取加密的sign
     * 操作顺序 1、map排序 2、组装参数 3、encodeUTF-8 4、MD5 加密 5、rsa加密 6、encode utf-8
     * @param privateKey 私钥
     * @param parameters 参数map
     * @date 2015年2月11日 上午10:57:16
     * @auth wenb
     */
    public static String getSign(String privateKey,
                                 Map<String, String> parameters) {

        StringBuffer sb = null;
        String parameter = null;
        StringBuffer visitData = null;
        try {
            sb = new StringBuffer();
            visitData = new StringBuffer();
            sb.append(SECRET);
            if (parameters != null && !parameters.isEmpty()) {
                List<String> list = new ArrayList<String>();
                List<String> visitList = new ArrayList<String>();
                Iterator<String> it = parameters.keySet().iterator();
                String key;
                while (it.hasNext()) {
                    key = (String) it.next();
                    list.add(key + parameters.get(key));
                    visitList.add(key + "=" + parameters.get(key) + "&");
                }
                Collections.sort(list);
                for (String s : list) {
                    sb.append(s);
                }

                Collections.sort(visitList);
                for (String s : visitList) {
                    visitData.append(s);
                }
            }
            sb.append(SECRET);
            visitData.delete(visitData.length() - 1, visitData.length());
            logger.info(sb.toString());
            parameter = URLEncoder.encode(sb.toString(), ENCODING);
            parameter = ZLSignMD5Util.encodeMD5(parameter);
            parameter = ZLSignRSAUtil.signRSA(parameter.getBytes(), privateKey);
            parameter = URLEncoder.encode(parameter, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }

    public static String getSign(String sign) {
        return getSign(PRIVATE_KEY, sign);
    }

    /**
     * 通过map参数和私钥获取加密的sign
     * 操作顺序 1、encodeUTF-8 2、MD5 加密 3、rsa加密 4、encode utf-8
     * @param privateKey 私钥
     * @param param 参数string
     * @date 2015年2月11日 上午10:57:16
     * @auth wenb
     */
    public static String getSign(String privateKey,
                                 String param) {

        StringBuffer sb = null;
        String parameter = null;
        try {
            sb = new StringBuffer();
            sb.append(SECRET);
            sb.append(param);
            sb.append(SECRET);
            logger.info(sb.toString());
            parameter = URLEncoder.encode(sb.toString(), ENCODING);
            parameter = ZLSignMD5Util.encodeMD5(parameter);
            parameter = ZLSignRSAUtil.signRSA(parameter.getBytes(), privateKey);
            parameter = URLEncoder.encode(parameter, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }

    public static String getPcSign(String privateKey,
                                 String param) {

        StringBuffer sb = null;
        String parameter = null;
        try {
            sb = new StringBuffer();
            sb.append(SECRET);
            sb.append(param);
            sb.append(SECRET);
            logger.info(sb.toString());
            parameter = URLEncoder.encode(sb.toString(), ENCODING);
            parameter = ZLSignMD5Util.encodeMD5(parameter);
            parameter = URLEncoder.encode(parameter, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }
}
