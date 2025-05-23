package com.web.www.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.web.www.commom.constants.SysConstants;
import com.web.www.commom.enums.SysCommonStatusEnum;
import com.web.www.commom.enums.SysUserStatusEnum;
import com.web.www.commom.model.Result;
import com.web.www.mapper.SysUserMapper;
import com.web.www.model.entity.SysUser;
import com.web.www.model.vo.reqVo.SysUserLoginReqVo;
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

    public SysUser selectByUserName(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        return sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public Result login(SysUserLoginReqVo user) {
        // 获取用户信息
        SysUser sysUser = this.selectByUserName(user.getUsername());
        // 用户不存在
        if (sysUser == null)
            return Result.fail(SysUserStatusEnum.USER_NOT_EXIST.getCode(), SysUserStatusEnum.USER_NOT_EXIST.getMessage());
        // 用户被禁用
        if (SysConstants.DISABLE.equals(sysUser.getStatus()))
            return Result.fail(SysUserStatusEnum.USER_DISABLED.getCode(), SysUserStatusEnum.USER_DISABLED.getMessage());
        // 校验密码
        Boolean isSuccess = SysUser.checkPassword(user.getPassword(), sysUser.getSalt(), sysUser.getPassword());
        // 密码错误
        if (!isSuccess)
            return Result.fail(SysUserStatusEnum.PASSWORD_ERROR.getCode(), SysUserStatusEnum.PASSWORD_ERROR.getMessage());
        // 密码正确并登录
        StpUtil.login(sysUser.getUserId());
        // 返回token
        return Result.success(SysCommonStatusEnum.SUCCESS.getCode(), SysCommonStatusEnum.SUCCESS.getMessage(), StpUtil.getTokenValue());
    }

}
