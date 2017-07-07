package com.nick.forum.service.message;

import com.nick.forum.dao.message.MessageDAOImpl;
import com.nick.forum.entity.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService{

    private static MessageServiceImpl instance = null;

    private MessageServiceImpl() {
    }

    public synchronized static MessageServiceImpl getInstance(){
        if (instance==null){
            instance = new MessageServiceImpl();
        }
        return instance;
    }

    private MessageDAOImpl messageDAO = MessageDAOImpl.getInstance();

    @Override
    public void save(Message message) throws SQLException {
        messageDAO.saveMessage(message);
    }

    @Override
    public List<Message> getListAll() throws SQLException {
        return messageDAO.getAllMessages();
    }
}
