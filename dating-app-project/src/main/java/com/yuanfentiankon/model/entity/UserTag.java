package com.yuanfentiankon.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_tags")
@Data
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
    
    @Column(nullable = false)
    private Float importance;
}
