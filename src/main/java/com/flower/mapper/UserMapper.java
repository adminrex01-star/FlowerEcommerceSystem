package com.flower.mapper;

import com.flower.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {
    User selectByUsername(@Param("username") String username);
    User selectByUserId(@Param("userId") Integer userId);
    List<User> selectAll();      // 新增：查询所有用户
    int insert(User user);
    int update(User user);
    int updatePassword(@Param("userId") Integer userId, @Param("password") String password);
    int deleteById(@Param("userId") Integer userId);  // 新增：删除用户
}