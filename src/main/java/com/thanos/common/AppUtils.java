package com.thanos.common;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by date13 on 15/3/17.
 */
public class AppUtils {


    public static Map<String, String> prepareParameters(MultivaluedMap<String, String> queryParameters) {

        Map<String, String> parameters = new HashMap<String, String>();

        Iterator<String> it = queryParameters.keySet().iterator();


        while (it.hasNext()) {
            String theKey = it.next();
            parameters.put(theKey, queryParameters.getFirst(theKey));
        }

        return parameters;

    }

    public static <T> T convertRequestBody(HttpServletRequest request,Class<T> cls){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(request.getInputStream(),cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertBean(Object target){
        StringWriter sw = new StringWriter();
        try {
            JsonGenerator gen = new JsonFactory().createGenerator(sw);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(gen, target);
            gen.close();
            return sw.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Console.writeErr(e);
            e.printStackTrace();
        }
        return null;

    }

    public static String getRemoteAddress(HttpServletRequest request) {
        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                ip = request.getRemoteAddr();
            }
            return ip;
        } catch (Exception e) {

        }
        return "";
    }

}
