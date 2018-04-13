package com.thanos.common;

/**
 * Created by Yingjie_Li on 2015/3/11.
 */
public class ZLException extends Exception {
    private Integer code = ZLData.ERROR_CODE;

    public ZLException() {
        super();
    }

    public ZLException(ZLErrorMessage message) {
        super(message.getMsg());
        this.code = message.getCode();
    }

    public ZLException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ZLException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
