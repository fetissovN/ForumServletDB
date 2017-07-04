package com.nick.forum.dao.user;


import com.nick.forum.entity.User;

import java.sql.SQLException;

public interface UserDao {

    void saveNewUser(User user) throws SQLException;

    void deleteUserById(int id);

    User getUserByLogin(String login) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    User getUserById(int id);
}
