package com.nick.forum.dao.message;

import com.nick.forum.entity.Message;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;


public class MessageDAOImplTest {

    private MessageDAOImpl messageDAO;

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

    @Test
    public void getConns(){
        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Message> list = messageDAO.getAllMessages();
                        System.out.println(list.size());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("поток "+i);
        }
    }
}