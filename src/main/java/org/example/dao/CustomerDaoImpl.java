package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entity.Customer;
import org.example.entity.Transactions;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;



// implementation of CustomerDao methods
public class CustomerDaoImpl implements CustomerDao {

    Connection connection;

    public CustomerDaoImpl() {
// when we instantiate this class we get the connection
        connection = ConnectionFactory.getConnection();

    }

    @Override
    public void insert(Customer customer) {
// question marks serve as placeholders for real values
        String sql = " insert into customerbank (id, customerName, joinDate, accountBalance) values (default, ?, ?, ?);";
// use our connection to prepare a statement to send to the database, pass in string we made, as well as a flag
// that returns the generated keys (id since we don't know what it is the computer assigns)
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
// fill in the missing '?' values with the data from the customers
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, String.valueOf(LocalDateTime.now()));
            preparedStatement.setFloat(3, customer.getAccountBalance());
// now that statement is prepared we can execute it
            int count = preparedStatement.executeUpdate();
// count is how many rows are affected ( ideally 1 since we are inserting 1 customer)
            if (count == 1) {
                System.out.println("Customer added successfully!");
                String sql1 = "insert into transactions (transactionid, customerid, action, timeStamp) values (default, ?, ?, ?);";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);

                LocalTime lt = LocalTime.now();

                preparedStatement1.setInt(1, customer.getId());
                preparedStatement1.setString(2, "Account Apply");
                preparedStatement1.setString(3, String.valueOf(LocalDateTime.now()));
                preparedStatement1.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                customer.setId(id);
                System.out.println("");
                System.out.println("New Customer Id: #" +id);
                System.out.println("");
                System.out.println("Please inform customer of their new Id# as it will be used to access their account for future transactions");
                System.out.println("");
            } else {
                System.out.println("Something went wrong when adding customer.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "select * from customerbank where id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
// set the id using the id we passed in
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
// checking to see if we have a customer from this query
            if (resultSet.next()) {
// extract out the data 'idDatabase' because it is coming from database
                Customer customer = getCustomer(resultSet);

                return customer;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        String sql = "select * from customerbank where customerName = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
// set the id using the id we passed in
            preparedStatement.setString(1, customerName);
            ResultSet resultSet = preparedStatement.executeQuery();
// checking to see if we have a customer from this query
            if (resultSet.next()) {
// extract out the data 'idDatabase' because it is coming from database
                Customer customer = getCustomer(resultSet);

                return customer;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
// create a list of customers to store our results
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customerbank;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
// don't need to set any parameters because we're getting all customer
            ResultSet resultSet = preparedStatement.executeQuery();
// we use while loop because there are multiple results
            while (resultSet.next()) {
                Customer customer = getCustomer(resultSet);
// add customer to list of customers
                customers.add(customer);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }
// getting a customer from a result set
    public Customer getCustomer(ResultSet resultSet) {
        try {
            int idDatabase = resultSet.getInt("id");
            String customerName = resultSet.getString("customerName");
            String joinDate = resultSet.getString("joinDate");
            float accountBalance = resultSet.getFloat("accountBalance");
            return new Customer(idDatabase, customerName, joinDate, accountBalance);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transactions> getAllTransactions() {
// create a list of transaction to store our results
        List<Transactions> transactions = new ArrayList<Transactions>();
        String sql = "select * from transactions;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
// don't need to set any parameters because we're getting all customer
            ResultSet resultSet = preparedStatement.executeQuery();
// we use while loop because there are multiple results
            while (resultSet.next()) {
                int idDatabase = resultSet.getInt("transactionid");
                int customerId = resultSet.getInt("customerid");
                String action = resultSet.getString("action");
                String timeStamp = resultSet.getString("timestamp");
                Transactions transaction1 = new Transactions(idDatabase, customerId, action, timeStamp) ;
                transactions.add(transaction1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void update(Customer customer) {
        String sql = "update customerbank set customername = ?, joinDate = ?, accountbalance = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, String.valueOf(LocalDateTime.now()));
            preparedStatement.setFloat(3, customer.getAccountBalance());
            preparedStatement.setInt(4, customer.getId());
            int count = preparedStatement.executeUpdate();
            if (count == 1){
                System.out.println("Customer Update Successful!");
            }
            else {
                System.out.println("Something went wrong with customer update!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void approve(Customer customer) {
        String sql = "update customerbank set customername = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setInt(2, customer.getId());
            int count = preparedStatement.executeUpdate();
            if (count == 1){
                System.out.println("Customer Approval Successful!");
            }
            else {
                System.out.println("Something went wrong with approval!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from customerbank where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Customer successfully deleted!");
            }
            else {
               System.out.println("Something went wrong while deleting. Deletion unsuccessful.");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void initTables() {
        String sql = "CREATE TABLE customerbank(id serial PRIMARY KEY, customerName VARCHAR(50), joinDate integer, accountBalance float);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillTables() {
        String sql = "insert into customerbank(id, customerName, joinDate, accountBalance) values (default, 'customer 1', 2022 , 1);\n";
        sql += "insert into customerbank(id, customerName, joinDate, accountBalance) values (default, 'customer 2', 2022, 2);\n";
        sql += "insert into customerbank(id, customerName, joinDate, accountBalance) values (default, 'customer 3', 2022, 3);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
