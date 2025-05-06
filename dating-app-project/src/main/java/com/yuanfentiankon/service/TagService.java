// package com.yuanfentiankon.service;

// import com.yuanfentiankon.model.dto.TagDTO;
// import com.yuanfentiankon.model.entity.Tag;
// import com.yuanfentiankon.model.entity.TagCategory;
// import com.yuanfentiankon.repository.TagRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Map;

// @Service
// public class TagService {

//     @Autowired
//     private TagRepository tagRepository;

//     // 标签类别相关方法
//     public List<TagCategory> getAllCategories() {
//         // Implementation here
//         return null;
//     }

//     public TagCategory getCategoryById(Integer id) {
//         // Implementation here
//         return null;
//     }

//     public TagCategory createCategory(TagCategory category) {
//         // Implementation here
//         return null;
//     }

//     public TagCategory updateCategory(Integer id, TagCategory category) {
//         // Implementation here
//         return null;
//     }

//     public void deleteCategory(Integer id) {
//         // Implementation here
//     }

//     // 标签相关方法
//     public List<Tag> getAllTags() {
//         return tagRepository.findAll();
//     }

//     public List<Tag> getTagsByCategory(Integer categoryId) {
//         // Implementation here
//         return null;
//     }

//     public List<Tag> searchTags(String keyword) {
//         // Implementation here
//         return null;
//     }

//     public Tag getTagById(Long id) {
//         return tagRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("标签不存在"));
//     }

//     // @Transactional
//     // public Tag createTag(TagDTO tagDTO) {
//     //     Tag tag = new Tag();
//     //     // Set properties from tagDTO to tag
//     //     // Implementation here
//     //     if (isTagNameDuplicated(tag)) {
//     //         throw new RuntimeException("标签名称已存在");
//     //     }
//     //     return tagRepository.save(tag);
//     // }

//     // @Transactional
//     // public Tag updateTag(Long id, TagDTO tagDTO) {
//     //     Tag tag = tagRepository.findById(id)
//     //             .orElseThrow(() -> new RuntimeException("标签不存在"));
//     //     // Update properties from tagDTO to tag
//     //     // Implementation here
//     //     if (isTagNameDuplicated(tag)) {
//     //         throw new RuntimeException("标签名称已存在");
//     //     }
//     //     return tagRepository.save(tag);
//     // }

//     @Transactional
//     public void deleteTag(Long id) {
//         tagRepository.deleteById(id);
//     }

//     @Transactional
//     public void deleteTagsByIds(List<Long> ids) {
//         tagRepository.deleteAllById(ids);
//     }

//     // public boolean isTagNameAvailable(String name, Long id) {
//     //     Tag existingTag = tagRepository.findByName(name);
//     //     if (existingTag == null) {
//     //         return true;
//     //     }
//     //     return id != null && existingTag.getId().equals(id);
//     // }

//     // private boolean isTagNameDuplicated(Tag tag) {
//     //     Tag existingTag = tagRepository.findByName(tag.getName());
//     //     return existingTag != null && !existingTag.getId().equals(tag.getId());
//     // }

//     // 标签统计相关方法
//     public Map<Long, Integer> getTagUsageCounts() {
//         // Implementation here
//         return null;
//     }

//     public List<Tag> getPopularTags(int limit) {
//         // Implementation here
//         return null;
//     }

//     public List<Tag> getRecommendedTags(Long userId, int limit) {
//         // Implementation here
//         return null;
//     }
// }
