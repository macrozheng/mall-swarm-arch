package com.macro.mall.portal.repository;


import com.macro.mall.portal.domain.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @auther macrozheng
 * @description 会员商品浏览历史Repository
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {
    /**
     * 根据会员id按时间倒序获取浏览记录
     * @param memberId 会员id
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
