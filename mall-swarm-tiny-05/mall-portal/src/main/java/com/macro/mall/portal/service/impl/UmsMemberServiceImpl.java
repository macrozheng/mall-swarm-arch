package com.macro.mall.portal.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.dto.UserDto;
import com.macro.mall.common.exception.ApiException;
import com.macro.mall.common.service.RedisService;
import com.macro.mall.portal.service.UmsMemberService;
import com.macro.mall.portal.util.StpMemberUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @auther macrozheng
 * @description 会员管理Service实现类
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    /**
     * 存放默认用户信息
     */
    private List<UserDto> memberDtoList = new ArrayList<>();

    @PostConstruct
    private void init(){
        memberDtoList.add(UserDto.builder()
                .id(1L)
                .username("member")
                .password(BCrypt.hashpw("123456"))
                .build());
        memberDtoList.add(UserDto.builder()
                .id(2L)
                .username("guest")
                .password(BCrypt.hashpw("123456"))
                .build());
    }

    @Override
    public UserDto getAdminByUsername(String username) {
        List<UserDto> findList = memberDtoList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(findList)){
            return findList.get(0);
        }
        return null;
    }

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }


    //对输入的验证码进行校验
    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StrUtil.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }

    @Override
    public SaTokenInfo login(String username, String password) {
        SaTokenInfo saTokenInfo;
        UserDto UserDto = getAdminByUsername(username);
        if (UserDto == null) {
            return null;
        }
        if (!BCrypt.checkpw(password, UserDto.getPassword())) {
            throw new ApiException("密码不正确");
        }
        // 密码校验成功后登录，一行代码实现登录
        StpMemberUtil.login(UserDto.getId());
        // 将用户信息存储到Session中
        StpMemberUtil.getSession().set("memberInfo",UserDto);
        // 获取当前登录用户Token信息
        saTokenInfo = StpMemberUtil.getTokenInfo();
        return saTokenInfo;
    }

}
