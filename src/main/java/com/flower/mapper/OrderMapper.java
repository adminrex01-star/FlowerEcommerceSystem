package com.flower.mapper;

import com.flower.po.Order;
import com.flower.po.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> selectByUserId(@Param("userId") Integer userId, @Param("offset") int offset, @Param("limit") int limit);
    long countByUserId(@Param("userId") Integer userId);
    List<Order> selectAll(@Param("offset") int offset, @Param("limit") int limit);
    long countAll();
    Order selectById(@Param("orderId") Integer orderId);
    int insertOrder(Order order);
    int insertOrderItem(OrderItem item);
    int updateOrderStatus(@Param("orderId") Integer orderId, @Param("orderStatus") String orderStatus);
    int deleteOrder(@Param("orderId") Integer orderId);
    List<OrderItem> selectItemsByOrderId(@Param("orderId") Integer orderId);
}