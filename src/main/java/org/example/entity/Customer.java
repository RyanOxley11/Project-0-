package org.example.entity;

// Customer is an "entity" that represents a physical object
public class Customer {
    private int id;
    private String customerName;
    private String yearJoined;
    private float accountBalance;

    private String customerApproval;

    public Customer(int id, String customerName, String yearJoined, float accountBalance) {
        this.id = id;
        this.customerName = customerName;
        this.yearJoined = yearJoined;
        this.accountBalance = accountBalance;
    }

// need a constructor that doesn't take an id because when book created we don't know what id is
    public Customer(String customerName, String yearJoined, float accountBalance) {
        this.customerName = customerName;
        this.yearJoined = yearJoined;
        this.accountBalance = accountBalance;
    }

    public Customer(int id, float accountBalance) {
        this.id = id;
        this.accountBalance = accountBalance;
    }

    public Customer(int id, String customerName, String yearJoined) {
        this.id = id;
        this.customerName = customerName;
        this.yearJoined = yearJoined;
    }

    public Customer(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;

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

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", yearJoined=" + yearJoined +
                ", accountBalance=" + accountBalance +
                '}';
    }

    public void add(Customer customer) {
    }
}
