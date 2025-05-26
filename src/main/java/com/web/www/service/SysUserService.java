package com.web.www.service;

import com.web.www.commom.model.Result;
import com.web.www.model.entity.SysUser;
import com.web.www.model.vo.reqVo.SysUserLoginReqVo;

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

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return 登录结果
     */
    Result login(SysUserLoginReqVo user);

    /**
     * 用户登出---> 强制注销
     * 注：
     * 强制注销等价于对方主动调用了注销方法，再次访问会提示：Token无效。
     *
     * @param userId 用户id
     */
    void logout(String userId);

    /**
     * 用户登出---> 踢出
     * 注：
     * 踢人下线不会清除Token信息，而是将其打上特定标记，再次访问会提示：Token已被踢下线。
     *
     * @param userId 用户id
     */
    void kickOut(String userId);
}
