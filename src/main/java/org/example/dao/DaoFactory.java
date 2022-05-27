package org.example.dao;

public class DaoFactory {
    private static CustomerDao customerDao;

    // private constructor intentionally disallow the instantiation of this class
    private DaoFactory() {

    }

    //check if null, return the dao
    public static CustomerDao getCustomerDao() {
        if(customerDao == null) {
           customerDao = new CustomerDaoImpl();
        }
    return customerDao;
    }


    }

