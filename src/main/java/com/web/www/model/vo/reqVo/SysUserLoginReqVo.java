package com.web.www.model.vo.reqVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysUserLoginReqVo {

    // 用户名
    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 16, message = "用户名长度在6~16之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度在6~16之间")
    private String password;

}
