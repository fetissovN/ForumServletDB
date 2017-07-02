package com.nick.forum.dao.message;

import com.nick.forum.entity.Message;

import java.util.List;

public interface MessageDAO {

    void saveMessage(Message message);

    void deleteMessageById(int id);

    List<Message> getAllMessages();

    List<Message> getUsersMessages(int idUser);
}
