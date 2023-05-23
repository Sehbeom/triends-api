package com.ssafy.triends.global.util.jwt;

import java.util.Map;

public interface JwtOperator {
    <T> String createAccessToken(String key, T data);
    <T> String createRefreshToken(String key, T data);
    <T> String create(String key, T data, String subject, long expir);
    Map<String, Object> get(String key);
    String getUserId();
    boolean checkToken(String jwt);
}
