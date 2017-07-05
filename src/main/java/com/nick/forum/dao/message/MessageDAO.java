package com.nick.forum.dao.message;

import com.nick.forum.entity.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDAO {

    void saveMessage(Message message) throws SQLException;

    void deleteMessageById(int id);

    List<Message> getAllMessages() throws SQLException;

    List<Message> getUsersMessages(int idUser);
}
