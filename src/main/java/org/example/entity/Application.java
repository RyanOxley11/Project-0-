package org.example.entity;

public class Application {
    private int id;
    private String customerName;
    private String yearJoined;
    private float accountBalance;

    public Application(int id, String customerName, String yearJoined, float accountBalance) {
        this.id = id;
        this.customerName = customerName;
        this.yearJoined = yearJoined;
        this.accountBalance = accountBalance;
    }

    public Application(String customerName, String yearJoined, float accountBalance) {
        this.customerName = customerName;
        this.yearJoined = yearJoined;
        this.accountBalance = accountBalance;
    }

    public Application(int id, String customerName, String yearJoined) {
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

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", yearJoined=" + yearJoined +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
