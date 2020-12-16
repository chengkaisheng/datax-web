package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.JwtUser;
import com.wugui.datax.admin.entity.JobUser;
import com.wugui.datax.admin.mapper.JobUserMapper;
import com.wugui.datax.admin.service.JobUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserDetailsServiceImpl
 * @author jingwk
 * @since 2019-03-15
 * @version v2.1.1
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JobUserService jobUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        JobUser user = jobUserService.queryByUserName(s);
        return new JwtUser(user);
    }

    public List<Long> queryAllMenuId(Long userId){
        return jobUserService.queryAllMenuId(userId);
    }
}
