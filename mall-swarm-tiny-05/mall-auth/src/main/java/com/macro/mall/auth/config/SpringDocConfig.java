package com.macro.mall.auth.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther macrozheng
 * @description SpringDoc API文档相关配置
 * @date 2022/3/4
 * @github https://github.com/macrozheng
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Mall-Auth API")
                        .description("Mall-Auth API 演示")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/macrozheng/mall-learning")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot实战电商项目mall（50K+Star）全套文档")
                        .url("http://www.macrozheng.com"));
    }

}

