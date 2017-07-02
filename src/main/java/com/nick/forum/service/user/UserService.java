package com.nick.forum.service.user;


import com.nick.forum.entity.User;

public interface UserService {

    void saveUser(User user);

    boolean userExists(User user);

    boolean loginExists(String login);

    boolean emailExists(String email);
}
