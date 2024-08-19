package com.ofss.main.repository;

import com.ofss.main.domain.TransactionDetail;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDetailRepository extends CrudRepository<TransactionDetail, Integer> {
    // Custom query methods can be defined here if needed
}
