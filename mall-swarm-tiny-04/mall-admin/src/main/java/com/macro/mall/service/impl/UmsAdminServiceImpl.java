package com.macro.mall.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.macro.mall.bo.UmsResource;
import com.macro.mall.common.dto.UserDto;
import com.macro.mall.common.exception.ApiException;
import com.macro.mall.service.UmsAdminService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
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
    private List<UserDto> userDtoList = new ArrayList<>();
    /**
     * 存放默认资源信息
     */
    private List<UmsResource> resourceList = new ArrayList<>();

    @PostConstruct
    private void init(){
        userDtoList.add(UserDto.builder()
                .id(1L)
                .username("admin")
                .password(BCrypt.hashpw("123456"))
                .permissionList(CollUtil.toList("brand:create","brand:update","brand:delete","brand:list","brand:listAll"))
                .build());
        userDtoList.add(UserDto.builder()
                .id(2L)
                .username("macro")
                .password(BCrypt.hashpw("123456"))
                .permissionList(CollUtil.toList("brand:listAll"))
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
    public UserDto getAdminByUsername(String username) {
        List<UserDto> findList = userDtoList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
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
    public SaTokenInfo login(String username, String password) {
        SaTokenInfo saTokenInfo = null;
        UserDto UserDto = getAdminByUsername(username);
        if (UserDto == null) {
            return null;
        }
        if (!BCrypt.checkpw(password, UserDto.getPassword())) {
            throw new ApiException("密码不正确");
        }
        // 密码校验成功后登录，一行代码实现登录
        StpUtil.login(UserDto.getId());
        // 将用户信息存储到Session中
        StpUtil.getSession().set("userInfo",UserDto);
        // 获取当前登录用户Token信息
        saTokenInfo = StpUtil.getTokenInfo();
        return saTokenInfo;
    }
}
