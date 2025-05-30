package com.web.www.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.web.www.model.dto.IpAddressDto;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static cn.hutool.poi.excel.sax.AttributeName.s;

/**
 * Ip工具类
 *
 * @author naruto
 * @since 2025-05-30
 */
@Slf4j
public class IpUtil {

    /**
     * 获取客户端IP
     *
     * @param request 请求
     * @return 客户端IP
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取IP地址信息
     *
     * @param ipAddress IP地址
     * @return IP地址信息
     */
    public static IpAddressDto getIpLocation(String ipAddress) {
        String apiUrl = "https://qifu-api.baidubce.com/ip/geo/v1/district?ip=" + ipAddress;
        String result = HttpUtil.get(apiUrl);
        return JSONObject.parseObject(JSONObject.parse(result).getString("data"), IpAddressDto.class);
    }

}
