package com.wugui.datax.admin.util;

import com.google.inject.internal.cglib.reflect.$FastMethod;

/**
 * @author hf
 * @creat 2020-09-29-14:30
 */
public class DriverIdBuilder {
    private static final String MYSQL_DRIVER_ID = "mysql:mysql8";

    public static String getDriverId(String datasourceName){
        if("mysql".equals(datasourceName)){
            return MYSQL_DRIVER_ID;
        }
        else {
            throw new RuntimeException("不支持当前数据库");
        }
    }
}
