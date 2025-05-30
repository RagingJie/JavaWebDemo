package com.web.www.service;

import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import com.web.www.model.entity.SysLoginInfoLog;

public interface SysLoginInfoLogService {

    /**
     * 保存登录日志
     *
     * @param sysLoginInfoLog 登录日志
     */
    void saveLoginInfo(SysLoginInfoLog sysLoginInfoLog);

    /**
     * 保存登录日志
     *
     * @param userName      用户名
     * @param ipAddress     ip地址
     * @param loginLocation 登录地点
     * @param browser       浏览器
     * @param os            操作系统
     * @param status        状态
     * @param msg           消息
     */
    void saveLoginInfo(String userName, String ipAddress, String loginLocation, String browser, String os, Integer status, String msg);

    /**
     * 登录日志
     *
     * @param loginType     登录类型
     * @param loginId       登录id
     * @param tokenValue    token值
     * @param loginParameter 登录参数
     */
    void saveLoginInfo(String loginType, Long loginId, String tokenValue, SaLoginParameter loginParameter);

}
