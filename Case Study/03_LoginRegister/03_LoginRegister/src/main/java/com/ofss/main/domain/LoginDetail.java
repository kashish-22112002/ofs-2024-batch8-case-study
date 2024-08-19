package com.ofss.main.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_detail")
public class LoginDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_id")
	private int loginId;

	@OneToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private CustomerDetail customerDetail;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "attempts")
	private int attempts;

	public LoginDetail() {
	}

	public LoginDetail(int loginId, CustomerDetail customerDetail, String username, String password, int attempts) {
		super();
		this.loginId = loginId;
		this.customerDetail = customerDetail;
		this.username = username;
		this.password = password;
		this.attempts = attempts;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	@Override
	public String toString() {
		return "LoginDetail [loginId=" + loginId + ", customerDetail=" + customerDetail + ", username=" + username
				+ ", password=" + password + ", attempts=" + attempts + "]";
	}

}