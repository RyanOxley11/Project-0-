package org.example.entity;

public class Transactions {

    private int transactionId;
    private int customerId;
    private String action;
    private String timestamp;

    public Transactions(int transactionId, int customerId, String action, String timestamp) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.action = action;
        this.timestamp = timestamp;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transactiona{" +
                "transactionid=" + transactionId +
                ", customerid='" + customerId + '\'' +
                ", action=" + action +
                ", timestamp=" + timestamp +
                '}';
    }

    public void add(Transactions transactions) {
    }
}
