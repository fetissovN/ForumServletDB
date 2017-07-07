package com.nick.forum.entity;

import java.sql.Timestamp;

public class User {

    private int id;

    private String nick;

    private String email;

    private Timestamp date;

    public User() {
    }

    public User(int id, String nick, String email, Timestamp date) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
