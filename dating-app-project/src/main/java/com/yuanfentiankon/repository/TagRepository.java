// package com.yuanfentiankon.repository;

// import com.yuanfentiankon.model.entity.Tag;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface TagRepository extends JpaRepository<Tag, Long> {
    
//     @Query("SELECT t FROM Tag t WHERE t.category.id = :categoryId AND t.status = 1")
//     List<Tag> findActiveByCategoryId(@Param("categoryId") Integer categoryId);
    
//     List<Tag> findByNameContaining(String keyword);
    
//     @Query("SELECT t FROM Tag t JOIN UserTag ut ON t.id = ut.tag.id WHERE ut.user.id = :userId")
//     List<Tag> findByUserId(@Param("userId") Long userId);
// }
