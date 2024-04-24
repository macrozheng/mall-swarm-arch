package com.macro.mall.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther macrozheng
 * @description 文件上传返回结果
 * @date 2019/12/25
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode
public class MinioUploadDto {
    private String url;
    private String name;
}
