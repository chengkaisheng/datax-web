package com.wugui.datax.executor.util;

import org.apache.commons.httpclient.HttpClient;

public class HttpClientUtil {

    private static HttpClient instance = new HttpClient();

    private HttpClientUtil(){}

    public static HttpClient getInstance(){
        return instance;
    }
}