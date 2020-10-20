package com.wugui.datax.admin.datashare.service.impl;

import com.github.pagehelper.PageHelper;
import com.wugui.datax.admin.datashare.entity.*;
import com.wugui.datax.admin.datashare.enums.ShareEnum;
import com.wugui.datax.admin.datashare.mapper.TApplyResourceMapper;
import com.wugui.datax.admin.datashare.mapper.TInterfaceMapper;
import com.wugui.datax.admin.datashare.mapper.TResourceExamineMapper;
import com.wugui.datax.admin.datashare.service.ResourceExamineService;
import com.wugui.datax.admin.datashare.tools.JwtToken;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.vo.ResourceExamParam;
import com.wugui.datax.admin.datashare.vo.ResourceExamQuery;
import com.wugui.datax.admin.datashare.vo.Token;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceExamineServiceImpl implements ResourceExamineService {
    @Autowired
    private TResourceExamineMapper resourceExamineMapper;

    @Autowired
    private TInterfaceMapper interfaceMapper;

    @Autowired
    private TApplyResourceMapper applyResourceMapper;

    @Override
    public Object getTResourceExaminePages(ResourceExamQuery query) {
        TResourceExamineExample examineExample=new TResourceExamineExample(TResourceExamine.class);
        TResourceExamineExample.Criteria criteria=examineExample.createCriteria();
        criteria.andContainNameEqualTo(query.getContainName());//查询当前部门的接口申请
        if(StringUtils.isNotBlank(query.getInfoName())){
            criteria.andInfoNameLike("%"+query.getInfoName()+"%");
        }
        if (query.getStartTime() != null && query.getEndTime() != null) {//根据申请日期查询
            criteria.andApplyTimeBetween(query.getStartTime(), query.getEndTime());
        }
        if(StringUtils.isNotBlank(query.getState())){
            criteria.andStateEqualTo(query.getState());
        }
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        int total=resourceExamineMapper.countByExample(examineExample);
        List<TResourceExamine> resourceExamineList=resourceExamineMapper.selectByExample(examineExample);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("resourceExamineList",resourceExamineList);
        return map;
    }

    @Override
    public Object approveResource(ResourceExamParam param) {
        Token token=null;
        try {
             token= JwtToken.unsign(param.getToken(),Token.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(false,"token异常，请重新登录");
        }

        TResourceExamineExample examineExample=new TResourceExamineExample(TResourceExamine.class);
        TResourceExamineExample.Criteria criteria=examineExample.createCriteria();
        criteria.andResIdEqualTo(param.getResId());

        TApplyResourceExample applyExample=new TApplyResourceExample(TApplyResource.class);
        TApplyResourceExample.Criteria applyCriteria=applyExample.createCriteria();
        applyCriteria.andIdEqualTo(param.getResId());

        List<TApplyResource> resourceList=applyResourceMapper.selectByExample(applyExample);
        try {
            if(param.getExaResult().equals("0")){ //同意
                TResourceExamine resourceExamine=new TResourceExamine();
                resourceExamine.setState(ShareEnum.GREED.getDescribe());
                resourceExamine.setExaDescribe(param.getExaDescribe());//审批意见
                resourceExamine.setReviewer(token.getUser().getUsername());//审批人
                resourceExamine.setExaTime(new Date());//审批时间
                resourceExamineMapper.updateByExampleSelective(resourceExamine,examineExample);

                //修改我的资源申请状态
                TApplyResource applyResource=new TApplyResource();
                applyResource.setState(ShareEnum.Online.getDescribe());
                applyResourceMapper.updateByExampleSelective(applyResource,applyExample);

                //修改接口申请状态
                if(resourceList.size()>0){
                    TInterfaceExample example=new TInterfaceExample(TInterface.class);
                    example.createCriteria().andIdEqualTo(resourceList.get(0).getInterId());
                    TInterfaceWithBLOBs tInterface=new TInterfaceWithBLOBs();
                    tInterface.setIsApply(ShareEnum.YES_APPLY.getDescribe());
                    interfaceMapper.updateByExampleSelective(tInterface,example);
                }


            }else{//驳回
                TResourceExamine resourceExamine=new TResourceExamine();
                resourceExamine.setState(ShareEnum.REJECT.getDescribe());//已驳回
                resourceExamine.setExaDescribe(param.getExaDescribe());//审批意见
                resourceExamine.setReviewer(token.getUser().getUsername());//审批人
                resourceExamine.setExaTime(new Date());//审批时间
                resourceExamineMapper.updateByExampleSelective(resourceExamine,examineExample);

                //修改我的资源申请状态
                TApplyResource applyResource=new TApplyResource();
                applyResource.setState(ShareEnum.CHECK_NOPASS.getDescribe());
                applyResourceMapper.updateByExampleSelective(applyResource,applyExample);

                //修改接口申请状态
                if(resourceList.size()>0){
                    TInterfaceExample example=new TInterfaceExample(TInterface.class);
                    example.createCriteria().andIdEqualTo(resourceList.get(0).getInterId());
                    TInterfaceWithBLOBs tInterface=new TInterfaceWithBLOBs();
                    tInterface.setIsApply(ShareEnum.NO_APPLY.getDescribe());
                    interfaceMapper.updateByExampleSelective(tInterface,example);
                }
            }
            return new Result<>(true,"审批成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(false,"审批失败");
        }

    }

    @Override
    public Object getResourceExamineByResId(String resId) {
        TResourceExamineExample examineExample=new TResourceExamineExample(TResourceExamine.class);
        TResourceExamineExample.Criteria criteria=examineExample.createCriteria();
        criteria.andResIdEqualTo(resId);
        List<TResourceExamine> resourceExamineList=resourceExamineMapper.selectByExample(examineExample);
        return resourceExamineList;
    }
}
