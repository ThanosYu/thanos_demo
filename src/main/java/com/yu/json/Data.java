package com.yu.json;

import java.util.List;

/**
 * @author Thanos Yu
 * @date 2018/4/25
 */
public class Data {

    private Integer code;
    private String mag;
    private List<Label> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public List<Label> getData() {
        return data;
    }

    public void setData(List<Label> data) {
        this.data = data;
    }
}
