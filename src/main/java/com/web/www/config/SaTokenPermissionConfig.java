package com.web.www.config;

import cn.dev33.satoken.model.wrapperInfo.SaDisableWrapperInfo;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Sa-Token 权限认证接口
 *
 * @author Naruto
 * @since 2025/05/22
 */
@Component
public class SaTokenPermissionConfig implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param loginId  登录id
     * @param loginType 账号体系标识，此处可以暂时忽略，在 [ 多账户认证 ] 章节下会对这个概念做详细的解释。
     * @since 2025-05-22
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String loginIdStr = loginId.toString();
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     *
     * @param loginId  登录id
     * @param loginType 账号体系标识，此处可以暂时忽略，在 [ 多账户认证 ] 章节下会对这个概念做详细的解释。
     * @since 2025-05-22
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String loginIdStr = loginId.toString();
        return null;
    }

    /**
     * 返回一个账号是否被封禁, true=封禁, false=未封禁
     *
     * @param loginId  登录id
     * @param service  服务名
     * @since 2025-05-22
     */
    @Override
    public SaDisableWrapperInfo isDisabled(Object loginId, String service) {
        return StpInterface.super.isDisabled(loginId, service);
    }
}
