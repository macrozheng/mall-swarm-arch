package com.macro.mall.service;


import com.macro.mall.bo.AdminUserDetails;
import com.macro.mall.bo.UmsResource;

import java.util.List;

/**
 * @auther macrozheng
 * @description 后台用户管理Service
 * @date 2020/10/15
 * @github https://github.com/macrozheng
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUserDetails getAdminByUsername(String username);

    /**
     * 获取所以权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
