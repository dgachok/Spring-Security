package com.registrapp.dao;

import com.registrapp.models.Role;
import com.registrapp.models.User;

import java.util.List;

public interface UserDao {

    void addUser(User user, Role role);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    List<User> getAllUser();

}
