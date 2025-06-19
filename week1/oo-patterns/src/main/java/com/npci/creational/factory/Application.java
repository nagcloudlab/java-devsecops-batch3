package com.npci.creational.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/*
 JAVA + SQL databases
 2 options:
 1. JDBC api
 2. JPA (Java Persistence API)
    - Hibernate  ( most used JPA implementation )
    - EclipseLink
    - OpenJPA
 */

/*

 steps to use JDBC api

    1. Load jdbc driver
    2. Create a connection
    3. Create a statement
    4. Execute the statement
    5. Process the result
    6. Close the connection

 */

// Connection Pooling library
// HikariCP (most used)
// Apache DBCP
// C3P0


// Factory class
class ConnectionFactory {

    static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/db.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // One time Intialization for the JDBC driver
    static {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        }
    }

    // Factory method
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = java.sql.DriverManager.getConnection(url, user, password);
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;

    }
}


class Module1 {
    void doSomething() {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println("User Name: " + name);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        } finally {
            // Ideally, you should close the connection, statement, and resultSet here
            // to avoid resource leaks. This is omitted for brevity.
            if (connection != null) {
                try {
                    connection.close();
                } catch (java.sql.SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }

    }
}

class Module2 {
    void doSomethingElse() {

        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println("User Name: " + name);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        } finally {
            // Ideally, you should close the connection, statement, and resultSet here
            // to avoid resource leaks. This is omitted for brevity.
            if (connection != null) {
                try {
                    connection.close();
                } catch (java.sql.SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }

    }


}


public class Application {
    public static void main(String[] args) {

        Module1 module1 = new Module1();
        for (int i = 0; i < 10000; i++) {
            module1.doSomething();
        }

//        Module2 module2 = new Module2();
//        module2.doSomethingElse();

        // Additional logic can be added here to utilize the modules further


    }
}
