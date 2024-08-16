package com.ofss.main.controller;

import com.ofss.main.domain.TransactionDetail;
import com.ofss.main.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionDetailService transactionDetailService;

    @PostMapping("/transfer")
    @CrossOrigin
    public ResponseEntity<TransactionDetail> transferMoney(@RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionDetail transaction = transactionDetailService.createTransaction(
                    transactionRequest.getFromAccountId(),
                    transactionRequest.getToAccountId(),
                    transactionRequest.getAmount()
            );
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Or return an appropriate error message
        }
    }
}
