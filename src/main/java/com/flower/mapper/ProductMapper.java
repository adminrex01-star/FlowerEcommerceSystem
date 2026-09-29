package com.flower.mapper;

import com.flower.po.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectByPage(@Param("offset") int offset, @Param("limit") int limit,
                               @Param("keyword") String keyword, @Param("categoryId") Integer categoryId);
    long count(@Param("keyword") String keyword, @Param("categoryId") Integer categoryId);
    Product selectById(@Param("productId") Integer productId);
    int insert(Product product);
    int update(Product product);
    int deleteById(@Param("productId") Integer productId);
    int updateStock(@Param("productId") Integer productId, @Param("quantity") int quantity);
    List<Product> selectAllForExport();   // 新增
}