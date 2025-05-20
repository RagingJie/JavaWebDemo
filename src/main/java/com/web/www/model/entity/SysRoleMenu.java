package com.web.www.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 角色和菜单关联实体类
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
@TableName(value = "sys_role_menu")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -23847983749831L;

    // 角色ID
    @TableField(value = "role_id")
    private Long roleId;

    // 菜单ID
    @TableField(value = "menu_id")
    private Long menuId;
}
