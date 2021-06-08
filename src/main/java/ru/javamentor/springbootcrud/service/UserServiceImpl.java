package ru.javamentor.springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springbootcrud.dao.RoleDao;
import ru.javamentor.springbootcrud.dao.UserDao;
import ru.javamentor.springbootcrud.model.Role;
import ru.javamentor.springbootcrud.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;


    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }


    @Transactional
    @Override
    public void createUser(User user) {
        Set<Role> roles = new HashSet<>();
        String role = user.getRoles().toString();
        if (role.contains("ADMIN")) {
            roles.add(roleDao.getOneRole(2));
            user.setRoles(roles);
        }
        if (role.contains("USER")) {
            roles.add(roleDao.getOneRole(1));
            user.setRoles(roles);
        }
        userDao.createUser(user);

    }

    @Override
    @Transactional
    @Modifying
    public void deleteUser(int id) {
        userDao.deleteUser(id);

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.getUsers();

    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);

    }

    @Override
    @Transactional
    public Role getRole(int id) {
        return roleDao.getOneRole(id);
    }

    @Override
    @Transactional
    @Modifying
    public void update(int id, User user) {
        userDao.update(id, user);
    }
    @Override
    @Transactional
    @Modifying
    public void updateUser( User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
