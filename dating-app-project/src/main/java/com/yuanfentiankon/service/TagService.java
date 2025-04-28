package com.yuanfentiankon.service;

import com.yuanfentiankon.model.dto.TagDTO;
import com.yuanfentiankon.model.entity.Tag;
import com.yuanfentiankon.model.entity.TagCategory;

import java.util.List;
import java.util.Map;

public interface TagService {
    
    // 标签类别相关方法
    List<TagCategory> getAllCategories();
    
    TagCategory getCategoryById(Integer id);
    
    TagCategory createCategory(TagCategory category);
    
    TagCategory updateCategory(Integer id, TagCategory category);
    
    void deleteCategory(Integer id);
    
    // 标签相关方法
    List<Tag> getAllTags();
    
    List<Tag> getTagsByCategory(Integer categoryId);
    
    List<Tag> searchTags(String keyword);
    
    Tag getTagById(Long id);
    
    Tag createTag(TagDTO tagDTO);
    
    Tag updateTag(Long id, TagDTO tagDTO);
    
    void deleteTag(Long id);
    
    // 标签统计相关方法
    Map<Long, Integer> getTagUsageCounts();
    
    List<Tag> getPopularTags(int limit);
    
    List<Tag> getRecommendedTags(Long userId, int limit);
}
