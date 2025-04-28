package com.yuanfentiankon.service.impl;

import com.yuanfentiankon.exception.EmailAlreadyExistsException;
import com.yuanfentiankon.exception.ResourceNotFoundException;
import com.yuanfentiankon.exception.UsernameAlreadyExistsException;
import com.yuanfentiankon.model.dto.UserDTO;
import com.yuanfentiankon.model.dto.UserRegistrationDTO;
import com.yuanfentiankon.model.entity.User;
import com.yuanfentiankon.repository.UserRepository;
import com.yuanfentiankon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    @Transactional
    public User registerUser(UserRegistrationDTO registrationDTO) {
        // 校验用户名是否已存在
        if (userRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("用户名已存在");
        }
        
        // 校验邮箱是否已存在
        if (registrationDTO.getEmail() != null && 
            userRepository.findByEmail(registrationDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("邮箱已存在");
        }
        
        // 校验两次密码是否一致
        if (!Objects.equals(registrationDTO.getPassword(), registrationDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("两次密码不一致");
        }
        
        // 创建用户对象
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());
        user.setPhone(registrationDTO.getPhone());
        user.setGender(registrationDTO.getGender());
        user.setBirthday(registrationDTO.getBirthday());
        user.setLocation(registrationDTO.getLocation());
        user.setRole("USER");
        user.setStatus(1);
        
        return userRepository.save(user);
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("用户不存在，ID: " + id));
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("用户不存在，用户名: " + username));
    }
    
    @Override
    @Transactional
    public User updateUserProfile(Long userId, UserDTO userDTO) {
        User user = getUserById(userId);
        
        // 更新用户信息
        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(user.getEmail())) {
            // 检查邮箱是否已被其他用户使用
            userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(u -> {
                    if (!u.getId().equals(userId)) {
                        throw new EmailAlreadyExistsException("邮箱已存在");
                    }
                });
            user.setEmail(userDTO.getEmail());
        }
        
        // 更新其他字段
        user.setPhone(userDTO.getPhone());
        user.setGender(userDTO.getGender());
        user.setBirthday(userDTO.getBirthday());
        user.setLocation(userDTO.getLocation());
        user.setEducation(userDTO.getEducation());
        user.setOccupation(userDTO.getOccupation());
        user.setBio(userDTO.getBio());
        
        // 更新头像
        if (userDTO.getAvatar() != null) {
            user.setAvatar(userDTO.getAvatar());
        }
        
        return userRepository.save(user);
    }
    
    @Override
    @Transactional
    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = getUserById(userId);
        
        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("旧密码不正确");
        }
        
        // 更新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    
    @Override
    public List<User> getRecommendedUsers(Long userId, int page, int size) {
        // 简单实现，获取当前用户异性用户列表
        List<User> potentialMatches = userRepository.findPotentialMatches(userId);
        
        // 分页处理
        int start = page * size;
        int end = Math.min(start + size, potentialMatches.size());
        
        if (start >= potentialMatches.size()) {
            return List.of();
        }
        
        return potentialMatches.subList(start, end);
    }
    
    @Override
    public List<User> getUsersByGender(String gender) {
        return userRepository.findByGender(gender);
    }
    
    @Override
    public List<User> getUsersByGenderAndLocation(String gender, String location) {
        return userRepository.findByGenderAndLocation(gender, location);
    }
    
    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
    
    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
