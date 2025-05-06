// package com.yuanfentiankon.service.impl;

// import com.yuanfentiankon.exception.IllegalOperationException;
// import com.yuanfentiankon.exception.ResourceNotFoundException;
// import com.yuanfentiankon.model.dto.TagDTO;
// import com.yuanfentiankon.model.entity.Tag;
// import com.yuanfentiankon.model.entity.TagCategory;
// import com.yuanfentiankon.model.entity.UserTag;
// import com.yuanfentiankon.repository.TagCategoryRepository;
// import com.yuanfentiankon.repository.TagRepository;
// import com.yuanfentiankon.repository.UserTagRepository;
// import com.yuanfentiankon.service.TagService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.*;
// import java.util.stream.Collectors;

// @Service
// public class TagServiceImpl implements TagService {
    
//     private final TagCategoryRepository tagCategoryRepository;
//     private final TagRepository tagRepository;
//     private final UserTagRepository userTagRepository;
    
//     @Autowired
//     public TagServiceImpl(TagCategoryRepository tagCategoryRepository,
//                          TagRepository tagRepository,
//                          UserTagRepository userTagRepository) {
//         this.tagCategoryRepository = tagCategoryRepository;
//         this.tagRepository = tagRepository;
//         this.userTagRepository = userTagRepository;
//     }
    
//     @Override
//     public List<TagCategory> getAllCategories() {
//         return tagCategoryRepository.findAllActiveCategories();
//     }
    
//     @Override
//     public TagCategory getCategoryById(Integer id) {
//         return tagCategoryRepository.findById(id)
//             .orElseThrow(() -> new ResourceNotFoundException("标签类别不存在，ID: " + id));
//     }
    
//     @Override
//     @Transactional
//     public TagCategory createCategory(TagCategory category) {
//         return tagCategoryRepository.save(category);
//     }
    
//     @Override
//     @Transactional
//     public TagCategory updateCategory(Integer id, TagCategory category) {
//         TagCategory existingCategory = getCategoryById(id);
        
//         existingCategory.setName(category.getName());
//         existingCategory.setDescription(category.getDescription());
//         existingCategory.setSortOrder(category.getSortOrder());
//         existingCategory.setStatus(category.getStatus());
        
//         return tagCategoryRepository.save(existingCategory);
//     }
    
//     @Override
//     @Transactional
//     public void deleteCategory(Integer id) {
//         TagCategory category = getCategoryById(id);
        
//         // 检查是否有标签关联此类别
//         if (!category.getTags().isEmpty()) {
//             throw new IllegalOperationException("无法删除包含关联标签的类别");
//         }
        
//         tagCategoryRepository.delete(category);
//     }
    
//     @Override
//     public List<Tag> getAllTags() {
//         return tagRepository.findAll();
//     }
    
//     @Override
//     public List<Tag> getTagsByCategory(Integer categoryId) {
//         return tagRepository.findActiveByCategoryId(categoryId);
//     }
    
//     @Override
//     public List<Tag> searchTags(String keyword) {
//         if (keyword == null || keyword.trim().isEmpty()) {
//             return Collections.emptyList();
//         }
        
//         return tagRepository.findByNameContaining(keyword.trim());
//     }
    
//     @Override
//     public Tag getTagById(Long id) {
//         return tagRepository.findById(id)
//             .orElseThrow(() -> new ResourceNotFoundException("标签不存在，ID: " + id));
//     }
    
//     @Override
//     @Transactional
//     public Tag createTag(TagDTO tagDTO) {
//         // 获取类别
//         TagCategory category = tagCategoryRepository.findById(tagDTO.getCategoryId())
//             .orElseThrow(() -> new ResourceNotFoundException("标签类别不存在，ID: " + tagDTO.getCategoryId()));
        
//         // 创建标签
//         Tag tag = new Tag();
//         tag.setCategory(category);
//         tag.setName(tagDTO.getName());
//         tag.setDescription(tagDTO.getDescription());
//         tag.setIcon(tagDTO.getIcon());
//         tag.setDefaultWeight(tagDTO.getDefaultWeight());
//         tag.setUseCount(0);
//         tag.setStatus(tagDTO.getStatus());
        
//         return tagRepository.save(tag);
//     }
    
//     @Override
//     @Transactional
//     public Tag updateTag(Long id, TagDTO tagDTO) {
//         Tag existingTag = getTagById(id);
        
//         // 获取类别
//         if (!Objects.equals(existingTag.getCategory().getId(), tagDTO.getCategoryId())) {
//             TagCategory category = tagCategoryRepository.findById(tagDTO.getCategoryId())
//                 .orElseThrow(() -> new ResourceNotFoundException("标签类别不存在，ID: " + tagDTO.getCategoryId()));
//             existingTag.setCategory(category);
//         }
        
//         // 更新标签信息
//         existingTag.setName(tagDTO.getName());
//         existingTag.setDescription(tagDTO.getDescription());
//         existingTag.setIcon(tagDTO.getIcon());
//         existingTag.setDefaultWeight(tagDTO.getDefaultWeight());
//         existingTag.setStatus(tagDTO.getStatus());
        
//         return tagRepository.save(existingTag);
//     }
    
//     @Override
//     @Transactional
//     public void deleteTag(Long id) {
//         Tag tag = getTagById(id);
        
//         // 检查标签是否被用户使用
//         List<UserTag> userTags = userTagRepository.findByUserId(id);
//         if (!userTags.isEmpty()) {
//             // 可以选择软删除，将状态设置为禁用，而不是物理删除
//             tag.setStatus(0);
//             tagRepository.save(tag);
//         } else {
//             tagRepository.delete(tag);
//         }
//     }
    
//     @Override
//     public Map<Long, Integer> getTagUsageCounts() {
//         Map<Long, Integer> tagUsageCounts = new HashMap<>();
        
//         for (Tag tag : tagRepository.findAll()) {
//             Integer count = userTagRepository.findByUserId(tag.getId()).size();
//             tagUsageCounts.put(tag.getId(), count);
//         }
        
//         return tagUsageCounts;
//     }
    
//     // @Override
//     // public List<Tag> getPopularTags(int limit) {
//     //     // 获取所有标签的使用次数
//     //     Map<Long, Integer> tagUsageCounts = getTagUsageCounts();
        
//     //     // 根据使用次数排序并限制数量
//     //     return tagRepository.findAll().stream()
//     //         .sorted((t1, t2) -> {
//     //             Integer count1 = tagUsageCounts.getOrDefault(t1.getId(), 0);
//     //             Integer count2 = tagUsageCounts.getOrDefault(t2.getId(), 0);
//     //             return count2.compareTo(count1);
//     //         })
//     //         .limit(limit)
//     //         .collect(Collectors.toList());
//     // }
    
//     // @Override
//     // public List<Tag> getRecommendedTags(Long userId, int limit) {
//     //     // 获取用户已有标签
//     //     List<Tag> userTags = tagRepository.findByUserId(userId);
//     //     Set<Long> userTagIds = userTags.stream()
//     //         .map(Tag::getId)
//     //         .collect(Collectors.toSet());
        
//     //     // 获取热门标签，排除用户已有标签
//     //     return getPopularTags(limit * 2).stream()
//     //         .filter(tag -> !userTagIds.contains(tag.getId()))
//     //         .limit(limit)
//     //         .collect(Collectors.toList());
//     // }
// }
