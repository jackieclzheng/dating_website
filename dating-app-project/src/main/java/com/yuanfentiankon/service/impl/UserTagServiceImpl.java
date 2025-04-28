package com.yuanfentiankon.service.impl;

import com.yuanfentiankon.exception.ResourceNotFoundException;
import com.yuanfentiankon.model.entity.Tag;
import com.yuanfentiankon.model.entity.User;
import com.yuanfentiankon.model.entity.UserTag;
import com.yuanfentiankon.repository.TagRepository;
import com.yuanfentiankon.repository.UserRepository;
import com.yuanfentiankon.repository.UserTagRepository;
import com.yuanfentiankon.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserTagServiceImpl implements UserTagService {
    
    private final UserTagRepository userTagRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    
    @Autowired
    public UserTagServiceImpl(UserTagRepository userTagRepository,
                            UserRepository userRepository,
                            TagRepository tagRepository) {
        this.userTagRepository = userTagRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }
    
    @Override
    public List<UserTag> getUserTags(Long userId) {
        return userTagRepository.findByUserId(userId);
    }
    
    @Override
    public List<UserTag> getUserTagsByCategory(Long userId, Integer categoryId) {
        return userTagRepository.findByUserIdAndCategoryId(userId, categoryId);
    }
    
    @Override
    @Transactional
    public UserTag addUserTag(Long userId, Long tagId, Float importance) {
        // 检查用户是否存在
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("用户不存在，ID: " + userId));
        
        // 检查标签是否存在
        Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new ResourceNotFoundException("标签不存在，ID: " + tagId));
        
        // 检查用户是否已有该标签
        Optional<UserTag> existingUserTag = userTagRepository.findByUserIdAndTagId(userId, tagId);
        
        if (existingUserTag.isPresent()) {
            // 如果已有标签，则更新重要度
            UserTag userTag = existingUserTag.get();
            userTag.setImportance(importance);
            return userTagRepository.save(userTag);
        } else {
            // 创建新的用户标签关联
            UserTag userTag = new UserTag();
            userTag.setUser(user);
            userTag.setTag(tag);
            userTag.setImportance(importance);
            
            // 更新标签使用次数
            tag.setUseCount(tag.getUseCount() + 1);
            tagRepository.save(tag);
            
            return userTagRepository.save(userTag);
        }
    }
    
    @Override
    @Transactional
    public void removeUserTag(Long userId, Long tagId) {
        // 检查用户标签是否存在
        Optional<UserTag> userTag = userTagRepository.findByUserIdAndTagId(userId, tagId);
        
        if (userTag.isPresent()) {
            // 获取标签
            Tag tag = userTag.get().getTag();
            
            // 删除用户标签关联
            userTagRepository.deleteByUserIdAndTagId(userId, tagId);
            
            // 更新标签使用次数
            tag.setUseCount(Math.max(0, tag.getUseCount() - 1));
            tagRepository.save(tag);
        }
    }
    
    @Override
    @Transactional
    public UserTag updateUserTagImportance(Long userId, Long tagId, Float importance) {
        // 检查用户标签是否存在
        UserTag userTag = userTagRepository.findByUserIdAndTagId(userId, tagId)
            .orElseThrow(() -> new ResourceNotFoundException("用户标签不存在"));
        
        // 更新重要度
        userTag.setImportance(importance);
        
        return userTagRepository.save(userTag);
    }
    
    @Override
    @Transactional
    public void updateUserTags(Long userId, List<Long> tagIds, List<Float> importances) {
        if (tagIds.size() != importances.size()) {
            throw new IllegalArgumentException("标签ID列表和重要度列表长度不一致");
        }
        
        // 检查用户是否存在
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("用户不存在，ID: " + userId));
        
        // 批量更新标签
        for (int i = 0; i < tagIds.size(); i++) {
            Long tagId = tagIds.get(i);
            Float importance = importances.get(i);
            
            // 检查标签是否存在
            Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("标签不存在，ID: " + tagId));
            
            // 检查用户是否已有该标签
            Optional<UserTag> existingUserTag = userTagRepository.findByUserIdAndTagId(userId, tagId);
            
            if (existingUserTag.isPresent()) {
                // 如果已有标签，则更新重要度
                UserTag userTag = existingUserTag.get();
                userTag.setImportance(importance);
                userTagRepository.save(userTag);
            } else {
                // 创建新的用户标签关联
                UserTag userTag = new UserTag();
                userTag.setUser(user);
                userTag.setTag(tag);
                userTag.setImportance(importance);
                userTagRepository.save(userTag);
                
                // 更新标签使用次数
                tag.setUseCount(tag.getUseCount() + 1);
                tagRepository.save(tag);
            }
        }
    }
    
    @Override
    public List<UserTag> getCommonTags(Long userId1, Long userId2) {
        return userTagRepository.findCommonTags(userId1, userId2);
    }
    
    @Override
    public float calculateTagSimilarity(Long userId1, Long userId2) {
        // 获取两个用户的标签
        List<UserTag> userTags1 = getUserTags(userId1);
        List<UserTag> userTags2 = getUserTags(userId2);
        
        // 如果任一用户没有标签，则相似度为0
        if (userTags1.isEmpty() || userTags2.isEmpty()) {
            return 0.0f;
        }
        
        // 获取共同标签
        List<UserTag> commonTags = getCommonTags(userId1, userId2);
        
        // 计算相似度
        float similarity = 0.0f;
        
        // 简单实现：共同标签数量 / 两个用户标签总数的平均值
        float avgTagCount = (userTags1.size() + userTags2.size()) / 2.0f;
        similarity = commonTags.size() / avgTagCount;
        
        return similarity;
    }
}
