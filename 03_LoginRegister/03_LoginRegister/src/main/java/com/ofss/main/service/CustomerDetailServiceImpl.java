package com.ofss.main.service;

import com.ofss.main.domain.CustomerDetail;
import com.ofss.main.domain.LoginDetail;
import com.ofss.main.domain.RegistrationDetail;
import com.ofss.main.repository.CustomerDetailRepository;
import com.ofss.main.repository.LoginDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

	@Autowired
	private CustomerDetailRepository customerDetailRepository;

	@Autowired
	private LoginDetailRepository loginDetailRepository;

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

		// Save Customer Detail
		customerDetailRepository.save(customerDetail);

		// Save Login Detail
		loginDetail.setCustomerDetail(customerDetail);
		loginDetailRepository.save(loginDetail);

		return new RegistrationDetail(customerDetail, loginDetail);
	}
}
