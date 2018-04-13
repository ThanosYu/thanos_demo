/**
 * 
 */
package com.thanos.common;

/**
 * Title: framwork
 * Company: HP
 * 
 * @author date13
 * @version 1.3
 * @Last Modify 2012-06-28
 */
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Map;

public class Console {
	private final static Logger file = Logger.getLogger("file");
	private final static Logger console = Logger.getLogger("console");
	
	//默认日志等级为INFO
	static {
		Level logMode = Level.toLevel(Environment.getValue("log_mode"));
		if (logMode.equals(Level.DEBUG)) {
			Logger.getRootLogger().setLevel(Level.DEBUG);
		}else if (logMode.equals(Level.WARN)) {
			Logger.getRootLogger().setLevel(Level.WARN);
		}else if (logMode.equals(Level.ERROR)) {
			Logger.getRootLogger().setLevel(Level.ERROR);
		}
	}

	public static void init() {

	}

	public static void writeInfo(Object message) {
		file.info("writeInfo:  " + message);
	}

	public static void writeWarn(Object message) {
		file.warn("writeWarn:  " + message);
	}
	
	public static void writeErr(Object message) {
		file.error("writeErr:  " + message);
	}

	public static void writeErr(Object object , Throwable t) {
		file.error("writeErr:  " + object, t);
	}

	public static void writeErr(Throwable e) {
		file.error("writeErr:  " , e);
	}

	public static void debug(Object message) {
		console.debug(message);
	}
	
	public static void info(Object message) {
		console.info(message);
	}
	
	public static void warn(Object message) {
		console.warn(message);
	}
	
	public static void error(Object message) {
		console.error(message);
	}


    public static void info(String tag, Map<String, String> params) {
        try{
            StringBuffer sb = new StringBuffer();
            sb.append(tag);
            sb.append(" : ");
            if(params == null) {
                sb.append("null");
            } else {
                for(String key : params.keySet()) {
                    sb.append(key);
                    sb.append("[");
                    sb.append(params.get(key));
                    sb.append("] ");
                }
            }
            console.info(sb.toString());
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
		debug("debug 123");
		info("info 123");
		warn("warn");
		error("error 123");
		writeInfo("write info");
		writeWarn("write warn");
		writeErr("error");
	}
}
