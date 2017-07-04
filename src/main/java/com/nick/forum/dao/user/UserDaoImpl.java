package com.nick.forum.dao.user;

import java.sql.*;

import com.nick.forum.entity.User;
import com.nick.forum.jdbc.ConnectionPool;

public class UserDaoImpl implements UserDao{

    private ConnectionPool pool = new ConnectionPool(5);


    @Override
    public void saveNewUser(User user) throws SQLException {
        Connection conn =  pool.retrieve();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (nick,email,dateLog) VALUES (?,?,?)");
            preparedStatement.setString(1,user.getNick());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setDate(3, user.getDate());
        }finally {
            pool.putback(conn);
        }
    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return getUserBy(1);
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        return getUserBy(2);
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
    public User getUserBy(int fieldName) throws SQLException {
        final String emailScript= "SELECT * FROM users WHERE email=?";
        final String loginScript= "SELECT * FROM users WHERE nick=?";
        String actualScript = null;
        String name = null;
        if (fieldName==1){
            actualScript=loginScript;
            name="nick";
        }else if (fieldName==2){
            actualScript=emailScript;
            name ="email";
        }

        Connection conn =  pool.retrieve();
        User user = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(actualScript);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User(resultSet.getInt("id"),resultSet.getString("nick"),resultSet.getString("email"),resultSet.getDate("dateLog"));
            }
        }finally {
            pool.putback(conn);
        }
        return user;
    }
}
