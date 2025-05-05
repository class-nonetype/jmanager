package com.manager.models;

public class Session {
    private static String accessToken;

    public static void setAccessToken(String token) {
        accessToken = token;
    }

    public static String getAccessToken() {
        return accessToken;
    }
}
