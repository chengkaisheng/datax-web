package com.wugui.datax.admin.datashare.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.wugui.datax.admin.datashare.entity.TInterface;
import com.wugui.datax.admin.datashare.entity.TInterfaceExamine;
import com.wugui.datax.admin.datashare.entity.TInterfaceExample;
import com.wugui.datax.admin.datashare.entity.TInterfaceWithBLOBs;
import com.wugui.datax.admin.datashare.enums.ShareEnum;
import com.wugui.datax.admin.datashare.mapper.TInterfaceExamineMapper;
import com.wugui.datax.admin.datashare.mapper.TInterfaceMapper;
import com.wugui.datax.admin.datashare.service.ApiService;
import com.wugui.datax.admin.datashare.service.InterfaceInfoSevice;
import com.wugui.datax.admin.datashare.tools.JSONPrinter;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.tools.UUIDUtils;
import com.wugui.datax.admin.datashare.vo.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InterfaceInfoSeviceImpl implements InterfaceInfoSevice {
    private static final Logger logger = LoggerFactory.getLogger(InterfaceInfoSeviceImpl.class);
    @Autowired
    private TInterfaceMapper interfaceMapper;

    @Autowired
    private TInterfaceExamineMapper examineMapper;

    @Autowired
    private ApiService apiService;

    @Override
    public Object getInterfaceInfoPages(InterfaceQuery query) {
        TInterfaceExample example=new TInterfaceExample(TInterface.class);
        TInterfaceExample.Criteria criteria=example.createCriteria();
        criteria.andRegisterCompanyEqualTo(query.getRegisterCompany());
        if(StringUtils.isNotBlank(query.getInterName())){
            criteria.andInterNameLike("%"+query.getInterName()+"%");
        }
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<TInterfaceWithBLOBs> interfaceList=interfaceMapper.selectByExampleWithBLOBs(example);
        int total=interfaceMapper.countByExample(example);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("interfaceList", interfaceList);
        return map;
    }

    @Override
    public Object getInterfaceByinfoId(String id) {
        TInterfaceExample example=new TInterfaceExample(TInterface.class);
        TInterfaceExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(id);
        List<TInterfaceWithBLOBs> interfaceList=interfaceMapper.selectByExampleWithBLOBs(example);
        if(interfaceList.size()>0){
            return interfaceList.get(0);
        }else{
            return new TInterfaceWithBLOBs();
        }
    }

    /**
     * 参数处理 拼装
     * @param param
     * @param request
     * @return
     */
    private TInterfaceWithBLOBs setTInterfaceWithBLOBs(InterfaceParam param, HttpServletRequest request){
        TInterfaceWithBLOBs tInterface=new TInterfaceWithBLOBs();
        tInterface.setId(UUIDUtils.getUUID());
        tInterface.setContacts(param.getContacts());//联系人
        tInterface.setTelephone(param.getTelephone());//联系电话
        tInterface.setRegisterCompany(param.getRegisterCompany());//注册单位
        tInterface.setInterName(param.getInterName());//接口名称
        tInterface.setInterRemark(param.getInterRemark());//接口描述
        /*tInterface.setDataRange(param.getDataRange());//数据范围*/
//        tInterface.setDataCompany(param.getDataCompany());//数源部门
        /*tInterface.setImplMethod(param.getImplMethod());//实现方式*/
        /*tInterface.setBusinessType(param.getBusinessType());//业务类型*/
        /*tInterface.setResponseMode(param.getResponseMode());//返回数据格式*/
        /*tInterface.setDeployMethod(param.getDeployMethod());//部署方式*/
//        tInterface.setRealName(param.getRealName());//实名认证
        /*tInterface.setIsLimit(param.getIsLimit());//是否受限
        tInterface.setProvideService(param.getProvideService());//提供服务*/
        tInterface.setInfoId(param.getInfoId());//资源id
        tInterface.setTableEnglish(param.getTableEnglish());//表名称
        String s=underlineToCamel(param.getTableEnglish());
        String interCode=s.substring(6);
        //System.out.println("转成驼峰之后截取"+interCode);
        tInterface.setInterCode(interCode);//接口编码通过表名称生成
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/api/gateway/"+interCode;
        tInterface.setInterUrl(url);//接口地址

        //请求示例
        List<InputParam> inputParams=param.getInputParams();
        StringBuffer example=new StringBuffer();
        example.append(url+"?");
        for (InputParam inputParam:inputParams) {
            example.append(inputParam.getColumnCode()+"=***&");
        }
        logger.info("拼接得到的字符串"+example.toString());
        //去掉最后一个字符
        tInterface.setExample(example.toString().substring(0,example.toString().length()-1));
        logger.info("处理之后的字符串"+example.toString().substring(0,example.toString().length()-1));

        //成功示例
        StringBuffer outParam=new StringBuffer();
        for (OutputParam outputParam:param.getOutputParams()) {
            outParam.append(outputParam.getFieldCode()+",");
        }
        //去掉最后一个逗号拼接输出参数
        String out=outParam.toString().substring(0,outParam.toString().length()-1);
        //查询一条数据作为成功模板示例
        Map map=apiService.querySucessExamples(param.getId(),param.getTableEnglish(),out);
        String success= JSON.toJSONString(map);
        String json= JSONPrinter.format(success);//json数据排版
        tInterface.setSuccess(json);

        //失败示例
        tInterface.setFail(JSONPrinter.format("{\"code\":\"02\",\"msg\":\"请求参数不能为空\",\"datas\":\"\",\"dataCount\":0}"));
        tInterface.setRequestMethod("post");
        tInterface.setInterVersion("v1.0");
        tInterface.setEncodingFormat("UTF-8");
        //状态码
        tInterface.setStateCode("[{\"code\":\"00\",\"describe\":\"成功\"},{\"code\":\"01\",\"describe\":\"接口编码不能为空\"},{\"code\":\"02\",\"describe\":\"请求参数不能为空\"},{\"code\":\"03\",\"describe\":\"认证不通过\"},{\"code\":\"04\",\"describe\":\"接口不存在\"}]");

        //输入参数
        String inputParam= JSON.toJSONString(param.getInputParams());
        logger.info("得到的输入参数："+inputParam);
        tInterface.setInputParam(inputParam);
        //输出参数
        tInterface.setOutputParam(JSON.toJSONString(param.getOutputParams()));

        tInterface.setInterState(ShareEnum.WAIT_CHECK.getDescribe());
        tInterface.setCreateTime(new Date());
        return tInterface;
    }

    @Override
    public Object insertInterface(InterfaceParam param, HttpServletRequest request) {
        TInterfaceWithBLOBs interfaceWithBLOBs=setTInterfaceWithBLOBs(param,request);
        try {

            interfaceMapper.insertSelective(interfaceWithBLOBs);//注册接口

            TInterfaceExamine interfaceExamine=setTInterfaceExamine(interfaceWithBLOBs);
            examineMapper.insertSelective(interfaceExamine);
            return new Result<>(true,"注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(true,"注册失败");
        }

    }

    /**
     * 拼装接口审核表参数信息
     * @param withBLOBs
     * @return
     */
    private TInterfaceExamine setTInterfaceExamine(TInterfaceWithBLOBs withBLOBs){
        TInterfaceExamine interfaceExamine=new TInterfaceExamine();
        interfaceExamine.setInterId(withBLOBs.getId());
        interfaceExamine.setInterName(withBLOBs.getInterName());
        interfaceExamine.setInterRemark(withBLOBs.getInterRemark());
        interfaceExamine.setRegisterCompany(withBLOBs.getRegisterCompany());
        interfaceExamine.setApplyTime(new Date());
        interfaceExamine.setState(ShareEnum.WAIT_CHECK.getDescribe());
        interfaceExamine.setCreateTime(new Date());
        return interfaceExamine;
    }


    @Override
    public Object getInterfaceDetails(String id) {
        TInterfaceExample example=new TInterfaceExample(TInterface.class);
        TInterfaceExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(id);
        return interfaceMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public TInterfaceWithBLOBs getInterfaceByCode(String interCode) {
        TInterfaceExample example=new TInterfaceExample(TInterface.class);
        TInterfaceExample.Criteria criteria=example.createCriteria();
        criteria.andInterCodeEqualTo(interCode);
        List<TInterfaceWithBLOBs> list=interfaceMapper.selectByExampleWithBLOBs(example);
        if(list.size()>0){
            return interfaceMapper.selectByExampleWithBLOBs(example).get(0);
        }else{
            return null;
        }
    }

    @Override
    public Object getInterfaceResult(ApplyResourceQuery applyResourceQuery) {
        TInterfaceExample example=new TInterfaceExample(TInterface.class);
        TInterfaceExample.Criteria criteria=example.createCriteria();
        criteria.andInterStateEqualTo("审核通过");
        /*if(StringUtils.isNotBlank(applyResourceQuery.getDataCompany())){
            criteria.andDataCompanyEqualTo(applyResourceQuery.getDataCompany());
        }*/
        if(StringUtils.isNotBlank(applyResourceQuery.getInterName())){
            criteria.andInterNameLike("%"+applyResourceQuery.getInterName()+"%");
        }
        PageHelper.startPage(applyResourceQuery.getPageNum(), applyResourceQuery.getPageSize());
        List<TInterfaceWithBLOBs> interfaceResults=interfaceMapper.selectByExampleWithBLOBs(example);
        int total=interfaceMapper.countByExample(example);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("resourceList",interfaceResults);
        return map;
    }


    /**
     * 下划线格式字符串转换为驼峰格式字符串
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }



}
