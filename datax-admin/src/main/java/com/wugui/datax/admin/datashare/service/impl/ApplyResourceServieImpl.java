package com.wugui.datax.admin.datashare.service.impl;

import com.auth0.jwt.exceptions.TokenExpiredException;

import com.github.pagehelper.PageHelper;
import com.wugui.datax.admin.datashare.entity.*;
import com.wugui.datax.admin.datashare.enums.ShareEnum;
import com.wugui.datax.admin.datashare.mapper.TApplyResourceMapper;
import com.wugui.datax.admin.datashare.mapper.TInterfaceMapper;
import com.wugui.datax.admin.datashare.mapper.TResourceExamineMapper;
import com.wugui.datax.admin.datashare.service.ApplyResourceServie;
import com.wugui.datax.admin.datashare.tools.JwtToken;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.tools.UUIDUtils;
import com.wugui.datax.admin.datashare.vo.ApplyResourceQuery;
import com.wugui.datax.admin.datashare.vo.ResourceParam;
import com.wugui.datax.admin.datashare.vo.Token;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.types.resources.ResourceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ApplyResourceServieImpl implements ApplyResourceServie {

    private static final Logger log= LoggerFactory.getLogger(ApplyResourceServieImpl.class);

    @Value("/home/dw/dpm/services/dir/file/")
    private String fileSavePath;

    @Autowired
    private TApplyResourceMapper resourceMapper;

    @Autowired
    private TInterfaceMapper interfaceMapper;

    @Autowired
    private TResourceExamineMapper examineMapper;

    @Override
    public Object getResourceList(ApplyResourceQuery applyResourceQuery) {
        PageHelper.startPage(applyResourceQuery.getPageNum(), applyResourceQuery.getPageSize());
        List<ResourceList> resourceList=resourceMapper.getResourceList(applyResourceQuery);
        int total=resourceMapper.getResourceListTotal(applyResourceQuery);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("resourceList",resourceList);
        return map;
    }

    /**
     * 提交申请并上传申请文件
     * @param file
     * @param request
     * @param resourceParam
     * @return
     */
    @Override
    public Object applyAnduploadFile(MultipartFile file, HttpServletRequest request, ResourceParam resourceParam) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        //1.后半段目录：以日期为标准生成目录文件夹  2020/06/24
        String directory = simpleDateFormat.format(new Date());
        // 2.文件保存目录  注意程序运行环境，window和linux目录结构不同，上传文件保存地址配置不一样
        //如果目录不存在，则创建
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info("图片上传，保存位置：" + fileSavePath + directory);
        //3.给文件重新设置一个名字，避免文件重名
        //后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(fileSavePath + directory + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);
            //协议://ip地址:端口号/文件目录(/file/2020/06/24/xxx.jpg) 这里的/file/目录跟CorsConfig配置类里资源路径保持一致
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + directory + newFileName;
            log.info("图片上传，访问URL：" + url);
            //保存我的申请信息记录
            TApplyResource applyResource=this.setApplyResource(resourceParam,url);
            resourceMapper.insertSelective(applyResource);

            //修改接口申请状态
            TInterfaceExample example=new TInterfaceExample(TInterface.class);
            example.createCriteria().andIdEqualTo(resourceParam.getInterId());
            TInterfaceWithBLOBs tInterface=new TInterfaceWithBLOBs();
            tInterface.setIsApply(ShareEnum.WAIT_CHECK.getDescribe());
            interfaceMapper.updateByExampleSelective(tInterface,example);

            //提交申请到审核表
            TResourceExamine examine=this.setResourceExamine(resourceParam,applyResource);
            examineMapper.insertSelective(examine);
            return new Result<>(true,"提交成功");
        } catch (IOException e) {
            return new Result<>(false,"提交失败");
        }
    }

    @Override
    public Object getMyApplyResource(ApplyResourceQuery applyResourceQuery) {
        TApplyResourceExample example=new TApplyResourceExample(TApplyResource.class);
        TApplyResourceExample.Criteria criteria=example.createCriteria();
        try {
            Token token= JwtToken.unsign(applyResourceQuery.getToken(), Token.class);
            criteria.andUserIdEqualTo(token.getUser().getUid());
        } catch (TokenExpiredException | IOException e) {
            //e.printStackTrace();
            return new Result<>(false,"token异常，请重新登录");
        }
        if(StringUtils.isNotBlank(applyResourceQuery.getInfoName())){
            criteria.andInfoNameLike("%"+ applyResourceQuery.getInfoName()+"%");
        }
        int total=resourceMapper.countByExample(example);
        PageHelper.startPage(applyResourceQuery.getPageNum(),applyResourceQuery.getPageSize());
        List<TApplyResource> resourceList=resourceMapper.selectByExample(example);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("resourceList",resourceList);
        return new Result<>(true,map);
    }

    //拼装参数
    public TApplyResource setApplyResource(ResourceParam resourceParam,String url) throws IOException {
        TApplyResource applyResource=new TApplyResource();
        applyResource.setId(UUIDUtils.getUUID());
        Token t= JwtToken.unsign(resourceParam.getToken(),Token.class);//解析token
        applyResource.setInterId(resourceParam.getInterId());
        applyResource.setUserId(t.getUser().getUid());
        applyResource.setInfoId(resourceParam.getInfoId());
        applyResource.setInfoName(resourceParam.getInfoName());
        applyResource.setInfoExtract(resourceParam.getInfoExtract());
        applyResource.setContacts(resourceParam.getContacts());
        applyResource.setTelephone(resourceParam.getTelephone());
        applyResource.setResType(resourceParam.getResType());
        applyResource.setApplyImg(url);//图片上传url
        applyResource.setUseScene(resourceParam.getUseScene());
        applyResource.setState(ShareEnum.SUMBIT_CHECK.getDescribe());
        applyResource.setCreateTime(new Date());
        return applyResource;
    }

    public TResourceExamine setResourceExamine(ResourceParam resourceParam,TApplyResource applyResource) throws IOException {
        TResourceExamine examine=new TResourceExamine();
        Token t= JwtToken.unsign(resourceParam.getToken(),Token.class);//解析token
        examine.setId(UUIDUtils.getUUID());
        examine.setResId(applyResource.getId());
        examine.setUserId(t.getUser().getUid());
        examine.setDepartmentName(t.getOrganize().getName());
        examine.setInfoId(applyResource.getInfoId());
        examine.setInfoName(applyResource.getInfoName());
        examine.setContainName(resourceParam.getDepartment());//被申请的组织部门
        examine.setContacts(applyResource.getContacts());
        examine.setTelephone(applyResource.getTelephone());
        examine.setApplyTime(new Date());
        examine.setResType(applyResource.getResType());
        examine.setApplyImg(applyResource.getApplyImg());
        examine.setUseScene(applyResource.getUseScene());
        examine.setState(ShareEnum.WAIT_CHECK.getDescribe());
        examine.setCreateTime(new Date());
        return examine;
    }


}
