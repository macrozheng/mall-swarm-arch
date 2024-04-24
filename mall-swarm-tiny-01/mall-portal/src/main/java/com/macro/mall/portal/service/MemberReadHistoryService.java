package com.macro.mall.portal.service;



import com.macro.mall.portal.domain.MemberReadHistory;

import java.util.List;

/**
 * @auther macrozheng
 * @description 会员浏览记录管理Service
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<MemberReadHistory> list(Long memberId);
}
