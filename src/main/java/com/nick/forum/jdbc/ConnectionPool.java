package com.nick.forum.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
    private static final String url = "jdbc:mysql://localhost:3306/servlet_forum?autoReconnect=true&useSSL=false";
    private static final String userName = "root";
    private static final String password = "root";
    private static final String driver = "com.mysql.jdbc.Driver";

    private Vector<Connection> availableConnections = new Vector<>();
    private Vector<Connection> usedConnections = new Vector<>();

    private static ConnectionPool instance = null;

    private ConnectionPool(int initConnCount) {
        try {
            Class.forName(this.driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getAvailableConnections()<2){
            for (int i = 0; i < initConnCount; i++) {
                availableConnections.addElement(getConnection());
            }
        }
    }

    public synchronized static ConnectionPool getInstance(int initConnCount){
        if (instance==null){
            instance = new ConnectionPool(initConnCount);
        }
        return instance;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public synchronized Connection retrieve() throws SQLException {
        Connection newConn;
        if (getAvailableConnections() == 0) {
            newConn = getConnection();
        } else {
            newConn = availableConnections.lastElement();
            availableConnections.removeElement(newConn);
        }
        usedConnections.addElement(newConn);
        return newConn;
    }

    public synchronized void putback(Connection c) throws RuntimeException{
        if (c != null) {
            if (usedConnections.removeElement(c)) {
                availableConnections.addElement(c);
            } else {
                throw new RuntimeException("Connection not in the used connections vector");
            }
        }
    }

    public int getAvailableConnections() {
        return availableConnections.size();
    }
}
