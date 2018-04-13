package com.thanos.model.kdniao;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Thanos_Yu
 * @date 2018/3/9.
 */
public class KdNiaoLogisticsTrace {

    @JsonProperty("AcceptStation")
    private String acceptStation;

    @JsonProperty("AcceptTime")
    private String acceptTime;

    public String getAcceptStation() {
        return acceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
}
