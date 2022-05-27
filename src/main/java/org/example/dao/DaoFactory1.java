package org.example.dao;

public class DaoFactory1 {
    private static ApplicationDao applicationDao;

    private DaoFactory1() {

    }

    public static ApplicationDao getApplicationDao() {
        if (applicationDao == null) {
            applicationDao = new ApplicationDaoImpl();
        }
        return applicationDao;
    }
}
