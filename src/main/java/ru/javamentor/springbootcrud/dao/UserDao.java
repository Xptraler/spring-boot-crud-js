package ru.javamentor.springbootcrud.dao;



import ru.javamentor.springbootcrud.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    void deleteUser(int id);
    List<User> getUsers();
    User getUser(int id);
    void update(int id, User user);
    User getUserByUsername(String username);
    User getByEmail(String email);
    void updateUser( User user);
}
