package com.macro.mall.portal.service;


import com.macro.mall.common.api.CommonResult;

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

}
