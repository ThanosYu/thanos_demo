package com.thanos.model.kdniao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Thanos_Yu
 * @date 2018/3/9.
 */
public class KdNiaoLogistics {

    @JsonProperty("LogisticCode")
    private String logisticCode;

    @JsonProperty("ShipperCode")
    private String shipperCode;

    @JsonProperty("Traces")
    private List<KdNiaoLogisticsTrace> traces;

    @JsonProperty("State")
    private String state;

    @JsonProperty("EBusinessID")
    private String eBusinessID;

    @JsonProperty("Success")
    private String success;

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public List<KdNiaoLogisticsTrace> getTraces() {
        return traces;
    }

    public void setTraces(List<KdNiaoLogisticsTrace> traces) {
        this.traces = traces;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String geteBusinessID() {
        return eBusinessID;
    }

    public void seteBusinessID(String eBusinessID) {
        this.eBusinessID = eBusinessID;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
