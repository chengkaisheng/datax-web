package com.wugui.datax.admin.datashare.service.impl;

import com.github.pagehelper.PageHelper;

import com.wugui.datax.admin.datashare.entity.*;
import com.wugui.datax.admin.datashare.enums.ShareEnum;
import com.wugui.datax.admin.datashare.mapper.TInterfaceExamineMapper;
import com.wugui.datax.admin.datashare.mapper.TInterfaceMapper;
import com.wugui.datax.admin.datashare.service.InterfaceExamSevice;
import com.wugui.datax.admin.datashare.tools.JwtToken;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.vo.InterfaceExamParam;
import com.wugui.datax.admin.datashare.vo.InterfaceQuery;
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
public class InterfaceExamSeviceImpl implements InterfaceExamSevice {
    @Autowired
    private TInterfaceExamineMapper examineMapper;

    @Autowired
    private TInterfaceMapper interfaceMapper;

    @Override
    public Object getInterfaceExamPage(InterfaceQuery query) {
        TInterfaceExamineExample example=new TInterfaceExamineExample();
        TInterfaceExamineExample.Criteria criteria=example.createCriteria();
        criteria.andRegisterCompanyEqualTo(query.getRegisterCompany());
        if(StringUtils.isNotBlank(query.getInterName())){
            criteria.andInterNameLike("%"+query.getInterName()+"%");
        }
        if(StringUtils.isNotBlank(query.getState())){
            criteria.andStateEqualTo(query.getState());
        }

        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<TInterfaceExamine> examineList=examineMapper.selectByExample(example);
        int total=examineMapper.countByExample(example);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("examineList", examineList);
        return map;
    }

    @Override
    public Object approvalInterface(InterfaceExamParam param) {
        /*Token token=null;
        try {
            token= JwtToken.unsign(param.getToken(),Token.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(false,"token异常，请重新登录");
        }*/
        TInterfaceExamineExample examineExample=new TInterfaceExamineExample();
        TInterfaceExamineExample.Criteria criteria=examineExample.createCriteria();
        criteria.andInterIdEqualTo(param.getInterId());

        TInterfaceExample example=new TInterfaceExample(TInterface.class);
        TInterfaceExample.Criteria criteria1=example.createCriteria();
        criteria1.andIdEqualTo(param.getInterId());
        try {
            TInterfaceExamine examine=new TInterfaceExamine();
            examine.setReviewer(param.getApplyUserName());//审核人
            examine.setExaTime(new Date());//审核时间

            TInterfaceWithBLOBs withBLOBs=new TInterfaceWithBLOBs();
            if(param.getExaResult().equals("0")){//同意
                //修改审批
                examine.setState(ShareEnum.GREED.getDescribe());//审核状态已同意
                examine.setExaDescribe(param.getExaDescribe());//审核意见
                examineMapper.updateByExampleSelective(examine,examineExample);

                //修改接口的状态
                withBLOBs.setInterState(ShareEnum.CHECK_PASS.getDescribe());
                interfaceMapper.updateByExampleSelective(withBLOBs,example);
            }else{//驳回
                examine.setState(ShareEnum.REJECT.getDescribe());
                examine.setExaDescribe(param.getExaDescribe());//审核意见
                examineMapper.updateByExampleSelective(examine,examineExample);


                withBLOBs.setInterState(ShareEnum.CHECK_NOPASS.getDescribe());
                interfaceMapper.updateByExampleSelective(withBLOBs,example);
            }
            return new Result<>(true,"审批成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(true,"审批失败");
        }
    }

    @Override
    public Object getInterfaceExamById(String id) {
        TInterfaceExamineExample example=new TInterfaceExamineExample();
        TInterfaceExamineExample.Criteria criteria=example.createCriteria();
        criteria.andInterIdEqualTo(id);
        return examineMapper.selectByExample(example);
    }


}
