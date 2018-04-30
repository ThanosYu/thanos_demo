package com.thanos.common;
/**
 * Title: date-framework
 * Description: date-framework
 * Copyright: Copyright (c) 2006
 * 
 * @author date13
 * @version 1.1
 * @Last Modify 2009-9-13
 */

import org.dom4j.Element;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.Properties;

public class Environment {
    private static String envName = "zenlife";
    //public static String realPath="";
    private static Properties systemParam = null;
    private static Properties systemProp = null;
	private static final String ENV_FILE = "/system_config.xml";
	//private static final File PRO_FILE = new File(Environment.class.getResource("/sys_prop.properties").getPath());
	static {
		initParams(null);
	}

	public static synchronized void initParams(String fname) {
		try {
			if(fname == null || fname.equals("")){
				fname = ENV_FILE;
			}
			String path = Environment.class.getResource(fname).getPath();

			File file = new File(URLDecoder.decode(path,"UTF-8"));
			//realPath = path.substring(0,path.indexOf("WEB-INF"));

			if(file != null){
				systemParam = new Properties();
				XMLParse xp = new XMLParse();
				xp.setFile(file);
				xp.parseXML();
				xp.search("//param-configuration/server-config");

				while (xp.next()) {
					String serverName = xp.getAttributeValue("name");
					try {
						if(xp.getAttributeValue("default").equals("true")){
							envName = serverName;
						}
					} catch (Exception e) {}

					@SuppressWarnings("unchecked")
					List<Element> paramList = xp.getSubElementList("property");
					for (int i = 0; i < paramList.size(); i++) {
						Element ele = paramList.get(i);
						String attribute = serverName + "-"
								+ ele.attributeValue("name");
						String value = ele.getTextTrim();
						systemParam.setProperty(attribute, value);
					}
				}
			}
			systemProp = new Properties();
			//systemProp.load(new FileInputStream(PRO_FILE));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return getValue(envName, key);
	}

	public static int getIntValue(String key){
		try {
			return Integer.parseInt(getValue(envName, key));
		} catch (Exception e) {}
		return 0;
	}

	public static Long getLongValue(String key){
		try {
			return Long.parseLong(getValue(envName, key));
		} catch (Exception e) {}
		return 0l;
	}

	public static boolean getBooleanValue(String key){
		return getBooleanValue(envName,key);
	}


	public static String getValue(String serverName, String key) {
		return systemParam.getProperty(serverName+"-"+key);
	}

	public static int getIntValue(String serverName, String key) {
		try {
			return Integer.parseInt(systemParam.getProperty(serverName+"-"+key));
		} catch (Exception e) {}
		return 0;
	}

	public static boolean getBooleanValue(String serverName,String key){
		try {
			return Boolean.parseBoolean(systemParam.getProperty(serverName+"-"+key));
		} catch (Exception e) {}
		return false;
	}

	public static String getPropValue(String key) {
		return systemProp.getProperty(key);
	}
	public static Properties getSystemProps() {
		return systemProp;
	}
	/*public static Integer setPropValue(String key,String value){
		systemProp.setProperty(key, value);
		FileOutputStream out;
		try {
			out = new FileOutputStream(PRO_FILE);
			systemProp.store(out, "modify sys properties");
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}*/

	public static void main(String [] args){
		System.out.println(Environment.getIntValue("http_timeout"));
        //Environment.setPropValue("exchange_rate", "6.121");
	}
}
