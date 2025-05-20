package com.web.www.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户和角色关联表
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
@TableName(value = "sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = -435456754645641L;

    // 用户ID
    @TableField(value = "user_id")
    private Long userId;

    // 角色ID
    @TableField(value = "role_id")
    private Long roleId;
}
