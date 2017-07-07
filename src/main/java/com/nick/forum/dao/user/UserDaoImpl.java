package com.nick.forum.dao.user;

import java.sql.*;

import com.nick.forum.entity.User;
import com.nick.forum.jdbc.ConnectionPool;

public class UserDaoImpl implements UserDao{

    private ConnectionPool pool = ConnectionPool.getInstance(5);

    @Override
    public void saveNewUser(User user) throws SQLException {
        Connection conn =  pool.retrieve();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (nick,email,dateLog) VALUES (?,?,?)");
            preparedStatement.setString(1,user.getNick());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setTimestamp(3, user.getDate());
            preparedStatement.execute();
        }finally {
            pool.putback(conn);
        }
    }

    @Override
    public void deleteUserByEmail(String email) throws SQLException, RuntimeException{
        Connection conn =  pool.retrieve();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE email=?");
            preparedStatement.setString(1,email);
            preparedStatement.execute();
        }finally {
            pool.putback(conn);
        }

    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return getUserBy(1, login);
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        return getUserBy(2, email);
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    /**
     * digest 1 for searching by nick
     * digest 2 for searching by email
     * @param fieldName
     * @return
     */
    public User getUserBy(int fieldName, String name) throws SQLException {
        final String emailScript= "SELECT * FROM users WHERE email=?";
        final String loginScript= "SELECT * FROM users WHERE nick=?";
        String actualScript = null;
        if (fieldName==1){
            actualScript=loginScript;
        }else if (fieldName==2){
            actualScript=emailScript;
        }

        Connection conn =  pool.retrieve();
        User user = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(actualScript);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("nick"),
                        resultSet.getString("email"),
                        resultSet.getTimestamp("dateLog"));
            }
        }finally {
            pool.putback(conn);
        }
        return user;
    }
}
