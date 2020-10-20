package com.wugui.datax.admin.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by iwlnner on 2020/3/23.
 * 文件上传工具类，待优化
 */
public class UploadUtils {
    /**
     *
     * @param url 文件保存地址
     * @param serviceUrl 访问图片地址
     * @param imgFile 图片二进制类
     * @return
     */
    public static Map<String, Object> uploadImage(String url,String serviceUrl,MultipartFile imgFile){
        File file=new File(url);
        if(!file.exists()){
            getError(1,"图片保存路径不存在");
        }
        List extList=new ArrayList(){{
            this.add("gif");
            this.add("jpg");
            this.add("jpeg");
            this.add("png");
            this.add("bmp");
        }};
            String SimpleOriginalName=imgFile.getOriginalFilename();
            String fileExt = SimpleOriginalName.substring(SimpleOriginalName.lastIndexOf(".") + 1).toLowerCase();
            if(!extList.contains(fileExt)){
                return getError(1,"上传图片格式错误");//TODO
            }
            if(imgFile.getSize()>10485760){
                return getError(1,"上传图片大小超过限制");
            }
            String newFileName= UUID.randomUUID().toString()+"."+fileExt;
            //定义图片保存到服务器的地址
            String saveUrl=url+newFileName;
            try{
                Files.copy(imgFile.getInputStream(), Paths.get(saveUrl));
            }catch (Exception e){
                e.printStackTrace();
            }
            //模拟服务器
            return getError(0,serviceUrl+newFileName);
    }

    private static Map<String,Object> getError(Integer error,String message) {
        Map<String,Object> successMap=new HashMap<>();
        Map<String,Object> errorMap=new HashMap<>();
        if(0==error){
            successMap.put("error",0);
            successMap.put("url",message);
            return successMap;
        }
        errorMap.put("error",1);
        errorMap.put("message",message);
        return errorMap;
    }
}
