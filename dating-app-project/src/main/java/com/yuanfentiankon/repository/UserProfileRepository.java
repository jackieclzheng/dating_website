package com.yuanfentiankon.repository;

import com.yuanfentiankon.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(Long userId);
    
    @Query("SELECT p FROM UserProfile p WHERE p.gender = :gender AND p.id != :userId")
    List<UserProfile> findPotentialMatches(@Param("userId") Long userId, @Param("gender") String gender);
    
    @Query("SELECT p FROM UserProfile p WHERE p.gender = :gender " +
           "AND p.age BETWEEN :minAge AND :maxAge " +
           "AND p.height BETWEEN :minHeight AND :maxHeight " +
           "AND (:location IS NULL OR p.location LIKE %:location%) " +
           "AND p.id != :userId")
    List<UserProfile> findPotentialMatchesWithFilters(
            @Param("userId") Long userId,
            @Param("gender") String gender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("minHeight") Integer minHeight,
            @Param("maxHeight") Integer maxHeight,
            @Param("location") String location);
}
