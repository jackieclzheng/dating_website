package com.yuanfentiankon.service;

import com.yuanfentiankon.model.dto.UserDTO;
import com.yuanfentiankon.model.dto.UserRegistrationDTO;
import com.yuanfentiankon.model.entity.User;

import java.util.List;

public interface UserService {
    
    /**
     * 注册新用户
     */
    User registerUser(UserRegistrationDTO registrationDTO);
    
    /**
     * 根据ID获取用户
     */
    User getUserById(Long id);
    
    /**
     * 根据用户名获取用户
     */
    User getUserByUsername(String username);
    
    /**
     * 更新用户资料
     */
    User updateUserProfile(Long userId, UserDTO userDTO);
    
    /**
     * 更新用户密码
     */
    void updatePassword(Long userId, String oldPassword, String newPassword);
    
    /**
     * 获取推荐用户列表
     */
    List<User> getRecommendedUsers(Long userId, int page, int size);
    
    /**
     * 根据性别获取用户列表
     */
    List<User> getUsersByGender(String gender);
    
    /**
     * 根据性别和地区获取用户列表
     */
    List<User> getUsersByGenderAndLocation(String gender, String location);
    
    /**
     * 检查用户名是否已存在
     */
    boolean isUsernameExists(String username);
    
    /**
     * 检查邮箱是否已存在
     */
    boolean isEmailExists(String email);
}
