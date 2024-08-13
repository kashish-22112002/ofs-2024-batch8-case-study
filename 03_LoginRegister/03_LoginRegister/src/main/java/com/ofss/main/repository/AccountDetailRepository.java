package com.ofss.main.repository;

import com.ofss.main.domain.AccountDetail;
import org.springframework.data.repository.CrudRepository;

public interface AccountDetailRepository extends CrudRepository<AccountDetail, Integer> {
    // Define any additional methods if needed
}
