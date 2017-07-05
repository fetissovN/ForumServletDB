package com.nick.forum.service.message;

import com.nick.forum.dao.message.MessageDAOImpl;
import com.nick.forum.entity.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService{

    private MessageDAOImpl messageDAO = new MessageDAOImpl();

    @Override
    public void save(Message message) throws SQLException {
        messageDAO.saveMessage(message);
    }

    @Override
    public List<Message> getListAll() throws SQLException {
        return messageDAO.getAllMessages();
    }
}
