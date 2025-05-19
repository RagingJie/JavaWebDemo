package com.web.www.otherBusiness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SalaryReqVo extends User {

    @NotBlank(message = "开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "请输入正确的时间格式")
    private String startTime;

    @NotBlank(message = "结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "请输入正确的时间格式")
    private String endTime;

    private Boolean isSelf;

}
