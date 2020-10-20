package com.wugui.datax.admin.util;

import org.apache.commons.httpclient.HttpClient;

public class HttpClientUtil {

    private static HttpClient instance = new HttpClient();

    private HttpClientUtil(){}

    public static HttpClient getInstance(){
        return instance;
    }
}