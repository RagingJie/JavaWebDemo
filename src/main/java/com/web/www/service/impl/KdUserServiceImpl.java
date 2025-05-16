package com.web.www.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.web.www.commom.enums.ResultStatusEnum;
import com.web.www.exception.BusinessRuntimeException;
import com.web.www.mapper.KdUserMapper;
import com.web.www.model.pojo.KdSysUser;
import com.web.www.service.KdUserService;
import com.web.www.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class KdUserServiceImpl implements KdUserService {

    @Autowired
    private KdUserMapper kdUserMapper;

    @Override
    public KdSysUser getKdUserById(Integer id) {
        return kdUserMapper.selectById(id);
    }

    @Override
    public KdSysUser getKdUserByName(String name) {
        LambdaQueryWrapper<KdSysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(KdSysUser::getName, name);
        return kdUserMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public KdSysUser getKdUserByPhone(String phone) {
        LambdaQueryWrapper<KdSysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(KdSysUser::getPhone, phone);
        return kdUserMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public List<KdSysUser> getAllKdUser() {
        return kdUserMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public void addKdUser(KdSysUser kdSysUser) {
        if (StrUtil.isNotBlank(kdSysUser.getPhone())) {
            if (BeanUtil.isNotEmpty(getKdUserByPhone(kdSysUser.getPhone()))) {
                throw new BusinessRuntimeException(ResultStatusEnum.CONFLICT.getCode(), ResultStatusEnum.CONFLICT.getMessage(), "手机号为【" + kdSysUser.getPhone() + "】的用户已存在");
            }
        }
        kdUserMapper.insert(kdSysUser);
    }

    @Override
    public void deleteKdUser(Integer id) {
        int row = kdUserMapper.deleteById(id);
        if (row == 0) {
            throw new BusinessRuntimeException(ResultStatusEnum.FAIL.getCode(), ResultStatusEnum.FAIL.getMessage(), "id为【" + id + "】的用户不存在");
        }
    }

    @Override
    public void updateKdUser(KdSysUser kdSysUser) {
        if (StrUtil.isNotBlank(kdSysUser.getPhone())) {
            KdSysUser user = getKdUserByPhone(kdSysUser.getPhone());
            if (BeanUtil.isNotEmpty(user) && !kdSysUser.getId().equals(user.getId())) {
                throw new BusinessRuntimeException(ResultStatusEnum.CONFLICT.getCode(), ResultStatusEnum.CONFLICT.getMessage(), "手机号为【" + kdSysUser.getPhone() + "】的用户已存在");
            }
        }
        kdUserMapper.updateById(kdSysUser);
    }

    @Override
    public void exportKdUsers() {
        List<KdSysUser> allKdSysUser = getAllKdUser();
        ArrayList<Map<String, Object>> exportData = new ArrayList<>();
        // 设置表头数据
        Map<String, Object> titleMap = new LinkedHashMap<>();
        titleMap.put("name", "姓名");
        titleMap.put("sex", "性别");
        titleMap.put("age", "年龄");
        titleMap.put("phone", "联系电话");
        titleMap.put("address", "地址");
        exportData.add(titleMap);

        // 设置表单内容数据
        for (KdSysUser kdSysUser : allKdSysUser) {
            Map<String, Object> contentMap = new LinkedHashMap<>();
            contentMap.put("name", kdSysUser.getName());
            contentMap.put("sex", kdSysUser.getSex());
            contentMap.put("age", kdSysUser.getAge());
            contentMap.put("phone", kdSysUser.getPhone());
            contentMap.put("address", kdSysUser.getAddress());
            exportData.add(contentMap);
        }

        String fileName = "用户信息表";
        String filePath = "C:\\Users\\20459\\Desktop";
        ExcelUtil.exportData(fileName, filePath, Arrays.asList(fileName, "", ""), exportData, null, exportData);
    }

    @Override
    public void importKdUsers(MultipartFile file) {
        try {
            ExcelUtil.readData(file.getInputStream());
        } catch (IOException e) {
            throw new BusinessRuntimeException(ResultStatusEnum.IMPORT_ERROR.getCode(), ResultStatusEnum.IMPORT_ERROR.getMessage(), e.getMessage());
        }
    }
}
