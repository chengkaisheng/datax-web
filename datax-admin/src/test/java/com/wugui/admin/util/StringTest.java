package com.wugui.admin.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author hf
 * @creat 2020-10-12-11:12
 */
public class StringTest {

    @Test
    public void test(){
        /*String str = "12312312";
        String[] split = str.split(".");
        System.out.println(Arrays.toString(split));*/
        String str = "ahsjdkahskd {asdasjkldajlk { hjashd{}}}}";
        int i = str.indexOf('{');
        System.out.println(str.charAt(i));
    }
}
