package com.flower.controller;

import com.flower.po.User;
import com.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            if ("admin".equals(user.getRole())) {
                return "redirect:/admin";   // 关键：跳转到管理首页
            }
            return "redirect:/";
        }
        model.addAttribute("error", "用户名或密码错误");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        // 后端二次校验手机号格式
        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            if (!user.getPhone().matches("^1[3-9]\\d{9}$")) {
                model.addAttribute("error", "手机号格式错误（11位数字，1开头）");
                return "register";
            }
        }
        // 后端二次校验邮箱格式
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            if (!user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                model.addAttribute("error", "邮箱格式错误");
                return "register";
            }
        }
        boolean success = userService.register(user);
        if (success) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";
        User user = userService.getUserById(loginUser.getUserId());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(User user, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";
        user.setUserId(loginUser.getUserId());
        if (userService.updateProfile(user)) {
            session.setAttribute("loginUser", userService.getUserById(loginUser.getUserId()));
            model.addAttribute("message", "保存成功");
        } else {
            model.addAttribute("error", "保存失败");
        }
        return "profile";
    }

    @PostMapping("/profile/password")
    public String changePassword(String oldPassword, String newPassword, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";
        if (userService.changePassword(loginUser.getUserId(), oldPassword, newPassword)) {
            model.addAttribute("message", "密码修改成功，请重新登录");
            session.invalidate();
            return "redirect:/login";
        }
        model.addAttribute("pwdError", "原密码错误");
        return "profile";
    }
}