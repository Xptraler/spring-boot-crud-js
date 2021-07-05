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

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUser(Math.toIntExact(id));
    }

    @PutMapping("/users/{role}")
    public void update(@RequestBody User user, @PathVariable(required = false) String role) {
        Set<Role> roles = new HashSet<>();
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

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

}