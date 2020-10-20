package com.wugui.datax.admin.datashare.tools;

import java.util.Date;
import java.util.UUID;

public class UUIDUtils {
    private static Date date = new Date();
    private static StringBuilder buf = new StringBuilder();
    private static int seq = 0;
    private static final int ROTATION = 99999;

    /**
     * 标识符
     * 年月日时分秒+5位序号
     */
    public static long randomIdenti(){
        if (seq > ROTATION) seq = 0;
        buf.delete(0, buf.length());
        date.setTime(System.currentTimeMillis());
        String str = String.format("%1$ty%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
        return Long.parseLong(str);
    }


    /**
     * 生成id
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


}
