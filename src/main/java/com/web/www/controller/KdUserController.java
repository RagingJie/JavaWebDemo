package com.web.www.controller;

import com.web.www.commom.model.Result;
import com.web.www.pojo.KdUser;
import com.web.www.service.KdUserService;

import com.web.www.utils.CommonContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = "/kdUser")
public class KdUserController {

    @Autowired
    private KdUserService kdUserService;

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return
     */
    @GetMapping(path = "/getKdUserById")
    public Result getKdUserById(@RequestParam(value = "id") Integer id) {
        log.info("根据id查询用户，请求用户id => {}", id);
        KdUser user = kdUserService.getKdUserById(id);
        return Result.success(user);
    }

    /**
     * 根据name查询用户
     *
     * @param name 用户名
     * @return
     */
    @GetMapping(path = "/getKdUserByName")
    public Result getKdUserByName(@RequestParam(value = "name") String name) {
        log.info("根据name查询用户，请求用户name => {}", name);
        KdUser user = kdUserService.getKdUserByName(name);
        return Result.success(user);
    }

    /**
     * 根据phone查询用户
     *
     * @param phone 用户手机号
     * @return
     */
    @GetMapping(path = "/getKdUserByPhone")
    public Result getKdUserByPhone(@RequestParam(value = "phone") String phone) {
        log.info("根据phone查询用户，请求用户phone => {}", phone);
        KdUser user = kdUserService.getKdUserByPhone(phone);
        return Result.success(user);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping(path = "/getAllKdUser")
    public Result getAllKdUser() {
        log.info("查询所有用户");
        List<KdUser> users = kdUserService.getAllKdUser();
        return Result.success(users);
    }

    /**
     * 添加用户
     *
     * @param kdUser 用户信息
     * @return
     */
    @PostMapping(path = "/addKdUser")
    public Result addKdUser(@Valid @RequestBody KdUser kdUser) {
        log.info("添加用户，请求参数 => {}", kdUser);
        kdUserService.addKdUser(kdUser);
        return Result.success("添加成功");
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    @DeleteMapping(path = "/deleteKdUser")
    public Result deleteKdUser(@RequestParam(value = "id") Integer id) {
        log.info("删除用户，请求参数 => {}", id);
        kdUserService.deleteKdUser(id);
        return Result.success("删除成功");
    }

    /**
     * 修改用户
     *
     * @param kdUser 用户信息
     * @return
     */
    @PutMapping(path = "/updateKdUser")
    public Result updateKdUser(@Valid @RequestBody KdUser kdUser) {
        log.info("修改用户，请求参数 => {}", kdUser);
        kdUserService.updateKdUser(kdUser);
        return Result.success("修改成功");
    }

    /**
     * 导出所有user信息
     *
     * @return
     */
    @PostMapping(path = "/exportKdUsers")
    public Result exportKdUsers() {
        log.info("导出所有user信息");
        kdUserService.exportKdUsers();
        return Result.success("导出成功",String.format("累计耗时：%s ms", CommonContextUtil.get()));
    }
}
