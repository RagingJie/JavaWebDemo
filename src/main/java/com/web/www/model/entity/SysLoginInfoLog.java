package com.web.www.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author Naruto
 * @since 2025-05-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_login_info_log")
public class SysLoginInfoLog implements Serializable {

    private static final long serialVersionUID = 123832479877989L;

    // 登录信息ID
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    // 用户名
    @TableField(value = "user_name")
    private String userName;

    // 登录ip地址
    @TableField(value = "ip_address")
    private String ipAddress;

    // 登录地点
    @TableField(value = "login_location")
    private String loginLocation;

    // 浏览器类型
    @TableField(value = "browser")
    private String browser;

    // 操作系统
    @TableField(value = "os")
    private String os;

    // 登录状态（0成功 1失败）
    @TableField(value = "status")
    private Integer status;

    // 提示消息
    @TableField(value = "msg")
    private String msg;

    // 登录时间
    @TableField(value = "login_time")
    private Date loginTime;
}
