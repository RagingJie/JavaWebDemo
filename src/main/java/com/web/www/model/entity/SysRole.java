package com.web.www.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 角色信息表
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
@TableName(value = "sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 21347892374234L;

    // 角色ID
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    // 角色名称
    @TableField(value = "role_name")
    private String roleName;

    // 角色权限字符串
    @TableField(value = "role_key")
    private String roleKey;

    // 显示顺序
    @TableField(value = "role_sort")
    private Integer roleSort;

    // 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
    @TableField(value = "data_scope")
    private String dataScope;

    // 菜单树选择项是否关联显示
    @TableField(value = "menu_check_strictly")
    private Integer menuCheckStrictly;

    // 部门树选择项是否关联显示
    @TableField(value = "dept_check_strictly")
    private Integer deptCheckStrictly;

    // 角色状态（0正常 1停用）
    @TableField(value = "status")
    private Integer status;

    // 删除标志（n代表存在 y代表删除）
    @TableLogic
    @TableField(value = "is_deleted")
    private String isDeleted;

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

}
