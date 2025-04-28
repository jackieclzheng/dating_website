package com.yuanfentiankon.service;

import com.yuanfentiankon.model.entity.UserTag;

import java.util.List;

public interface UserTagService {
    
    /**
     * 获取用户标签列表
     */
    List<UserTag> getUserTags(Long userId);
    
    /**
     * 获取用户特定类别的标签列表
     */
    List<UserTag> getUserTagsByCategory(Long userId, Integer categoryId);
    
    /**
     * 添加用户标签
     */
    UserTag addUserTag(Long userId, Long tagId, Float importance);
    
    /**
     * 移除用户标签
     */
    void removeUserTag(Long userId, Long tagId);
    
    /**
     * 更新用户标签重要度
     */
    UserTag updateUserTagImportance(Long userId, Long tagId, Float importance);
    
    /**
     * 批量更新用户标签
     */
    void updateUserTags(Long userId, List<Long> tagIds, List<Float> importances);
    
    /**
     * 获取两个用户共同的标签
     */
    List<UserTag> getCommonTags(Long userId1, Long userId2);
    
    /**
     * 计算两个用户的标签相似度
     */
    float calculateTagSimilarity(Long userId1, Long userId2);
}
