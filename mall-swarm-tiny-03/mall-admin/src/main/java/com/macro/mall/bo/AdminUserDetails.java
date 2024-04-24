package com.macro.mall.bo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

/**
 * @auther macrozheng
 * @description SpringSecurity用户信息封装类
 * @date 2020/10/15
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class AdminUserDetails {
    private String username;
    private String password;
    private List<String> authorityList;
    public Collection<String> getAuthorities() {
        return authorityList;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

}
