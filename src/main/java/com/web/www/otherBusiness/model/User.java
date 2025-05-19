package com.web.www.otherBusiness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String userPass;
}
