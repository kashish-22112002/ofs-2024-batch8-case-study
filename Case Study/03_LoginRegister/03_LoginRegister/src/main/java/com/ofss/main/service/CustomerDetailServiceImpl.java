package com.ofss.main.service;

import com.ofss.main.domain.AccountDetail;
import com.ofss.main.domain.CustomerDetail;
import com.ofss.main.domain.LoginDetail;
import com.ofss.main.domain.RegistrationDetail;
import com.ofss.main.repository.AccountDetailRepository;
import com.ofss.main.repository.CustomerDetailRepository;
import com.ofss.main.repository.LoginDetailRepository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

	@Autowired
	private CustomerDetailRepository customerDetailRepository;

	@Autowired
	private LoginDetailRepository loginDetailRepository;

	 @Autowired
	 private AccountDetailRepository accountDetailRepository;

	@Override
	public CustomerDetail saveCustomerDetail(CustomerDetail customerDetail) {
		return customerDetailRepository.save(customerDetail);
	}

	@Override
	public CustomerDetail getCustomerDetailById(int customerId) {
		return customerDetailRepository.findById(customerId).orElse(null);
	}

	@Override
	public CustomerDetail getCustomerDetailByEmail(String email) {
		// Implement this if you have a method in repository
		return customerDetailRepository.findByEmail(email);
	}

	@Override
    public RegistrationDetail registerCustomer(RegistrationDetail registrationDetail) {
        CustomerDetail customerDetail = registrationDetail.getCustomerDetail();
        LoginDetail loginDetail = registrationDetail.getLoginDetail();
        AccountDetail accountDetail = registrationDetail.getAccountDetail(); // Get account detail

        // Save Customer Detail
        customerDetailRepository.save(customerDetail);

        // Save Login Detail
        loginDetail.setCustomerDetail(customerDetail);
        loginDetailRepository.save(loginDetail);

        // Save Account Detail
        if (accountDetail != null) {
            createAccount(customerDetail.getCustomerId(), accountDetail.getAccountType());
        }

        return new RegistrationDetail(customerDetail, loginDetail, accountDetail);
    }
	
	@Override
    public void lockAccount(CustomerDetail customerDetail) {
        if (customerDetail != null) {
            customerDetail.setLockedStatus(true); // Use the correct method to lock the account
            customerDetailRepository.save(customerDetail);
        }
    }
	
	@Override
    public void createAccount(int customerId, String accountType) {
        double minBalance = 0;
        boolean overdraftAvail = false;

        if (accountType.equalsIgnoreCase("Current")) {
            minBalance = 0;
            overdraftAvail = true;
        } else if (accountType.equalsIgnoreCase("Savings")) {
            minBalance = 5000;
            overdraftAvail = false;
        }

        AccountDetail newAccount = new AccountDetail(0, customerId, new Timestamp(System.currentTimeMillis()), accountType, minBalance, 0, overdraftAvail);
        accountDetailRepository.save(newAccount);
    }
}