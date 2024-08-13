package com.ofss.main.domain;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_detail")
public class CustomerDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
    private int customerId;
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="date_of_birth")
    private Date dateOfBirth;
	
	@Column(name="address")
    private String address;
	
	@Column(name="email")
    private String email;
	
	@Column(name="phone_number")
    private long phoneNumber;
	
	@Column(name="locked_status")
    private boolean lockedStatus;
	
	@Column(name="Approval_status")
    private boolean approvalStatus;
	
//    private List<AccountDetail> accounts; // List of accounts for the customer
	
	@OneToOne(mappedBy = "customerDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private LoginDetail loginDetail;
	
    // Default constructor
    public CustomerDetail() {}

    // Parameterized constructor
    public CustomerDetail(int customerId, String firstName, String lastName, Date dateOfBirth, String address, String email, long phoneNumber, boolean lockedStatus, boolean approvalStatus) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lockedStatus = lockedStatus;
        this.approvalStatus = approvalStatus;
//        this.accounts = accounts;
    }
    

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    public boolean isLockedStatus() {
        return lockedStatus;
    }

    public void setLockedStatus(boolean lockedStatus) {
        this.lockedStatus = lockedStatus;
    }

    
    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    
//    public List<AccountDetail> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(List<AccountDetail> accounts) {
//        this.accounts = accounts;
//    }

    @Override
    public String toString() {
        return "CustomerDetail{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", lockedStatus=" + lockedStatus +
                ", approvalStatus=" + approvalStatus+
                '}';
    }
}