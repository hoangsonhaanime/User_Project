package com.example.practise_crud.controller;

import com.example.practise_crud.model.User;
import com.example.practise_crud.service.NotFoundException;
import com.example.practise_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("/users")
    public String showListUser(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "users";
    }
    @GetMapping("/users/new")
    public String showAddNewUser( Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new User");
        return "form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "Create, Update this User successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try{
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User with id : " + id);
            return "form";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e);
            return "redirect:/users";


        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Delete sucessfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
