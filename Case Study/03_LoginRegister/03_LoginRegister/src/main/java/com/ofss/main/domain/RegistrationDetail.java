package com.ofss.main.domain;

public class RegistrationDetail {

    private CustomerDetail customerDetail;
    private LoginDetail loginDetail;
    private AccountDetail accountDetail; // Added this field

    public RegistrationDetail() {
    }

    public RegistrationDetail(CustomerDetail customerDetail, LoginDetail loginDetail, AccountDetail accountDetail) {
        this.customerDetail = customerDetail;
        this.loginDetail = loginDetail;
        this.accountDetail = accountDetail;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public LoginDetail getLoginDetail() {
        return loginDetail;
    }

    public void setLoginDetail(LoginDetail loginDetail) {
        this.loginDetail = loginDetail;
    }

    public AccountDetail getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(AccountDetail accountDetail) {
        this.accountDetail = accountDetail;
    }

    @Override
    public String toString() {
        return "RegistrationDetail [customerDetail=" + customerDetail + ", loginDetail=" + loginDetail + ", accountDetail=" + accountDetail + "]";
    }
}
