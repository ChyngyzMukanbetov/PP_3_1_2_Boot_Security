package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(value = "/")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userService.allUsers());
//        return "Users";
//    }

//    @GetMapping(value = "/add")
//    public String addPage(Model model) {
//        return "AddUser";
//    }
//
//    @PostMapping(value = "/add")
//    public String addUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/";
//    }
//
//    @GetMapping(value = "/edit/{id}")
//    public String editPage(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getById(id));
//        return "EditUser";
//    }
//
//    @PostMapping(value = "/edit")
//    public String editUser (@ModelAttribute("user") User user) {
//        userService.update(user);
//        return "redirect:/";
//    }
//
//    @GetMapping(value = "/delete/{id}")
//    public String deleteUser(@PathVariable("id") long id) {
//        userService.delete(userService.getById(id));
//        return "redirect:/";
//    }

    @GetMapping(value = "/index")
        public String index () {
        return "index";
    }
}
