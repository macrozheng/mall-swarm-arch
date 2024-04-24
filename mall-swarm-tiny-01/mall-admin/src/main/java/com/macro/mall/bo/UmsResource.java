package com.macro.mall.bo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @auther macrozheng
 * @description 后台资源
 * @date 2024/1/18
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class UmsResource {

    private Long id;

    private Date createTime;

    private String name;

    private String url;

    private String description;

    private Long categoryId;

}
