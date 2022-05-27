package org.example;

import org.example.dao.CustomerDao;
import org.example.dao.DaoFactory;
import org.example.entity.Customer;
import org.example.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



public class DaoTest {

// for dao test we must have a dao ( Data Access Object ) in order to access data to be able to test
    private CustomerDao customerDao;

    // we use the '@Before' annotation to set up our dao
    @BeforeEach
    public void initTables() {
        // use the dao factory to get the book dao:
        customerDao = DaoFactory.getCustomerDao();
        // this is a new method that initializes our tables:
        // since our database is being recreated every time, it always starts out empty, so we want to
        // recreate it every time
        customerDao.initTables();
    }

    @Test
    public void testInsertCustomer()
    {
        // make a new book
        Customer customer = new Customer("Dudeman", "Dudeman", 999);
        // insert it:
        customerDao.insert(customer);
        // ensure the id is 1:
        assertEquals(1, customer.getId());
    }

    @Test
    public void testGetCustomerById() {
        customerDao.fillTables();
        Customer customer = customerDao.getCustomerById(1);
        assertEquals("customer 1", customer.getCustomerName());
    }



    }


