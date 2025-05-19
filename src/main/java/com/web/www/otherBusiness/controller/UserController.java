package com.web.www.otherBusiness.controller;

import com.web.www.commom.model.Result;
import com.web.www.otherBusiness.model.SalaryReqVo;
import com.web.www.otherBusiness.model.User;
import com.web.www.otherBusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/getUserSalary")
    public Result getUserSalary(@RequestBody @Valid SalaryReqVo reqVo) {
        return Result.success(userService.getSalary(reqVo, reqVo.getIsSelf()));
    }

    @PostMapping(path = "/test")
    public String test(@RequestBody User user) {
        return userService.test(user);
    }
}
