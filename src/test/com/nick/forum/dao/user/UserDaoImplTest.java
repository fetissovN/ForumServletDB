package com.nick.forum.dao.user;

import com.nick.forum.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private static final UserDaoImpl userDao = new UserDaoImpl();
    private static final String email = "email@test.com";
    private static final int id = 999;
    private static final User user = new User(id,"nick",email,new Date(System.currentTimeMillis()));

    @Test
    public void saveNewUser() throws Exception {
        userDao.saveNewUser(user);
        User user = userDao.getUserByEmail(email);
        if (user==null){
            fail("User is null");
        }else if (user.getEmail().equals(email)){
            deleteUserByEmail();
            assertTrue(true);
        }
    }

    @Test
    public void deleteUserByEmail() {
        try {
            userDao.deleteUserByEmail(email);
            assertTrue(true);
        } catch (SQLException e) {
            assertTrue("SQL error delete",false);
        }
    }

}