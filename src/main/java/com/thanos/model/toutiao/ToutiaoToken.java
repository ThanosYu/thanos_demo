package com.thanos.model.toutiao;

/**
 * @author Thanos Yu
 * @date 2017/11/24
 */
public class ToutiaoToken {

    private String msg;

    private ToutiaoTokenData data;

    private Integer ret;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ToutiaoTokenData getData() {
        return data;
    }

    public void setData(ToutiaoTokenData data) {
        this.data = data;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }
}
