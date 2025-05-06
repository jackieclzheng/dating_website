// package com.yuanfentiankon.repository;

// import com.yuanfentiankon.model.entity.TagCategory;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface TagCategoryRepository extends JpaRepository<TagCategory, Integer> {
    
//     @Query("SELECT tc FROM TagCategory tc WHERE tc.status = 1 ORDER BY tc.sortOrder ASC")
//     List<TagCategory> findAllActiveCategories();
// }
