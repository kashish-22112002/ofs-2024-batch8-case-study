package com.ofss.main.repository;

import com.ofss.main.domain.AccountDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailRepository extends CrudRepository<AccountDetail, Integer> {
 
}
