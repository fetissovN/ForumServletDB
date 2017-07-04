package com.nick.forum.service.user;


import com.nick.forum.entity.User;

import java.sql.SQLException;

public interface UserService {

    void saveUser(User user) throws SQLException;

    boolean userExists(User user) throws SQLException;

    boolean loginExists(String login) throws SQLException;

    boolean emailExists(String email) throws SQLException;

    User getUserByEmail(String email) throws SQLException;
}
