package com.yuanfentiankon.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TagDTO {
    
    @NotNull
    private Integer categoryId;
    
    @NotBlank
    private String name;
    
    private String description;
    
    private String icon;
    
    @NotNull
    private Float defaultWeight;
    
    @NotNull
    private Integer status;
}
