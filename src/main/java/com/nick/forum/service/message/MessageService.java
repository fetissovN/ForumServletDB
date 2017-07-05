package com.nick.forum.service.message;

import com.nick.forum.entity.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {

    void save(Message message) throws SQLException;

    List<Message> getListAll() throws SQLException;
}
