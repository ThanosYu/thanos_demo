package com.thanos.common;

public class ZLResult<T> {
	private Integer code = ZLData.SUCCESS_CODE;
	private T msg;
	
	public ZLResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZLResult(Integer code, T msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

    public static <T> ZLResult<T> success(){
        return new ZLResult(ZLData.SUCCESS_CODE, ZLData.OK);
    }

    public static <T> ZLResult<T> success(T msg){
		return new ZLResult(ZLData.SUCCESS_CODE, msg);
	}

    public static <T> ZLResult<T> failed(Integer code, T msg){
		return new ZLResult(code, msg);
	}

    public static <T> ZLResult<T> failed(ZLErrorMessage message){
        return new ZLResult(message.getCode(), message.getMsg());
    }

	public static  ZLResult failed(Integer code){
		return new ZLResult(code, "");
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getMsg() {
		return msg;
	}
	public void setMsg(T msg) {
		this.msg = msg;
	}
}
