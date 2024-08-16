package com.ofss.main.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "account_detail")
public class AccountDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "opening_date")
    private Timestamp openingDate;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "min_balance")
    private double minBalance;

    @Column(name = "current_balance")
    private double currentBalance;

    @Column(name = "overdraft_avail")
    private boolean overdraftAvail;

    @Column(name = "overdraft_amount")
    private double overdraftAmount;
    
    public static final double MAX_OVERDRAFT_AMOUNT = 10000.00;

    // Default constructor
    public AccountDetail() {}

    // Parameterized constructor
    public AccountDetail(int accountId, int customerId, Timestamp openingDate, String accountType, double minBalance, double currentBalance, boolean overdraftAvail) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.openingDate = openingDate;
        this.accountType = accountType;
        this.minBalance = minBalance;
        this.currentBalance = currentBalance;
        this.overdraftAvail = overdraftAvail;
        this.overdraftAmount = 0.0;
    }

    // Getters and Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Timestamp openingDate) {
        this.openingDate = openingDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public boolean isOverdraftAvail() {
        return overdraftAvail;
    }

    public void setOverdraftAvail(boolean overdraftAvail) {
        this.overdraftAvail = overdraftAvail;
    }

    public double getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }
    
    
    @Override
    public String toString() {
        return "AccountDetail [accountId=" + accountId + ", customerId=" + customerId + ", openingDate=" + openingDate + ", accountType=" + accountType + ", minBalance=" + minBalance + ", currentBalance=" + currentBalance + ", overdraftAvail=" + overdraftAvail + ", overdraftAmount=" + overdraftAmount + "]";
    }
}
