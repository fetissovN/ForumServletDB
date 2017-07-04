package com.nick.forum.dao.message;

import com.nick.forum.entity.Message;
import com.nick.forum.jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Николай on 28.06.2017.
 */
public class MessageDAOImpl implements MessageDAO {

    private ConnectionPool pool = new ConnectionPool(5);

    @Override
    public void saveMessage(Message message) throws SQLException {
        Connection conn =  pool.retrieve();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users_messages(message,user_id,message_date) VALUES (?,?,?)");
            preparedStatement.setString(1,message.getMessage());
            preparedStatement.setInt(2,message.getUserId());
            preparedStatement.setDate(3, message.getMessage_date());
            preparedStatement.execute();
        }finally {
            pool.putback(conn);
        }
    }

    @Override
    public void deleteMessageById(int id) {

    }

    @Override
    public List<Message> getAllMessages() {
        return null;
    }

    @Override
    public List<Message> getUsersMessages(int idUser) {
        return null;
    }
}
