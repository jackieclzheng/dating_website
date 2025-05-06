// package com.yuanfentiankon.repository;

// import com.yuanfentiankon.model.entity.UserTag;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import java.util.List;
// import java.util.Optional;

// @Repository
// public interface UserTagRepository extends JpaRepository<UserTag, Long> {
    
//     List<UserTag> findByUserId(Long userId);
    
//     @Query("SELECT ut FROM UserTag ut WHERE ut.user.id = :userId AND ut.tag.category.id = :categoryId")
//     List<UserTag> findByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Integer categoryId);
    
//     Optional<UserTag> findByUserIdAndTagId(Long userId, Long tagId);
    
//     void deleteByUserIdAndTagId(Long userId, Long tagId);
    
//     @Query("SELECT ut.tag.id, AVG(ut.importance) FROM UserTag ut GROUP BY ut.tag.id")
//     List<Object[]> findAverageImportanceByTagId();
    
//     @Query("SELECT ut FROM UserTag ut WHERE ut.user.id = :userId1 AND ut.tag.id IN " +
//            "(SELECT ut2.tag.id FROM UserTag ut2 WHERE ut2.user.id = :userId2)")
//     List<UserTag> findCommonTags(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
// }
