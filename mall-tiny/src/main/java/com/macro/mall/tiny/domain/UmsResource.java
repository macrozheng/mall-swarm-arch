package com.macro.mall.tiny.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 后台资源表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class UmsResource{

    private Long id;

    private Date createTime;

    private String name;

    private String url;

    private String description;

    private Long categoryId;

}
