package com.yuanfentiankon.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UserRegistrationDTO {
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    
    @NotBlank
    @Size(min = 6, max = 100)
    private String confirmPassword;
    
    @Email
    private String email;
    
    @Size(max = 20)
    private String phone;
    
    @Size(max = 10)
    private String gender;
    
    private LocalDate birthday;
    
    @Size(max = 100)
    private String location;
}
