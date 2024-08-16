package com.ofss.main.service;

import com.ofss.main.domain.TransactionDetail;

public interface TransactionDetailService {
    TransactionDetail createTransaction(int fromAccountId, int toAccountId, double amount);
}
