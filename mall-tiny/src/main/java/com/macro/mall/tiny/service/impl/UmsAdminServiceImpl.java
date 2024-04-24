package com.macro.mall.tiny.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.macro.mall.tiny.common.exception.ApiException;
import com.macro.mall.tiny.common.utils.JwtTokenUtil;
import com.macro.mall.tiny.domain.AdminUserDetails;
import com.macro.mall.tiny.domain.UmsResource;
import com.macro.mall.tiny.service.UmsAdminService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther macrozheng
 * @description 后台用户管理Service实现类
 * @date 2020/10/15
 * @github https://github.com/macrozheng
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    /**
     * 存放默认用户信息
     */
    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();
    /**
     * 存放默认资源信息
     */
    private List<UmsResource> resourceList = new ArrayList<>();
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostConstruct
    private void init(){

        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("admin")
                .password(BCrypt.hashpw("123456"))
                .authorityList(CollUtil.toList("brand:create","brand:update","brand:delete","brand:list","brand:listAll"))
                .build());
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("macro")
                .password(BCrypt.hashpw("123456"))
                .authorityList(CollUtil.toList("brand:listAll"))
                .build());
        resourceList.add(UmsResource.builder()
                .id(1L)
                .name("brand:create")
                .url("/brand/create")
                .build());
        resourceList.add(UmsResource.builder()
                .id(2L)
                .name("brand:update")
                .url("/brand/update/**")
                .build());
        resourceList.add(UmsResource.builder()
                .id(3L)
                .name("brand:delete")
                .url("/brand/delete/**")
                .build());
        resourceList.add(UmsResource.builder()
                .id(4L)
                .name("brand:list")
                .url("/brand/list")
                .build());
        resourceList.add(UmsResource.builder()
                .id(5L)
                .name("brand:listAll")
                .url("/brand/listAll")
                .build());
    }
    @Override
    public AdminUserDetails getAdminByUsername(String username) {
        List<AdminUserDetails> findList = adminUserDetailsList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(findList)){
            return findList.get(0);
        }
        return null;
    }

    @Override
    public List<UmsResource> getResourceList() {
        return resourceList;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        AdminUserDetails userDetails = getAdminByUsername(username);
        if (userDetails == null) {
            return token;
        }
        if (!BCrypt.checkpw(password, userDetails.getPassword())) {
            throw new ApiException("密码不正确");
        }
        // TODO: 2024/1/17 对接sa-token
        token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}
