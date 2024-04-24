package com.macro.mall.service;



import com.macro.mall.model.PmsBrand;

import java.util.List;

/**
 * @auther macrozheng
 * @description 品牌管理Service
 * @date 2019/4/19
 * @github https://github.com/macrozheng
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
