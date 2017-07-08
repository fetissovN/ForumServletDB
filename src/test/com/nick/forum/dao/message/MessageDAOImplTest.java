package com.nick.forum.dao.message;

import com.nick.forum.entity.Message;
import concurrent.NewThread;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ForkJoinWorkerThread;

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
    public void getConns() throws InterruptedException {
        long start = System.currentTimeMillis();
        int n = 100;
        Thread[] threads = new Thread[n];
        for (int i=0;i<n;i++){
            Thread newThread = new Thread(new NewThread(i));
            threads[i]=newThread;
            newThread.start();
        }
        for (int i = 0; i < threads.length; i++) {
            Thread nextThread = threads[i];
            if (nextThread.isAlive()){
                try {
                    synchronized (nextThread) {
                        nextThread.wait();
                    }
                } catch (InterruptedException e) {
                    fail("interrupted exception");
                }
            }
        }
        long finish = System.currentTimeMillis();
        long time = finish-start;
        if ((time)<2000){
            assertTrue("100 connections in less than 2000ms",true);
        }else {
            assertFalse("Too long" + time + "it is more than 2000ms", true);
        }
    }
}