package com.ofss.main.repository;

import com.ofss.main.domain.CustomerDetail;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerDetailRepository extends CrudRepository<CustomerDetail, Integer> {

	CustomerDetail findByEmail(String email);
//    void save(CustomerDetail customerDetail);
//    CustomerDetail findById(int customerId);
//    List<CustomerDetail> findAll();
//    void update(CustomerDetail customerDetail);
//    void delete(int customerId);

	
//	  CustomerDetail findByEmail(String email);
}
