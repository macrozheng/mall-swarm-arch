package com.macro.mall.auth.controller;

import com.macro.mall.auth.service.UmsAdminService;
import com.macro.mall.auth.service.UmsMemberService;
import com.macro.mall.common.api.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther macrozheng
 * @description 统一认证授权接口
 * @date 2024/1/30
 * @github https://github.com/macrozheng
 */
@Controller
@Tag(name = "AuthController", description = "统一认证授权接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsMemberService memberService;

    @Operation(summary = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String clientId,
                              @RequestParam String username,
                              @RequestParam String password) {
        if("admin-app".equals(clientId)){
            return adminService.login(username,password);
        }else if("portal-app".equals(clientId)){
            return memberService.login(username,password);
        }else{
            return CommonResult.failed("clientId不正确");
        }
    }
}
