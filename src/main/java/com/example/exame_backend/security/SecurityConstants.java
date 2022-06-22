package com.example.exame_backend.security;

public class SecurityConstants {
    public static final String SECRET = "VerySecretiveKey";
//    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final int EXPIRATION_TIME = 100; // 100 years

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String SIGN_UP_URL = "/api/users/create";
    public static final String LOGIN_URL = "/login";




}