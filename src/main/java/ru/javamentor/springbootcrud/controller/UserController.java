package ru.javamentor.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootcrud.model.Role;
import ru.javamentor.springbootcrud.model.User;
import ru.javamentor.springbootcrud.service.UserService;

import java.util.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/users")
    public List<User> userByID(@PathVariable("id") int id, Model model) {
        return userService.getUsers();

    }

    @GetMapping(value = "/admin")
    public String getUsersAdmin(ModelMap model, SecurityContextHolderAwareRequestWrapper requestWrapper) {
        List<User> users = userService.getUsers();
        List<String> rolesList = new ArrayList<>();
        Map<Long, String> idRoles = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            rolesList.add(users.get(i).getId() + users.get(i).getAuthorities().toString());
            idRoles.put(users.get(i).getId(), users.get(i).getAuthorities()
                    .toString().replace("[", "")
                    .replace(",", "").replace("]", "")
                    .replace("ROLE_", ""));
        }
        model.addAttribute("roles", idRoles);
        model.addAttribute("users", users);
        return "/admin/adminPanel";
    }

    @GetMapping("/admin/new")
    public String creationForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "/admin/adminPanel";

    }

    @PostMapping()
    public String createUser(@RequestParam String name,
                             @RequestParam String lastName, @RequestParam String age,
                             @RequestParam String email, @RequestParam String password,
                             @RequestParam Set<Role> roles) {
        User user = (User) new User(name, lastName, age, email, password, roles);
        userService.createUser(user);
        return "redirect:/admin";

    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       SecurityContextHolderAwareRequestWrapper requestWrapper) {
        model.addAttribute("users", userService.getUser(id));
        Set<String> rolesDisplay = new HashSet<>();
        if (requestWrapper.isUserInRole("ROLE_ADMIN")) {
            rolesDisplay.add("ADMIN");
        }
        if (requestWrapper.isUserInRole("ROLE_USER")) {
            rolesDisplay.add("USER");
        }
        String formattedString = rolesDisplay.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();

        model.addAttribute("roles", formattedString);

        return "/user/showBoot";
    }

    @GetMapping("/admin/{id}/edit")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userService.getUser(id));
        return "/admin/edit";
    }

    @RequestMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
