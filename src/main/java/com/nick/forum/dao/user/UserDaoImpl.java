package com.nick.forum.dao.user;

import java.sql.Connection;

import com.nick.forum.entity.User;
import com.nick.forum.jdbc.ConnectionJDBC;
import com.nick.forum.jdbc.ConnectionPool;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    ConnectionPool pool = new ConnectionPool(ConnectionJDBC.getUrl(),
            ConnectionJDBC.getUserName(),
            ConnectionJDBC.getPassword(),
            ConnectionJDBC.getDriver(),
            5);


    @Override
    public void saveNewUser(User user) {
        try {
            Connection conn =  pool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (nick,email,dateLog) VALUES (?,?,?)");
            preparedStatement.setString(1,user.getNick());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setDate(3, (Date) user.getDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
