package com.macro.mall.config;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther macrozheng
 * @description Spring Boot Admin Http跟踪配置
 * @date 2024/1/16
 * @github https://github.com/macrozheng
 */
@Configuration
public class HttpExchangeConfig {

    @Bean
    public HttpExchangeRepository httpExchangeRepository() {
        InMemoryHttpExchangeRepository repository = new InMemoryHttpExchangeRepository();
        //设置保存HTTP请求记录的条数
        repository.setCapacity(1000);
        return repository;
    }
}

