package com.macro.mall.portal.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.dto.UserDto;

/**
 * @auther macrozheng
 * @description 会员管理Service
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

    /**
     * 用户名密码登录
     */
    SaTokenInfo login(String username, String password);

    /**
     * 根据用户名获取用户信息
     */
    UserDto getAdminByUsername(String username);
}
