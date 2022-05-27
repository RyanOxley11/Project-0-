package org.example.dao;

import org.example.entity.Customer;
import org.example.entity.Transactions;

import java.util.List;
import java.sql.SQLException;

//this interface will contain methods that we use to access 'customer' database
public interface CustomerDao {
// methods that interact with the database ( CRUD = create, read, update, delete )
    public void insert(Customer customer);
    public Customer getCustomerById(int id);
    public Customer getCustomerByName(String customerName);
    public List<Customer> getAllCustomers();
    public List<Transactions> getAllTransactions();
    public void update(Customer customer);
    public void approve(Customer customer);
    public void delete(int id);
    public void initTables();
    public void fillTables();


}
