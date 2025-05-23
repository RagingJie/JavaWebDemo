package com.web.www.model.entity;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.web.www.commom.property.RsaProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
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
@TableName(value = "sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 324678328942309L;

    // 使用hutool的rsa算法
    private static final RSA rsa = SecureUtil.rsa(RsaProperty.privateKey, RsaProperty.publicKey);

    // 主键，用户id，使用雪花算法自增主键
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    // 昵称
    @TableField(value = "nick_name")
    private String nickname;

    // 用户名
    @TableField(value = "user_name")
    private String username;

    // 用户类型（00系统用户）
    @TableField(value = "user_type")
    private String userType;

    // 邮箱
    @TableField(value = "email")
    private String email;

    // 手机号
    @TableField(value = "phone")
    private String phone;

    // 密码
    @TableField(value = "password")
    private String password;

    // 用户性别（0女 1男 2未知）
    @TableField(value = "sex")
    private Integer sex;

    // 生日
    @TableField(value = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    // 住址
    @TableField(value = "address")
    private String address;

    // 删除标志（n代表存在 y代表删除）
    @TableLogic
    @TableField(value = "is_deleted")
    private String isDeleted;

    // 头像
    @TableField(value = "avatar")
    private String avatar;

    // 帐号状态（0正常 1停用）
    @TableField(value = "status")
    private Integer status;

    // 登录ip
    @TableField(value = "login_ip")
    private String loginIp;

    // 最后登录时间
    @TableField(value = "login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    // 创建人
    @TableField(value = "create_by")
    private String createBy;

    // 创建时间
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    // 更新人
    @TableField(value = "update_by")
    private String updateBy;

    // 更新时间
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    // 备注
    @TableField(value = "remark")
    private String remark;

    // 盐值，使用uuid
    @TableField(value = "salt")
    private String salt;

    /**
     * 密码加密
     *
     * @param password 用户输入的密码
     * @param salt     盐值
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String salt) {
        return Base64Encoder.encode(DigestUtil.sha256Hex(salt + password + salt));
    }

    /**
     * 密码校验
     *
     * @param password        用户输入的密码
     * @param encodedPassword 数据库中存储的加密后的密码
     * @param salt            盐值
     * @return 校验结果
     */
    public static Boolean checkPassword(String password, String salt, String encodedPassword) {
        return encryptPassword(password, salt).equals(encodedPassword);
    }

    /**
     * 手机号加密，RSA加密
     *
     * @param phone 手机号
     * @return 加密后的手机号
     */
    public static String encryptPhone(String phone) {
        return rsa.encryptBase64(phone, KeyType.PublicKey);
    }

    /**
     * 手机号解密，RSA加密
     *
     * @param phone 手机号
     * @return 解密后的手机号
     */
    public static String decryptPhone(String phone) {
        return rsa.decryptStr(phone, KeyType.PrivateKey);
    }
}
