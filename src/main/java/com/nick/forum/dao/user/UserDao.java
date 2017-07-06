package com.nick.forum.dao.user;


import com.nick.forum.entity.User;

import java.sql.SQLException;

public interface UserDao {

    void saveNewUser(User user) throws SQLException;

    void deleteUserByEmail(String email) throws SQLException;

    User getUserByLogin(String login) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    User getUserById(int id);
}
