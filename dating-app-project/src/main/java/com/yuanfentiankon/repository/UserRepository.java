package com.yuanfentiankon.repository;

import com.yuanfentiankon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByGender(String gender);
    
    List<User> findByGenderAndLocation(String gender, String location);
    
    @Query("SELECT u FROM User u WHERE u.gender != (SELECT u2.gender FROM User u2 WHERE u2.id = :userId) AND u.id != :userId")
    List<User> findPotentialMatches(@Param("userId") Long userId);
}
