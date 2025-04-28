package com.yuanfentiankon.model.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_preferences")
@Data
public class MatchPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;
    
    @Column(nullable = false)
    private Integer minAge = 18;
    
    @Column(nullable = false)
    private Integer maxAge = 50;
    
    @Column(nullable = false)
    private Integer minHeight = 150;
    
    @Column(nullable = false)
    private Integer maxHeight = 200;
    
    @Column(length = 100)
    private String locationPreference;
    
    @Column(length = 50)
    private String educationPreference;
    
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
