package com.Dennis.Boxinator.DBConn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseInit {
    private static String url;
    private static String username;
    private static String password;

    public void init(String filename) {
        Properties props = new Properties();

        try (FileInputStream in = new FileInputStream(filename)) {
            props.load(in);
            String driver = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            if (username == null || username.equals("")) {
                throw new NullPointerException("Missing username");
            }
            password = props.getProperty("jdbc.password");
            if (password == null || password.equals("")) {
                throw new NullPointerException("Missing Password");
            }
            if (driver != null) {
                Class.forName(driver);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("something %s",e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
