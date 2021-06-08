package ru.javamentor.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootcrud.dao.RoleDao;
import ru.javamentor.springbootcrud.model.Role;
import ru.javamentor.springbootcrud.model.User;
import ru.javamentor.springbootcrud.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/")
public class RestController {
    private final UserService userService;
    private final RoleDao roleDao;

    @Autowired
    public RestController(UserService userService, RoleDao roleDao) {

        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUser(Math.toIntExact(id));
    }

    @CachePut
    @PutMapping("/update/{role}")
    public void update(@RequestBody User user, @PathVariable String role) {
        Set<Role> roles = new HashSet<>();
        System.out.println(role);
        System.out.println("before");
        System.out.println(user);
        System.out.println(roleDao.getOneRole(1));
        if (role.equals("ADMIN")) {
            roles.add(roleDao.getOneRole(2));
            user.setRoles(roles);
        }
        if (role.equals("USER")) {
            roles.add(roleDao.getOneRole(1));
            user.setRoles(roles);
        }
        userService.updateUser(user);
    }

}