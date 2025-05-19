package com.web.www.otherBusiness.service;


import com.web.www.otherBusiness.model.ResultRespVo;
import com.web.www.otherBusiness.model.SalaryReqVo;
import com.web.www.otherBusiness.model.User;

import java.math.BigDecimal;

public interface UserService {

    /**
     * 获取工资
     *
     * @param reqVo  请求参数
     * @param isSelf 是否是自己，true表示自己，false表示班组的所有人
     * @return 工资列表
     */
    BigDecimal getSalary(SalaryReqVo reqVo, boolean isSelf);

    String test(User user);
}
