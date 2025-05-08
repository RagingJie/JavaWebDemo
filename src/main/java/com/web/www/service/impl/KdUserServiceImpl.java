package com.web.www.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.web.www.commom.enums.ResultStatusEnum;
import com.web.www.exception.BusinessRuntimeException;
import com.web.www.mapper.KdUserMapper;
import com.web.www.pojo.KdUser;
import com.web.www.service.KdUserService;
import com.web.www.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KdUserServiceImpl implements KdUserService {

    @Autowired
    private KdUserMapper kdUserMapper;

    @Override
    public KdUser getKdUserById(Integer id) {
        return kdUserMapper.selectById(id);
    }

    @Override
    public KdUser getKdUserByName(String name) {
        LambdaQueryWrapper<KdUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(KdUser::getName, name);
        return kdUserMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public KdUser getKdUserByPhone(String phone) {
        LambdaQueryWrapper<KdUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(KdUser::getPhone, phone);
        return kdUserMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public List<KdUser> getAllKdUser() {
        return kdUserMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public void addKdUser(KdUser kdUser) {
        if (StrUtil.isNotBlank(kdUser.getPhone())) {
            if (BeanUtil.isNotEmpty(getKdUserByPhone(kdUser.getPhone()))) {
                throw new BusinessRuntimeException(ResultStatusEnum.CONFLICT.getCode(), ResultStatusEnum.CONFLICT.getMessage(), "手机号为【" + kdUser.getPhone() + "】的用户已存在");
            }
        }
        kdUserMapper.insert(kdUser);
    }

    @Override
    public void deleteKdUser(Integer id) {
        int row = kdUserMapper.deleteById(id);
        if (row == 0) {
            throw new BusinessRuntimeException(ResultStatusEnum.FAIL.getCode(), ResultStatusEnum.FAIL.getMessage(), "id为【" + id + "】的用户不存在");
        }
    }

    @Override
    public void updateKdUser(KdUser kdUser) {
        if (StrUtil.isNotBlank(kdUser.getPhone())) {
            KdUser user = getKdUserByPhone(kdUser.getPhone());
            if (BeanUtil.isNotEmpty(user) && !kdUser.getId().equals(user.getId())) {
                throw new BusinessRuntimeException(ResultStatusEnum.CONFLICT.getCode(), ResultStatusEnum.CONFLICT.getMessage(), "手机号为【" + kdUser.getPhone() + "】的用户已存在");
            }
        }
        kdUserMapper.updateById(kdUser);
    }

    @Override
    public void exportKdUsers() {
        List<KdUser> allKdUser = getAllKdUser();
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
        for (KdUser kdUser : allKdUser) {
            Map<String, Object> contentMap = new LinkedHashMap<>();
            contentMap.put("name", kdUser.getName());
            contentMap.put("sex", kdUser.getSex());
            contentMap.put("age", kdUser.getAge());
            contentMap.put("phone", kdUser.getPhone());
            contentMap.put("address", kdUser.getAddress());
            exportData.add(contentMap);
        }

        String fileName = "用户信息表";
        String filePath = "C:\\Users\\20459\\Desktop";
        ExcelUtil.exportData(fileName, filePath, Arrays.asList(fileName,"",""), exportData, null, exportData);
    }
}
