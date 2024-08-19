package com.ofss.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.ofss.main.domain.LoginDetail;

public interface LoginDetailRepository extends CrudRepository<LoginDetail, Integer> {

	LoginDetail findByUsername(String username);
//    LoginDetail save(LoginDetail loginDetail);
//    
//    
//    LoginDetail findByUsername(String username);
//    void updateAttempts(String username, int attempts);
//    LoginDetail findByCustomerID(int customer_id);
}
