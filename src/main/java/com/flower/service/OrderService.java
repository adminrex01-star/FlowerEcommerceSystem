package com.flower.service;

import com.flower.po.Order;
import com.flower.util.PageResult;
import java.util.Map;

public interface OrderService {
    // 获取用户的订单列表（分页）
    PageResult<Order> getUserOrders(Integer userId, int pageNum, int pageSize);

    // 获取所有订单列表（管理员用，分页）
    PageResult<Order> getAllOrders(int pageNum, int pageSize);

    // 根据订单ID获取订单详情（包含订单项）
    Order getOrderById(Integer orderId);

    // 创建订单，参数包含 userId, receiver, phone, address，返回订单ID
    Integer createOrder(Map<String, Object> orderData);

    // 更新订单状态（pending/shipped/completed/cancelled）
    boolean updateOrderStatus(Integer orderId, String status);

    // 删除订单
    boolean deleteOrder(Integer orderId);
}