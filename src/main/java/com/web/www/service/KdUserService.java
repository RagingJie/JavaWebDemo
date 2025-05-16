package com.web.www.service;

import com.web.www.model.pojo.KdSysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KdUserService {

    KdSysUser getKdUserById(Integer id);

    KdSysUser getKdUserByName(String name);

    KdSysUser getKdUserByPhone(String phone);

    List<KdSysUser> getAllKdUser();

    void addKdUser(KdSysUser kdSysUser);

    void deleteKdUser(Integer id);

    void updateKdUser(KdSysUser kdSysUser);

    void exportKdUsers();

    void importKdUsers(MultipartFile file);
}
