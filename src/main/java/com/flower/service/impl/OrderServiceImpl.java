package com.flower.service.impl;

import com.flower.mapper.CartMapper;
import com.flower.mapper.OrderMapper;
import com.flower.mapper.ProductMapper;
import com.flower.po.Cart;
import com.flower.po.Order;
import com.flower.po.OrderItem;
import com.flower.po.Product;
import com.flower.service.OrderService;
import com.flower.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult<Order> getUserOrders(Integer userId, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        long total = orderMapper.countByUserId(userId);
        List<Order> list = orderMapper.selectByUserId(userId, offset, pageSize);
        for (Order order : list) {
            order.setItems(orderMapper.selectItemsByOrderId(order.getOrderId()));
        }
        return new PageResult<>(pageNum, pageSize, total, list);
    }

    @Override
    public PageResult<Order> getAllOrders(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        long total = orderMapper.countAll();
        List<Order> list = orderMapper.selectAll(offset, pageSize);
        for (Order order : list) {
            order.setItems(orderMapper.selectItemsByOrderId(order.getOrderId()));
        }
        return new PageResult<>(pageNum, pageSize, total, list);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order != null) {
            order.setItems(orderMapper.selectItemsByOrderId(orderId));
        }
        return order;
    }

    @Override
    @Transactional
    public Integer createOrder(Map<String, Object> orderData) {
        Integer userId = (Integer) orderData.get("userId");
        String receiver = (String) orderData.get("receiver");
        String phone = (String) orderData.get("phone");
        String address = (String) orderData.get("address");

        List<Cart> cartList = cartMapper.selectByUserId(userId);
        if (cartList == null || cartList.isEmpty()) {
            return null;
        }
        double totalPrice = 0.0;
        for (Cart cart : cartList) {
            Product product = productMapper.selectById(cart.getProductId());
            if (product.getStock() < cart.getQuantity()) {
                throw new RuntimeException("库存不足：" + product.getProductName());
            }
            totalPrice += product.getPrice() * cart.getQuantity();
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setReceiver(receiver);
        order.setPhone(phone);
        order.setAddress(address);
        order.setOrderStatus("pending");
        order.setPayStatus("unpaid");
        order.setCreateTime(new Date());
        orderMapper.insertOrder(order);
        Integer orderId = order.getOrderId();

        for (Cart cart : cartList) {
            Product product = productMapper.selectById(cart.getProductId());
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setProductId(product.getProductId());
            item.setProductName(product.getProductName());
            item.setPrice(product.getPrice());
            item.setNum(cart.getQuantity());
            orderMapper.insertOrderItem(item);
            productMapper.updateStock(product.getProductId(), cart.getQuantity());
        }
        cartMapper.deleteByUserId(userId);
        return orderId;
    }

    @Override
    public boolean updateOrderStatus(Integer orderId, String status) {
        return orderMapper.updateOrderStatus(orderId, status) > 0;
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        return orderMapper.deleteOrder(orderId) > 0;
    }
}