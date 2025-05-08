package com.web.www.service;

import com.web.www.pojo.KdUser;

import java.util.List;

public interface KdUserService {

    KdUser getKdUserById(Integer id);

    KdUser getKdUserByName(String name);

    KdUser getKdUserByPhone(String phone);

    List<KdUser> getAllKdUser();

    void addKdUser(KdUser kdUser);

    void deleteKdUser(Integer id);

    void updateKdUser(KdUser kdUser);

    void exportKdUsers();

}
