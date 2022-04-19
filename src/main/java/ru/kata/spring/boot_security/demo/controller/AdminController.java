package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    private RoleRepository roleRepository;

    Set<Role> roles1 = new HashSet<>();

    @Autowired
    public AdminController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "Users";
    }

    @GetMapping(value = "/add")
    public String addPage(Model model) {
        return "AddUser";
    }

    @PostMapping(value = "/add")
    public String addUser(
            @RequestParam("roles") String[] roles,
            @ModelAttribute("user") User user)
    {
        user.setRoles(null);

        for (String role: roles)
        {
            if (roleRepository.findAll().contains(role)) {
                roles1.addAll(roleRepository.findById(Long.parseLong(role)).stream().collect(Collectors.toSet()));
            }
        }
        user.setRoles(roles1);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "EditUser";
    }

    @PostMapping(value = "/edit")
    public String editUser (
            @RequestParam("roles") String[] roles,
            @ModelAttribute("user") User user) {

        user.setRoles(null);

        for (String role: roles)
              {
            if (roleRepository.findAll().contains(role)) {
                roles1.add(roleRepository.findByRolename(role));
            }
        }
        user.setRoles(roles1);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }

    @GetMapping(value = "/index")
    public String index () {
        return "index";
    }

    @GetMapping(value = "/test2")
    public String test () {
        return "test2";
    }



    @PostMapping(value = "/test2")
    public void test2(@RequestParam(defaultValue = "false") boolean checkbox) {
        if (checkbox) {
            System.out.println();
        }
    }


}
