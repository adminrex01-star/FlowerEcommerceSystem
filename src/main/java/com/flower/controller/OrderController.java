package com.flower.controller;

import com.flower.po.Order;
import com.flower.po.User;
import com.flower.service.CartService;
import com.flower.service.OrderService;
import com.flower.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    // 提交订单（支持全结算和部分结算）
    @PostMapping("/order/submit")
    public String submitOrder(@RequestParam("receiver") String receiver,
                              @RequestParam("phone") String phone,
                              @RequestParam("address") String address,
                              @RequestParam(value = "cartIds", required = false) List<Integer> cartIds,
                              HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        if ("admin".equals(user.getRole())) {
            return "redirect:/admin";
        }
        Map<String, Object> orderData = new HashMap<>();
        orderData.put("userId", user.getUserId());
        orderData.put("receiver", receiver);
        orderData.put("phone", phone);
        orderData.put("address", address);
        orderData.put("cartIds", cartIds);
        Integer orderId = orderService.createOrder(orderData);
        if (orderId != null) {
            return "redirect:/orders";
        } else {
            return "redirect:/cart?error=orderFailed";
        }
    }

    // 订单成功页（可选，订单提交后直接跳转订单列表，此页面可保留或删除）
    @GetMapping("/order/success")
    public String orderSuccess(@RequestParam Integer orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "order_success";
    }

    // 我的订单列表
    @GetMapping("/orders")
    public String myOrders(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "5") int pageSize,
                           HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        PageResult<Order> page = orderService.getUserOrders(user.getUserId(), pageNum, pageSize);
        model.addAttribute("page", page);
        return "my_orders";
    }

    // 订单详情
    @GetMapping("/order/detail")
    public String orderDetail(@RequestParam Integer orderId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        Order order = orderService.getOrderById(orderId);
        if (!"admin".equals(user.getRole()) && !order.getUserId().equals(user.getUserId())) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        return "order_detail";
    }

    // 取消订单
    @GetMapping("/order/cancel")
    public String cancelOrder(@RequestParam Integer orderId, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        Order order = orderService.getOrderById(orderId);
        if (order != null && order.getUserId().equals(user.getUserId()) && "pending".equals(order.getOrderStatus())) {
            orderService.updateOrderStatus(orderId, "cancelled");
        }
        return "redirect:/orders";
    }
}