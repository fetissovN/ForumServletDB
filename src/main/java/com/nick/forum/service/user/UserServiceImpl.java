package com.nick.forum.service.user;


import com.nick.forum.dao.user.UserDaoImpl;
import com.nick.forum.entity.User;

public class UserServiceImpl implements UserService {


    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void saveUser(User user) {
        userDao.saveNewUser(user);
    }

    @Override
    public boolean userExists(User user) {
        if (loginExists(user.getNick())&&emailExists(user.getEmail())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean loginExists(String login) {
        User user = userDao.getUserByLogin(login);
        if (user==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean emailExists(String email) {
        User user = userDao.getUserByLogin(email);
        if (user==null){
            return false;
        }else {
            return true;
        }
    }
}
