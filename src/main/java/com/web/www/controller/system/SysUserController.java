package com.web.www.controller.system;

import cn.dev33.satoken.stp.StpUtil;
import com.web.www.commom.model.Result;
import com.web.www.model.vo.reqVo.SysUserLoginReqVo;
import com.web.www.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户管理控制器
 *
 * @author Naruto
 * @since 2025-05-23
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/login")
    public Result login(@RequestBody @Valid SysUserLoginReqVo user) {
        return sysUserService.login(user);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping(path = "/logout")
    public Result logout(String loginId, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        StpUtil.logout(loginId);
        return Result.success("退出成功");
    }

    /**
     * 查询用户登录状态
     *
     * @return
     */
    @GetMapping(path = "/isLogin")
    public Result isLogin(String loginId) {
        return StpUtil.isLogin(loginId) ? Result.success("已登录") : Result.fail("未登录");
    }

}
