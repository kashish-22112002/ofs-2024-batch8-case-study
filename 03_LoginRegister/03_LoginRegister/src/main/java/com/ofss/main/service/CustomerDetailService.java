package com.ofss.main.service;

import com.ofss.main.domain.CustomerDetail;
import com.ofss.main.domain.RegistrationDetail;

public interface CustomerDetailService {
    CustomerDetail saveCustomerDetail(CustomerDetail customerDetail);
    CustomerDetail getCustomerDetailById(int customerId);
    CustomerDetail getCustomerDetailByEmail(String email);
    RegistrationDetail registerCustomer(RegistrationDetail registrationDetail);
}
