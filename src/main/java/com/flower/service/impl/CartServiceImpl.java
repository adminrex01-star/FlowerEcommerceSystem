package com.flower.service.impl;

import com.flower.mapper.CartMapper;
import com.flower.mapper.ProductMapper;
import com.flower.po.Cart;
import com.flower.po.Product;
import com.flower.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Cart> getCartByUserId(Integer userId) {
        return cartMapper.selectByUserId(userId);
    }

    @Override
    public boolean addToCart(Integer userId, Integer productId, Integer quantity) {
        Product product = productMapper.selectById(productId);
        if (product == null || product.getStock() < quantity) {
            return false;
        }
        Cart existCart = cartMapper.selectByUserAndProduct(userId, productId);
        if (existCart != null) {
            int newQuantity = existCart.getQuantity() + quantity;
            return cartMapper.updateQuantity(existCart.getCartId(), newQuantity) > 0;
        } else {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);
            return cartMapper.insert(cart) > 0;
        }
    }

    @Override
    public boolean updateQuantity(Integer cartId, Integer quantity) {
        if (quantity <= 0) {
            return cartMapper.deleteById(cartId) > 0;
        }
        return cartMapper.updateQuantity(cartId, quantity) > 0;
    }

    @Override
    public boolean removeFromCart(Integer cartId) {
        return cartMapper.deleteById(cartId) > 0;
    }

    @Override
    public void clearCart(Integer userId) {
        cartMapper.deleteByUserId(userId);
    }

    @Override
    public List<Cart> getCartsByIds(List<Integer> cartIds) {
        return cartMapper.selectByIds(cartIds);
    }
}