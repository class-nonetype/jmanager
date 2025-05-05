package com.manager.models;

public record AuthenticationResponse(String accessToken, int httpStatusCode) {

    @Override
    public String accessToken() {
        return accessToken;
    }

    @Override
    public int httpStatusCode() {
        return httpStatusCode;
    }

    public boolean isSuccess() {
        return httpStatusCode == 200 && accessToken != null;
    }
}
