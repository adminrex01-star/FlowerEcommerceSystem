package com.flower.controller.admin;

import com.flower.po.User;
import com.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    // 用户列表
    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user_list";
    }

    // 新增用户页面
    @GetMapping("/user/add")
    public String addPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/user_form";
    }

    // 编辑用户页面
    @GetMapping("/user/edit")
    public String editPage(@RequestParam Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user_form";
    }

    // 保存用户（新增或修改）
    @PostMapping("/user/save")
    public String save(User user) {
        if (user.getUserId() == null) {
            userService.addUser(user);
        } else {
            userService.updateUserRole(user);
        }
        return "redirect:/admin/users";
    }

    // 删除用户
    @GetMapping("/user/delete")
    public String delete(@RequestParam Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}