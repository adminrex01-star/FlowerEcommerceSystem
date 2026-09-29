package com.flower.service;

import com.flower.po.User;
import  java.util.List;

public interface UserService {
    User login(String username, String password);
    boolean register(User user);
    User getUserById(Integer id);
    boolean updateProfile(User user);
    boolean changePassword(Integer userId, String oldPassword, String newPassword);
    List<User> getAllUsers();      // 新增
    boolean deleteUser(Integer userId);  // 新增
    boolean addUser(User user);     // 新增：管理员添加用户
    boolean updateUserRole(User user); // 新增：管理员修改角色等
}