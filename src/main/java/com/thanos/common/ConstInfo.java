package com.thanos.common;


import org.apache.log4j.Level;

import java.io.File;


@SuppressWarnings("unchecked")
public class ConstInfo {
	//sys config
	public final static Integer HTTP_MAX_CONNECT = Environment.getIntValue("http_max_connect");
	public final static Integer HTTP_TIMEOUT = Environment.getIntValue("http_timeout");
	public final static Integer PAGE_SIZE = Environment.getIntValue("page_size");
	public final static Level LOG_LEVEL = Level.toLevel(Environment.getValue("log_mode"));
	public final static String PROP = Environment.getValue("prop");
	public final static String SYS_SESSION_NAME=Environment.getValue("sys_session_name");
	
	public final static String HTTP_PROXY_HOST = Environment.getValue("http_proxy_host");
	public final static Integer HTTP_PROXY_PORT = StrUtil.nullToInt(Environment.getValue("http_proxy_port"),-1);
	public final static String SEP=File.separator;
	public static final String ENCODING = "UTF-8";
	public static final String OS_NAME = System.getProperty("os.name");
	public static String WEB_ROOT_PATH = null;

	static{
		String temp = ConstInfo.class.getResource("/system_config.xml").getPath();
		int start = temp.indexOf("/WEB-INF/");
		if(start!=-1){
			WEB_ROOT_PATH = temp.substring(0,start);
		}
	}
	public static void main(String[] args) {
		Console.info(ConstInfo.SEP);
	}
}
