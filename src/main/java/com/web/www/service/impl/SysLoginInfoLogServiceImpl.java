package com.web.www.service.impl;

import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import com.web.www.commom.constants.SysConstants;
import com.web.www.mapper.SysLoginInfoLogMapper;
import com.web.www.model.entity.SysLoginInfoLog;
import com.web.www.model.entity.SysUser;
import com.web.www.service.SysLoginInfoLogService;
import com.web.www.service.SysUserService;
import com.web.www.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SysLoginInfoLogServiceImpl implements SysLoginInfoLogService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLoginInfoLogMapper sysLoginInfoLogMapper;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public void saveLoginInfo(SysLoginInfoLog sysLoginInfoLog) {
        sysLoginInfoLogMapper.insert(sysLoginInfoLog);
    }

    @Override
    public void saveLoginInfo(String userName, String ipAddress, String loginLocation, String browser, String os, Integer status, String msg) {
        SysLoginInfoLog sysLoginInfoLog = SysLoginInfoLog.builder()
                .userName(userName)
                .ipAddress(ipAddress)
                .loginLocation(loginLocation)
                .browser(browser)
                .os(os)
                .status(status)
                .msg(msg)
                .build();
        saveLoginInfo(sysLoginInfoLog);
    }

    @Override
    public void saveLoginInfo(String loginType, Long loginId, String tokenValue, SaLoginParameter loginParameter) {
        // 获取登录用户信息
        SysUser sysUser = sysUserService.selectByUserId(loginId);
        // 获取ip地址
        String ipAddress = IpUtil.getClientIp(request);
        // 获取浏览器类型
        String browser = request.getHeader("User-Agent");
        // TODO 获取操作系统类型
        String os = request.getHeader("sec-ch-ua-platform");
        // TODO 获取登录地点
        String loginLocation = IpUtil.getIpLocation(ipAddress).toProvCityDistrictStr();
        SysLoginInfoLog sysLoginInfoLog = SysLoginInfoLog.builder()
                .userName(sysUser.getUsername())
                .ipAddress(ipAddress)
                .loginLocation(loginLocation)
                .browser(browser)
                .os(os)
                .status(SysConstants.SUCCESS)
                .msg("登录成功")
                .build();
        saveLoginInfo(sysLoginInfoLog);
    }
}
