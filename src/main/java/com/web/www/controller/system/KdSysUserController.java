package com.web.www.controller.system;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.web.www.commom.model.Result;
import com.web.www.model.vo.reqVo.KdSysUserLoginReqVo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/sys/user")
public class KdSysUserController {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/login")
    public Result login(@RequestBody @Valid KdSysUserLoginReqVo user) {
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            StpUtil.login("1001");
        } else {
            return Result.fail("用户名或密码错误");
        }
        return Result.success("登录成功");
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
