package com.example.keycloakapp.models;

import com.google.gson.annotations.SerializedName;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;


public class Token {

    @SerializedName(value = "access_token")
    private String accessToken;

    @SerializedName(value = "expires_in")
    private Integer expiredIn;

    @SerializedName(value = "refresh_expires_in")
    private Integer refreshExpiresIn;

    @SerializedName(value = "refresh_token")
    private String refreshToken;

    @SerializedName(value = "token_type")
    private String tokenType;

    @SerializedName(value = "not-before-policy")
    private Integer notBeforePolicy;

    @SerializedName(value = "session_state")
    private String sessionState;

    @SerializedName(value = "scope")
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(Integer expiredIn) {
        this.expiredIn = expiredIn;
    }

    public Integer getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(Integer refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getNotBeforePolicy() {
        return notBeforePolicy;
    }

    public void setNotBeforePolicy(Integer notBeforePolicy) {
        this.notBeforePolicy = notBeforePolicy;
    }

    public String getSessionState() {
        return sessionState;
    }

    public void setSessionState(String sessionState) {
        this.sessionState = sessionState;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", expiredIn=" + expiredIn +
                ", refreshExpiresIn=" + refreshExpiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", notBeforePolicy=" + notBeforePolicy +
                ", sessionState='" + sessionState + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
