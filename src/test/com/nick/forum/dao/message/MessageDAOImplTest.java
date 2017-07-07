package com.nick.forum.dao.message;

import com.nick.forum.entity.Message;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;


public class MessageDAOImplTest {

    MessageDAOImpl messageDAO;

    @Before
    public void setUp() throws Exception {
        messageDAO = MessageDAOImpl.getInstance();
    }

    @Test
    public void getAllMessages() {
        try {
            List<Message> list = messageDAO.getAllMessages();
            assertTrue(list.size()>1);
            assertFalse("List is null",list==null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}