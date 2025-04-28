package com.yuanfentiankon.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profile")
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @Column(nullable = false, length = 10)
    private String gender;  // male or female
    
    @Column
    private Integer age;
    
    @Column
    private Integer height;
    
    @Column(length = 100)
    private String location;
    
    @Column(length = 100)
    private String occupation;
    
    @Column(length = 50)
    private String education;  // high_school, bachelor, master, phd
    
    @Column(length = 20)
    private String maritalStatus;  // single, divorced, widowed
    
    @Column
    private Boolean hasChildren;
    
    @Column(length = 1000)
    private String bio;
    
    @Column(length = 500)
    private String interests;
    
    @Column(length = 500)
    private String lookingFor;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
