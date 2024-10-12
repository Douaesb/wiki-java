package org.wiki.wikijava.database;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;


    private DatabaseConnection() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            properties.load(input);
            String URL = properties.getProperty("db.url");
            String USER = properties.getProperty("db.username");
            String PASSWORD = properties.getProperty("db.password");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }


    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}