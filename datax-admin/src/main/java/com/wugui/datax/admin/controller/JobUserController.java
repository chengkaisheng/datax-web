package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.constans.Constant;
import com.wugui.datax.admin.entity.JobUser;
import com.wugui.datax.admin.service.JobUserRoleService;
import com.wugui.datax.admin.service.JobUserService;
import org.apache.commons.lang.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-12-16-14:13
 */
public class JobUserController extends BaseController{
    @Autowired
    private JobUserService jobUserService;

    @Autowired
    private JobUserRoleService jobUserRoleService;

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        //只有超级管理员，才能查看所有管理员列表
        if(getCurrentUserId(request) != Constant.SUPER_ADMIN){
        params.put("createUserId", getCurrentUserId(request));
        }
        return R.ok(jobUserService.queryPage(params));
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public R info(HttpServletRequest request){
        return R.ok(jobUserService.getById(getCurrentUserId(request)));
    }

    /**
     * 修改登录用户密码
    @PostMapping("/password")
    public R password(@RequestBody PasswordForm form){
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

        //更新密码
        boolean flag = jobUserService.updatePassword(getUserId(), password, newPassword);
        if(!flag){
            return R.failed("原密码不正确");
        }

        return R.ok();
    }*/

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        JobUser user = jobUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = jobUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok(user);
    }

    /**
     * 保存用户
     */
    @PostMapping("/save")
    public R save(@RequestBody JobUser user, HttpServletRequest request){

        user.setCreateUserId(getCurrentUserId(request).longValue());
        jobUserService.saveUser(user);

        return R.ok("保存成功");
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public R update(@RequestBody JobUser user, HttpServletRequest request){
        user.setCreateUserId(getCurrentUserId(request).longValue());
        jobUserService.update(user);

        return R.ok("更新成功");
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] userIds, HttpServletRequest request){
        if(ArrayUtils.contains(userIds, 1L)){
            return R.failed("系统管理员不能删除");
        }

        if(ArrayUtils.contains(userIds, getCurrentUserId(request).longValue())){
            return R.failed("当前用户不能删除");
        }

        jobUserService.deleteBatch(userIds);

        return R.ok("删除成功");
    }
}
