package com.flower.service;

import com.flower.po.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getCartByUserId(Integer userId);
    boolean addToCart(Integer userId, Integer productId, Integer quantity);
    boolean updateQuantity(Integer cartId, Integer quantity);
    boolean removeFromCart(Integer cartId);
    void clearCart(Integer userId);
    List<Cart> getCartsByIds(List<Integer> cartIds);
}