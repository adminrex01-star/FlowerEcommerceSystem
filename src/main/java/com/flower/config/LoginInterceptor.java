package com.flower.config;

import com.flower.po.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 登录拦截器：未登录用户禁止访问核心页面
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        String uri = request.getRequestURI();
        // 放行登录、注册、静态资源
        if (uri.contains("/login") || uri.contains("/register") ||
                uri.contains("/css") || uri.contains("/js") || uri.contains("/images")) {
            return true;
        }
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}