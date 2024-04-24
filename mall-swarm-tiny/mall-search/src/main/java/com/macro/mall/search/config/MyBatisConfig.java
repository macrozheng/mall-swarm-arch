package com.macro.mall.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @auther macrozheng
 * @description MyBatis相关配置
 * @date 2019/4/8
 * @github https://github.com/macrozheng
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.macro.mall.mapper","com.macro.mall.search.dao"})
public class MyBatisConfig {
}
