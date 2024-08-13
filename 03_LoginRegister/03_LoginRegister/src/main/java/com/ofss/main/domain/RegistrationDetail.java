package com.ofss.main.domain;

public class RegistrationDetail {

	private CustomerDetail customerDetail;
	private LoginDetail loginDetail;

	public RegistrationDetail() {
	}

	public RegistrationDetail(CustomerDetail customerDetail, LoginDetail loginDetail) {
		super();
		this.customerDetail = customerDetail;
		this.loginDetail = loginDetail;
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

	@Override
	public String toString() {
		return "RegistrationDetail [customerDetail=" + customerDetail + ", loginDetail=" + loginDetail + "]";
	}

}
