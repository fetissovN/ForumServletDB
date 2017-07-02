package com.nick.forum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    private static final String url = "jdbc:mysql://localhost:3306/servlet_forum?autoReconnect=true&useSSL=false";
    private static final String userName = "root";
    private static final String password = "root";
    private static final String driver = "com.mysql.jdbc.Driver";

    private Connection connection;


    public Connection getConnection() {
        return connection;
    }

    public ConnectionJDBC() {

    }

    public void closeConnection() throws SQLException{
        connection.close();
    }

    public void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(url,userName,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
}
