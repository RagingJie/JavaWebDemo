package com.web.www.service;

import com.web.www.model.entity.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return 用户信息
     */
    SysUser selectByUserId(Long userId);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<SysUser> selectAll();

    /**
     * 添加用户
     *
     * @param sysUser 用户信息
     * @return 添加结果
     */
    Integer addUser(SysUser sysUser);

    /**
     * 修改用户
     *
     * @param sysUser 用户信息
     * @return 修改结果
     */
    Integer updateUser(SysUser sysUser);

    /**
     * 根据用户id删除用户
     *
     * @param userId 用户id
     * @return 删除结果
     */
    Integer deleteByUserId(Long userId);


}
