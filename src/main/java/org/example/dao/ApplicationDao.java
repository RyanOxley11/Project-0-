package org.example.dao;

import org.example.entity.Customer;

public interface ApplicationDao {
    public void insert(Customer customer);
    public void accept(Customer customer);

    public Customer getCustomerById(int id);
    public void update(Customer customer);

    public void transfer(Customer customer);
    public void transferAccept(Customer customer);


}
