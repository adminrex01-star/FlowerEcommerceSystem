package com.flower.mapper;

import com.flower.po.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> selectByUserId(@Param("userId") Integer userId);
    Cart selectByUserAndProduct(@Param("userId") Integer userId, @Param("productId") Integer productId);
    int insert(Cart cart);
    int updateQuantity(@Param("cartId") Integer cartId, @Param("quantity") Integer quantity);
    int deleteById(@Param("cartId") Integer cartId);
    int deleteByUserId(@Param("userId") Integer userId);
    // 新增：根据ID列表查询购物车项（用于部分结算）
    List<Cart> selectByIds(@Param("cartIds") List<Integer> cartIds);
}