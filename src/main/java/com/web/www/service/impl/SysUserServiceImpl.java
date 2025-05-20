package com.web.www.service.impl;

import com.web.www.mapper.SysUserMapper;
import com.web.www.model.entity.SysUser;
import com.web.www.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectByUserId(Long userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectList(null);
    }

    @Override
    public Integer addUser(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public Integer updateUser(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public Integer deleteByUserId(Long userId) {
        return sysUserMapper.deleteById(userId);
    }
}
