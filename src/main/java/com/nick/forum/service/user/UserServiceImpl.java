package com.nick.forum.service.user;


import com.nick.forum.dao.user.UserDaoImpl;
import com.nick.forum.entity.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = null;

    private UserServiceImpl() {
    }

    public synchronized static UserServiceImpl getInstance(){
        if (instance==null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    @Override
    public void saveUser(User user) throws SQLException {
        userDao.saveNewUser(user);
    }

    @Override
    public boolean userExists(User user) throws SQLException {
        return loginExists(user.getNick())&&emailExists(user.getEmail());
    }

    @Override
    public boolean loginExists(String login) throws SQLException {
        return userDao.getUserByEmail(login) != null;
    }

    @Override
    public boolean emailExists(String email) throws SQLException {
        return userDao.getUserByEmail(email) != null;

    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        return userDao.getUserByEmail(email);
    }
}
