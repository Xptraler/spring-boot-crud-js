package ru.javamentor.springbootcrud.service;



import ru.javamentor.springbootcrud.model.Role;
import ru.javamentor.springbootcrud.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void deleteUser(int id);
    List<User> getUsers();
    User getUser(int id);
    void update(int id, User user);
    User getUserByUsername(String username);
    Role getRole(int id);
    void updateUser(User user);
}
