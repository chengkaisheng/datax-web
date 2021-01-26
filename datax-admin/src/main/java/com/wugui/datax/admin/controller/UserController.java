package com.wugui.datax.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.constans.Constant;
import com.wugui.datax.admin.core.util.I18nUtil;
import com.wugui.datax.admin.entity.JobMenuEntity;
import com.wugui.datax.admin.entity.JobUser;
import com.wugui.datax.admin.mapper.JobUserMapper;
import com.wugui.datax.admin.service.JobMenuService;
import com.wugui.datax.admin.service.JobUserRoleService;
import com.wugui.datax.admin.service.JobUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wugui.datatx.core.biz.model.ReturnT.FAIL_CODE;

/**
 * Created by jingwk on 2019/11/17
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户信息接口")
public class UserController extends BaseController{

    @Resource
    private JobUserMapper jobUserMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JobMenuService jobMenuService;

    @Autowired
    private JobUserService jobUserService;

    @Autowired
    private JobUserRoleService jobUserRoleService;


    @GetMapping("/pageList")
    @ApiOperation("用户列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String username) {

        // page list
        List<JobUser> list = jobUserMapper.pageList((current - 1) * size, size, username);
        int recordsTotal = jobUserMapper.pageListCount((current - 1) * size, size, username);

        // package result
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", recordsTotal);        // 总记录数
        maps.put("recordsFiltered", recordsTotal);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        return new ReturnT<>(maps);
    }

    @GetMapping("/list")
    @ApiOperation("用户列表")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        // page list
        if(getCurrentUserId(request) != Constant.SUPER_ADMIN){
            params.put("createUserId", getCurrentUserId(request));
        }
        return R.ok(jobUserService.queryPage(params));
    }

    @GetMapping("/all")
    public R getAllUser(){
        List<JobUser> userList = jobUserService.list();
        userList.forEach(item-> item.setPassword(null));
        return R.ok(userList);
    }

    @GetMapping("/getUserById")
    @ApiOperation(value = "根据id获取用户")
    public R selectById(@RequestParam("userId") Integer userId) {
        JobUser user = jobUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = jobUserRoleService.queryRoleIdList(userId.longValue());
        user.setRoleIdList(roleIdList.stream().distinct().collect(Collectors.toList()));

        return R.ok(user);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public ReturnT<String> add(@RequestBody JobUser jobUser, HttpServletRequest request) {

        // valid username
        if (!StringUtils.hasText(jobUser.getUsername())) {
            return new ReturnT<>(FAIL_CODE, I18nUtil.getString("system_please_input") + I18nUtil.getString("user_username"));
        }
        jobUser.setUsername(jobUser.getUsername().trim());
        if (!(jobUser.getUsername().length() >= 4 && jobUser.getUsername().length() <= 20)) {
            return new ReturnT<>(FAIL_CODE, I18nUtil.getString("system_length_limit") + "[4-20]");
        }
        // valid password
        if (!StringUtils.hasText(jobUser.getPassword())) {
            return new ReturnT<>(FAIL_CODE, I18nUtil.getString("system_please_input") + I18nUtil.getString("user_password"));
        }
        jobUser.setPassword(jobUser.getPassword().trim());
        if (!(jobUser.getPassword().length() >= 4 && jobUser.getPassword().length() <= 20)) {
            return new ReturnT<>(FAIL_CODE, I18nUtil.getString("system_length_limit") + "[4-20]");
        }
        jobUser.setPassword(bCryptPasswordEncoder.encode(jobUser.getPassword()));


        // check repeat
        JobUser existUser = jobUserMapper.loadByUserName(jobUser.getUsername());
        if (existUser != null) {
            return new ReturnT<>(FAIL_CODE, I18nUtil.getString("user_username_repeat"));
        }
        jobUser.setCreateUserId(getCurrentUserId(request).longValue());
        // write
        jobUserService.saveUser(jobUser);
        return ReturnT.SUCCESS;
    }

    @PostMapping(value = "/update")
    @ApiOperation("更新用户信息")
    public ReturnT<String> update(@RequestBody JobUser jobUser, HttpServletRequest request) {
        if (jobUser.getPassword() != null && StringUtils.hasText(jobUser.getPassword())) {
            String pwd = jobUser.getPassword().trim();
            if (StrUtil.isBlank(pwd)) {
                return new ReturnT<>(FAIL_CODE, I18nUtil.getString("system_no_blank") + "密码");
            }

            if (!(pwd.length() >= 4 && pwd.length() <= 20)) {
                return new ReturnT<>(FAIL_CODE, I18nUtil.getString("system_length_limit") + "[4-20]");
            }
            jobUser.setPassword(bCryptPasswordEncoder.encode(pwd));
        }
        // write
        jobUser.setCreateUserId(getCurrentUserId(request).longValue());
        jobUserService.update(jobUser);
        return ReturnT.SUCCESS;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ApiOperation("删除用户")
    public ReturnT<String> remove(int id) {
        int result = jobUserMapper.deleteById(id);
        return result != 1 ? ReturnT.FAIL : ReturnT.SUCCESS;
    }

    @PostMapping(value = "/updatePwd")
    @ApiOperation("修改密码")
    public ReturnT<String> updatePwd(@RequestBody JobUser jobUser) {
        String password = jobUser.getPassword();
        if (password == null || password.trim().length() == 0) {
            return new ReturnT<>(ReturnT.FAIL.getCode(), "密码不可为空");
        }
        password = password.trim();
        if (!(password.length() >= 4 && password.length() <= 20)) {
            return new ReturnT<>(FAIL_CODE, I18nUtil.getString("system_length_limit") + "[4-20]");
        }
        // do write
        JobUser existUser = jobUserMapper.loadByUserName(jobUser.getUsername());
        existUser.setPassword(bCryptPasswordEncoder.encode(password));
        jobUserMapper.updateById(existUser);
        return ReturnT.SUCCESS;
    }

    @GetMapping("/permission")
    @ApiOperation("获取该用户对应权限")
    public R<List<JobMenuEntity>> nav(HttpServletRequest request){
        List<JobMenuEntity> menuList = jobMenuService.getUserMenuList(getCurrentUserId(request).longValue());
        return R.ok(menuList);
    }

    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        JobUser user = jobUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = jobUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok(user);
    }

    @GetMapping("project/userList")
    public R userListByProjectId(@RequestParam(required = false, defaultValue = "1") Long current,
                                 @RequestParam(required = false, defaultValue = "10") Long size,
                                 String username,
                                 Integer projectId){
        return R.ok(jobUserService. userListByProjectId(current, size,username, projectId));
    }

    @PostMapping("/updatePassWorld")
    @ApiOperation("修改密码")
    public R updatePassWorld(@RequestParam("userId") Integer userId,
                                                              @RequestParam(value = "passWorld", required = false) String pwd,
                                                              @RequestParam(value = "newPassWorld", required = false) String newPassWorld) {
        return  R.ok(jobUserService. updatePassword(userId, pwd,newPassWorld));
    }
}
