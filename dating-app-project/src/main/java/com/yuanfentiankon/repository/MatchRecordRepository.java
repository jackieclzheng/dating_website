package com.yuanfentiankon.repository;

import com.yuanfentiankon.model.entity.MatchRecord;
import com.yuanfentiankon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRecordRepository extends JpaRepository<MatchRecord, Long> {
    
    @Query("SELECT mr FROM MatchRecord mr WHERE (mr.user.id = :userId AND mr.target.id = :targetId) " +
           "OR (mr.user.id = :targetId AND mr.target.id = :userId)")
    List<MatchRecord> findByUserAndTarget(@Param("userId") Long userId, @Param("targetId") Long targetId);
    
    Optional<MatchRecord> findByUserIdAndTargetId(Long userId, Long targetId);
    
    @Query("SELECT mr FROM MatchRecord mr WHERE mr.user.id = :userId AND mr.action != 'DISLIKE'")
    List<MatchRecord> findUserLikes(@Param("userId") Long userId);
    
    @Query("SELECT mr FROM MatchRecord mr WHERE mr.target.id = :userId AND mr.action != 'DISLIKE'")
    List<MatchRecord> findTargetOfLikes(@Param("userId") Long userId);
    
    @Query("SELECT mr FROM MatchRecord mr WHERE (mr.user.id = :userId OR mr.target.id = :userId) AND mr.matched = true")
    List<MatchRecord> findUserMatches(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(mr) FROM MatchRecord mr WHERE (mr.user.id = :userId OR mr.target.id = :userId) AND mr.matched = true")
    Integer countUserMatches(@Param("userId") Long userId);
    
    @Query("SELECT mr.target FROM MatchRecord mr WHERE mr.user.id = :userId AND mr.action != 'DISLIKE' AND mr.matched = false")
    List<User> findPendingLikes(@Param("userId") Long userId);
}
