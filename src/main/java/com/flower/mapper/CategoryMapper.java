package com.flower.mapper;

import com.flower.po.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectAll();
    Category selectById(@Param("categoryId") Integer categoryId);
    int insert(Category category);
    int update(Category category);
    int deleteById(@Param("categoryId") Integer categoryId);
    // 分页查询
    List<Category> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
    // 查询总数
    long count();
}