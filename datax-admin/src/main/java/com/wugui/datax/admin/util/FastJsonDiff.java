package com.wugui.datax.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

import java.util.*;

public class FastJsonDiff {
    public static List<String> contrast = Collections.synchronizedList(new ArrayList<>());

    @SuppressWarnings("unchecked")
    public static void compareJson(JSONObject json1, JSONObject json2, String key) {
        if((json1.size() != json2.size()) && key != null){
            contrast.add(key);
            return;
        }
        Iterator<String> j = json2.keySet().iterator();
        Iterator<String> i = json1.keySet().iterator();
        String temp = key;
        while (i.hasNext() && j.hasNext()) {
            String next1 = i.next();
            String next2 = j.next();
            if(!Objects.equals(next1, next2)){
                contrast.add(key);
                return;
            }
            if(key == null){
                key = next1;
            }else {
                key = key + "." + next1;
            }
            /*key = i.next();*/
            compareJson(json1.get(next1), json2.get(next1), key);
            //精髓，重新赋值给key，避免递归后对key产生影响
            key = temp;
        }
//        return sb.toString();
    }
 
    public static void compareJson(Object json1, Object json2, String key) {
        if (json1 instanceof JSONObject) {
//            System.out.println("this JSONObject----" + key);
            if(((JSONObject) json1).size() != ((JSONObject) json2).size()){
                contrast.add(key);
                return;
            }
            compareJson((JSONObject) json1, (JSONObject) json2, key);
        } else if (json1 instanceof JSONArray) {
//            System.out.println("this JSONArray----" + key);
            compareJson((JSONArray) json1, (JSONArray) json2, key);
        } else if (json1 instanceof String) {
//            System.out.println("this String----" + key);
//            compareJson((String) json1, (String) json2, key);
            try {
                String json1ToStr = json1.toString();
                String json2ToStr = json2.toString();
                compareJson(json1ToStr, json2ToStr, key);
            } catch (Exception e) {
                System.out.println("转换发生异常 key:" + key);
                e.printStackTrace();
            }
        }
        else {
//            System.out.println("this other----" + key);
            compareJson(json1==null?null:json1.toString(), json2==null?null:json2.toString(), key);
        }
    }
 
    public static void compareJson(String str1, String str2, String key) {
        if (str1 == null && str2 == null) {
            System.out.println("一致并都为空：key:" + key + ",json1:" + str1 + ",json2:" + str2);
            return;
        } else if (str1 == null) {
            contrast.add(key);
            System.err.println("不一致：key:" + key + "  在json1中不存在");
            return;
        } else if (str2 == null) {
            contrast.add(key);
            System.err.println("不一致：key:" + key + "  在json2中不存在");
            return;
        }

        StringBuffer sb = new StringBuffer();
        if (!str1.equals(str2)) {
            sb.append("key:"+key+ ",json1:"+ str1 +",json2:"+str2);
            contrast.add(key);
            System.err.println("不一致key:" + key + ",json1:" + str1 + ",json2:" + str2);
        } else {
            System.out.println("一致：key:" + key + ",json1:" + str1 + ",json2:" + str2);
        }
    }
 
    public static void compareJson(JSONArray json1, JSONArray json2, String key) {
        if (json1 != null && json2 != null) {
            Iterator i1 = json1.iterator();
            Iterator i2 = json2.iterator();
            if( (i1.hasNext() && !i2.hasNext()) || (!i1.hasNext() && i2.hasNext()) || json1.size() != json2.size()){
                contrast.add(key);
            }
            while (i1.hasNext() && i2.hasNext()) {
                compareJson(i1.next(), i2.next(), key);
            }
        } else {
            if (json1 == null && json2 == null) {
                System.err.println("一致：key:" + key + "  在json1和json2中均不存在");
            } else if (json1 == null) {
                contrast.add(key);
                System.err.println("不一致：key:" + key + "  在json1中不存在");
            } else if (json2 == null) {
                contrast.add(key);
                System.err.println("不一致：key:" + key + "  在json2中不存在");
            } else {
                contrast.add(key);
                System.err.println("不一致：key:" + key + "  未知原因");
            }
        }
    }
 
    private final static String st1 = "{\"username\":\"tom\",\"age\":18,\"address\":[{\"province\":\"上海市\"},{\"city\":\"上海市\"},{\"disrtict\":\"静安区\"}]}";
    private final static String st2 = "{username:\"tom\",age:18}";

    public static void main(String[] args) {
        System.out.println(st1);
        JSONObject jsonObject1 = JSONObject.parseObject(st1);
        JSONObject jsonObject2 = JSONObject.parseObject(st2);
        compareJson(jsonObject1, jsonObject2, null);
    }
 
}