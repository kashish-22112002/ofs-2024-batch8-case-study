package com.ofss.main.domain;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_detail")
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "date_time")
    private Timestamp dateTime;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "account_id")
    private int accountId;

    // Default constructor
    public TransactionDetail() {}

    // Parameterized constructor
    public TransactionDetail(int transactionId, Timestamp dateTime, String transactionType, double amount, int accountId) {
        this.transactionId = transactionId;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountId = accountId;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp timestamp) {
        this.dateTime = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    @Override
    public String toString() {
        return "TransactionDetail [transactionId=" + transactionId + ", dateTime=" + dateTime +
               ", transactionType=" + transactionType + ", amount=" + amount + ", accountId=" + accountId + "]";
    }
}
