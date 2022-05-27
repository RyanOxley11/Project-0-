package org.example.entity;

public class User {
    private int id;
    private String customerName;
    private String yearJoined;


    public User(int id, String customerName, String yearJoined) {
        this.id = id;
        this.customerName = customerName;
        this.yearJoined = yearJoined;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getYearJoined() {
        return yearJoined;
    }

    public void setYearJoined(String yearJoined) {
        this.yearJoined = yearJoined;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", yearJoined=" + yearJoined + '}';
    }

}
