package com.thanos.model.toutiao;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Thanos Yu
 * @date 2017/11/24
 */
public class ToutiaoTokenData {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
