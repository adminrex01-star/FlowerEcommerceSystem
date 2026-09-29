package com.flower.controller;

import com.flower.po.Cart;
import com.flower.po.User;
import com.flower.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    // 查看购物车
    @GetMapping("/cart")
    public String cartPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        if ("admin".equals(user.getRole())) {
            return "redirect:/admin";
        }
        List<Cart> cartList = cartService.getCartByUserId(user.getUserId());
        double total = cartList.stream().mapToDouble(c -> c.getPrice() * c.getQuantity()).sum();
        model.addAttribute("cartList", cartList);
        model.addAttribute("total", total);
        return "cart";
    }

    // 加入购物车
    @GetMapping("/cart/add")
    public String addToCart(@RequestParam Integer productId, @RequestParam(defaultValue = "1") Integer quantity,
                            HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        if ("admin".equals(user.getRole())) {
            return "redirect:/admin";
        }
        cartService.addToCart(user.getUserId(), productId, quantity);
        return "redirect:/cart";
    }

    // 更新购物车数量
    @PostMapping("/cart/update")
    public String updateCart(@RequestParam Integer cartId, @RequestParam Integer quantity) {
        cartService.updateQuantity(cartId, quantity);
        return "redirect:/cart";
    }

    // 删除购物车项
    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam Integer cartId) {
        cartService.removeFromCart(cartId);
        return "redirect:/cart";
    }

    // 跳转到结算页面（支持多个选中项）
    @PostMapping("/cart/checkoutSelected")
    public String checkoutSelected(@RequestParam(value = "cartIds", required = false) List<Integer> cartIds,
                                   HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        if ("admin".equals(user.getRole())) {
            return "redirect:/admin";
        }
        if (cartIds == null || cartIds.isEmpty()) {
            return "redirect:/cart?error=noSelect";
        }
        List<Cart> selectedCarts = cartService.getCartsByIds(cartIds);
        model.addAttribute("selectedCarts", selectedCarts);
        model.addAttribute("user", user);
        return "checkout";   // 需要创建 checkout.html
    }

    // 全购物车结算（原逻辑保持不变，方便使用）
    @GetMapping("/order/checkout")
    public String checkoutAll(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        if ("admin".equals(user.getRole())) {
            return "redirect:/admin";
        }
        List<Cart> cartList = cartService.getCartByUserId(user.getUserId());
        if (cartList.isEmpty()) {
            return "redirect:/cart?error=empty";
        }
        model.addAttribute("selectedCarts", cartList);
        model.addAttribute("user", user);
        return "checkout";
    }
}