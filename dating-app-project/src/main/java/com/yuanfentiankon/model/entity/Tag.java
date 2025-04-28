package com.yuanfentiankon.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tags")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TagCategory category;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(length = 200)
    private String description;
    
    @Column(length = 100)
    private String icon;
    
    @Column(nullable = false)
    private Float defaultWeight;
    
    @Column(nullable = false)
    private Integer useCount;
    
    @Column(nullable = false)
    private Integer status;
}
