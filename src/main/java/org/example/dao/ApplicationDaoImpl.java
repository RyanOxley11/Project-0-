package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ApplicationDaoImpl implements ApplicationDao {

    Connection connection;

    public ApplicationDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Customer customer) {
        String sql = " insert into customerbank (id, customerName, joinDate, accountBalance) values (default, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
// fill in the missing '?' values with the data from the customers
            preparedStatement.setString(1, customer.getCustomerName());
            LocalTime lt = LocalTime.now();

            preparedStatement.setString(2, String.valueOf(LocalDateTime.now()));
            preparedStatement.setFloat(3, customer.getAccountBalance());
// now that statement is prepared we can execute it
            int count = preparedStatement.executeUpdate();
// count is how many rows are affected ( ideally 1 since we are inserting 1 customer)
            if (count == 1) {
                String sql1 = "insert into transactions (transactionid, customerid, action, timestamp) values (default, ?, ?, ?);";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);


                preparedStatement1.setInt(1, customer.getId());
                preparedStatement1.setString(2, "Account Apply");
                preparedStatement1.setString(3, String.valueOf(LocalDateTime.now()));
                preparedStatement1.executeUpdate();

                System.out.println("Your account was created but is awaiting approval!");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                System.out.println("Generated Id is: " + id);
            } else {
                System.out.println("Something went wrong when creating account.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void accept(Customer customer) {
        String sql = " insert into customerbank (id, customerName, joinDate) values (default, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
// fill in the missing '?' values with the data from the customers
            preparedStatement.setString(1, customer.getCustomerName());
            LocalTime lt = LocalTime.now();

            preparedStatement.setString(2, String.valueOf(LocalDateTime.now()));
// now that statement is prepared we can execute it
            int count = preparedStatement.executeUpdate();
// count is how many rows are affected ( ideally 1 since we are inserting 1 customer)
            if (count == 1) {
                System.out.println("Your account was created but is awaiting approval!");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                System.out.println("Generated Id is: " + id);
            } else {
                System.out.println("Something went wrong when creating account.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "select * from customerbank where id = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idDatabase1 = resultSet.getInt("id");
                String customerName = resultSet.getString("customerName");
                String joinDate = String.valueOf(LocalDateTime.now());
                float accountBalance = resultSet.getFloat("accountBalance");
                return new Customer(idDatabase1, customerName, joinDate, accountBalance);


            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
       try {
           String sql = "update customerbank set accountbalance = ? where id = ?;";
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setFloat(1, customer.getAccountBalance());
           preparedStatement.setInt(2, customer.getId());
           int count = preparedStatement.executeUpdate();
           if(count == 1){
               System.out.println("Transaction successful!");
               String sql1 = "insert into transactions (transactionid, customerid, action, timeStamp) values (default, ?, ?, ?);";
               PreparedStatement preparedStatement1 = connection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);

               LocalTime lt = LocalTime.now();

               preparedStatement1.setInt(1, customer.getId());
               preparedStatement1.setString(2, "Deposit/Withdraw");
               preparedStatement1.setString(3, String.valueOf(LocalDateTime.now()));
               preparedStatement1.executeUpdate();
           }
           else {
               System.out.println("Error with transaction! Please try again!");
           }

       }
       catch (SQLException e) {
           e.printStackTrace();
       }
    }

    @Override
    public void transfer(Customer customer) {
        try {
            String sql = "update customerbank set accountbalance = ? where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, customer.getAccountBalance());
            preparedStatement.setInt(2, customer.getId());
            int count = preparedStatement.executeUpdate();


            if(count == 1){
                System.out.println("Transfer of funds successful!");
                String sql1 = "insert into transactions (transactionid, customerid, action, timeStamp) values (default, ?, ?, ?);";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);

                LocalTime lt = LocalTime.now();

                preparedStatement1.setInt(1, customer.getId());
                preparedStatement1.setString(2, "Transfer");
                preparedStatement1.setString(3, String.valueOf(LocalDateTime.now()));
                preparedStatement1.executeUpdate();

            }
            else {
                System.out.println("Error with Transfer! Please try again!");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transferAccept(Customer customer) {
        try {
            String sql = "update customerbank set accountbalance = ? where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, customer.getAccountBalance());
            preparedStatement.setInt(2, customer.getId());
            int count = preparedStatement.executeUpdate();


            if(count == 1){
                System.out.println("Transfer of funds successful!");
                String sql1 = "insert into transactions (transactionid, customerid, action, timeStamp) values (default, ?, ?, ?);";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);

                LocalTime lt = LocalTime.now();

                preparedStatement1.setInt(1, customer.getId());
                preparedStatement1.setString(2, "Transfer");
                preparedStatement1.setString(3, String.valueOf(LocalDateTime.now()));
                preparedStatement1.executeUpdate();

            }
            else {
                System.out.println("Error with Transfer! Please try again!");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
