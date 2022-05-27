package org.example;

// must import connection from java SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


// since we need a connection to the SQL data we need factory to produce that connection
//this class gives that connection
// design patterns: factory and singleton (because we only need one instance of the connection)



public class ConnectionFactory {

// this is our connection to the SQL database
    private static Connection connection = null;

// make a private constructor meaning we can't manually instantiate this factory
    private ConnectionFactory() {

    }

// this method will return a connection to the SQL
    public static Connection getConnection() {
        if (connection == null) {
// if we don't have a connection yet, we can create one
// can access the values from outside the file (dbConfig.properties)
            ResourceBundle resourceBundle = ResourceBundle.getBundle("dbConfig");
            String url = resourceBundle.getString("url");
            String username = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");
            //String url = "jdbc:postgresql://localhost:5432/customerbank";
            //String username = "postgres";
            //String password = "Verizon1!";
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Something went wrong with the connection");
            }
        }
        return connection;
    }
}
