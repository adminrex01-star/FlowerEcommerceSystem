package com.flower.controller.admin;

import com.flower.po.Order;
import com.flower.service.OrderService;
import com.flower.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String orderList(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "10") int pageSize,
                            Model model) {
        PageResult<Order> page = orderService.getAllOrders(pageNum, pageSize);
        model.addAttribute("page", page);
        return "admin/order_list";
    }

    @GetMapping("/order/updateStatus")
    public String updateStatus(@RequestParam Integer orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/orders";
    }

    @GetMapping("/order/delete")
    public String deleteOrder(@RequestParam Integer orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/admin/orders";
    }
}