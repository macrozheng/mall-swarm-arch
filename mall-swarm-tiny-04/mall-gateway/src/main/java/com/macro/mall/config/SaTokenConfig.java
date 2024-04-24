package com.macro.mall.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.util.StpMemberUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

/**
 * @auther macrozheng
 * @description Sa-Token相关配置
 * @date 2023/11/28
 * @github https://github.com/macrozheng
 */
@Configuration
public class SaTokenConfig {

    /**
     * 注册Sa-Token全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter(IgnoreUrlsConfig ignoreUrlsConfig) {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 配置白名单路径
                .setExcludeList(ignoreUrlsConfig.getUrls())
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录认证：商城前台会员认证
                    SaRouter.match("/mall-portal/**", r -> StpMemberUtil.checkLogin());
                    // 登录认证：管理后台用户认证
                    SaRouter.match("/mall-admin/**", r -> StpUtil.checkLogin());
                    // 权限认证：后台用户不同接口访问权限不同
                    SaRouter.match("/mall-admin/brand/create", r -> StpUtil.checkPermission("brand:create"));
                    SaRouter.match("/mall-admin/brand/update/**", r -> StpUtil.checkPermission("brand:update"));
                    SaRouter.match("/mall-admin/brand/delete/**", r -> StpUtil.checkPermission("brand:delete"));
                    SaRouter.match("/mall-admin/brand/list", r -> StpUtil.checkPermission("brand:list"));
                    SaRouter.match("/mall-admin/brand/listAll", r -> StpUtil.checkPermission("brand:listAll"));
                })
                // setAuth方法异常处理
                .setError(this::handleException);
    }

    /**
     * 自定义异常处理
     */
    private CommonResult handleException(Throwable e) {
        //设置错误返回格式为JSON
        ServerWebExchange exchange = SaReactorSyncHolder.getContext();
        exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
        CommonResult result = null;
        if(e instanceof NotLoginException){
            result = CommonResult.unauthorized(null);
        }else if(e instanceof NotPermissionException){
            result = CommonResult.forbidden(null);
        }else{
            result = CommonResult.failed(e.getMessage());
        }
        return result;
    }
}

