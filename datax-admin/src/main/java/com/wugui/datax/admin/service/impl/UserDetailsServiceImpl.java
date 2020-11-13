package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.JobRole;
import com.wugui.datax.admin.entity.JobUser;
import com.wugui.datax.admin.entity.JwtUser;
import com.wugui.datax.admin.mapper.JobUserMapper;
import com.wugui.datax.admin.service.JobRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl
 * @author jingwk
 * @since 2019-03-15
 * @version v2.1.1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JobUserMapper jobUserMapper;

    @Autowired
    JobRoleService jobRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        JobUser user = jobUserMapper.loadByUserName(s);
        JobRole jobRole = jobRoleService.getById(user.getRoleId());
        return new JwtUser(user, jobRole.getType());
    }

}
