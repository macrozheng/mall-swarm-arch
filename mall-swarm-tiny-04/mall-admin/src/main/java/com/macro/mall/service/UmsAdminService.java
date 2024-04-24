package com.macro.mall.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import com.macro.mall.bo.UmsResource;
import com.macro.mall.common.dto.UserDto;

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
    UserDto getAdminByUsername(String username);

    /**
     * 获取所以权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    SaTokenInfo login(String username, String password);
}
