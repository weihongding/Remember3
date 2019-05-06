package com.example.remember.util;

public class ConstResponse {

    public static final int STATUS_OK = 1000;
    public static final int STATUS_KNOWN_ERROR = 1001;
    public static final int STATUS_PARAM_ERROR = 1002;

    public static final int unread_true = 1;//有新信息
    public static final int unread_false = 0;//无新信息

    public static final int linkFail = 100860;
    public static final int loginSuccess = 100861;
    public static final int regSuccess = 100862;
    public static final int unreadSuccess = 100863;

    public static final String DESC_OK = "OK";
    public static final String DESC_PARAM = "param error";
}
