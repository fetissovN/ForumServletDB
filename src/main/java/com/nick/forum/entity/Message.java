package com.nick.forum.entity;


import java.sql.Date;
import java.sql.Timestamp;

public class Message implements Comparable<Message>{

    private int id;

    private int userId;

    private String message;

    private Timestamp message_date;


    public Message() {
    }

    public Message(int userId, String message, Timestamp message_date) {
        this.userId = userId;
        this.message = message;
        this.message_date = message_date;
    }

    public Timestamp getMessage_date() {
        return message_date;
    }

    public void setMessage_date(Timestamp message_date) {
        this.message_date = message_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int compareTo(Message o) {
        if (this.message_date.before(o.getMessage_date())){
            return -1;
        }else if (this.message_date.after(o.getMessage_date())){
            return 1;
        }else {
            return 0;
        }
    }
}
