package com.nick.forum.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
    private Vector<Connection> availableConns = new Vector<>();
    private Vector<Connection> usedConns = new Vector<>();
    private String url;

    public ConnectionPool(String url, String user, String pass, String driver, int initConnCnt) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.url = url+user+pass;
        if (availableConns.size()<5){
            for (int i = 0; i < initConnCnt; i++) {
                availableConns.addElement(getConnection());
            }
        }

    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(ConnectionJDBC.getUrl(),
                    ConnectionJDBC.getUserName(),
                    ConnectionJDBC.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public synchronized Connection retrieve() throws SQLException {
        Connection newConn;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        return newConn;
    }

    public synchronized void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}
