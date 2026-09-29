package com.flower.service.impl;

import com.flower.mapper.UserMapper;
import com.flower.po.User;
import com.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return userMapper.deleteById(userId) > 0;
    }

    @Override
    public boolean addUser(User user) {
        User existUser = userMapper.selectByUsername(user.getUsername());
        if (existUser != null) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword("123456");
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("user");
        }
        // 手机号格式校验
        if (user.getPhone() != null && !user.getPhone().isEmpty() && !user.getPhone().matches("^1[3-9]\\d{9}$")) {
            return false;
        }
        // 邮箱格式校验
        if (user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return false;
        }
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUserRole(User user) {
        return userMapper.update(user) > 0;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        // 手机号格式校验
        if (user.getPhone() != null && !user.getPhone().isEmpty() && !user.getPhone().matches("^1[3-9]\\d{9}$")) {
            return false;
        }
        // 邮箱格式校验
        if (user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return false;
        }
        User existUser = userMapper.selectByUsername(user.getUsername());
        if (existUser != null) {
            return false;
        }
        user.setRole("user");
        return userMapper.insert(user) > 0;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByUserId(id);
    }

    @Override
    public boolean updateProfile(User user) {
        // 手机号格式校验
        if (user.getPhone() != null && !user.getPhone().isEmpty() && !user.getPhone().matches("^1[3-9]\\d{9}$")) {
            return false;
        }
        // 邮箱格式校验
        if (user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return false;
        }
        return userMapper.update(user) > 0;
    }

    @Override
    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.selectByUserId(userId);
        if (user != null && user.getPassword().equals(oldPassword)) {
            return userMapper.updatePassword(userId, newPassword) > 0;
        }
        return false;
    }
}