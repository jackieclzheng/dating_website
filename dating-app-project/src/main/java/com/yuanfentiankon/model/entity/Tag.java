// package com.yuanfentiankon.model.entity;

// import javax.persistence.*;
// import lombok.Data;

// @Data
// @Entity
// @Table(name = "tags")
// public class Tag {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String name;

//     @Column(nullable = false)
//     private String icon;

//     @Column(length = 500)
//     private String description;

//     @Column(name = "category")
//     private String category;  // 标签分类：兴趣/性格/职业等

//     @Column(name = "sort_order")
//     private Integer sortOrder;  // 排序权重

//     @Column(name = "is_active")
//     private Boolean isActive = true;  // 是否启用

//     @Column(name = "use_count")
//     private Integer useCount = 0;
// }
