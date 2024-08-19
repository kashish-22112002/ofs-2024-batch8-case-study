package com.ofss.main.service;

import com.ofss.main.domain.AccountDetail;
import com.ofss.main.domain.TransactionDetail;
import com.ofss.main.repository.AccountDetailRepository;
import com.ofss.main.repository.TransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Override
    public TransactionDetail createTransaction(int fromAccountId, int toAccountId, double amount) {
        // Retrieve accounts
        AccountDetail fromAccount = accountDetailRepository.findById(fromAccountId).orElse(null);
        AccountDetail toAccount = accountDetailRepository.findById(toAccountId).orElse(null);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account details");
        }

        // Perform the transaction
        double fromAccountBalance = fromAccount.getCurrentBalance();
        double toAccountBalance = toAccount.getCurrentBalance();
        double maxOverdraft = AccountDetail.MAX_OVERDRAFT_AMOUNT;

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Check for overdraft facility
        if (fromAccount.getAccountType().equalsIgnoreCase("Current")) {
            double overdraftLimit = maxOverdraft - fromAccount.getOverdraftAmount();
            if ((fromAccountBalance + overdraftLimit) < amount) {
                throw new IllegalArgumentException("Insufficient funds including overdraft limit");
            }
        } else if (fromAccountBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance in the from account");
        }

        // Update balances and overdraft
        if (fromAccount.getAccountType().equalsIgnoreCase("Current")) {
            double overdraftNeeded = amount - fromAccountBalance;
            if (overdraftNeeded > 0) {
                double newOverdraftAmount = fromAccount.getOverdraftAmount() + overdraftNeeded;
                if (newOverdraftAmount > maxOverdraft) {
                    throw new IllegalArgumentException("Overdraft limit exceeded");
                }
                fromAccount.setOverdraftAmount(newOverdraftAmount);
                fromAccount.setCurrentBalance(0); // All balance is used, remaining amount is covered by overdraft
            } else {
                fromAccount.setCurrentBalance(fromAccountBalance - amount);
            }
        } else {
            fromAccount.setCurrentBalance(fromAccountBalance - amount);
        }

        toAccount.setCurrentBalance(toAccountBalance + amount);

        // Save the updated accounts
        accountDetailRepository.save(fromAccount);
        accountDetailRepository.save(toAccount);

        // Record the transaction for the sender (Transfer From)
        TransactionDetail transactionFrom = new TransactionDetail();
        transactionFrom.setDateTime(new Timestamp(System.currentTimeMillis()));
        transactionFrom.setTransactionType("Transfer From");
        transactionFrom.setAmount(amount);
        transactionFrom.setAccountId(fromAccountId); // Sender's account ID

        // Record the transaction for the receiver (Transfer To)
        TransactionDetail transactionTo = new TransactionDetail();
        transactionTo.setDateTime(new Timestamp(System.currentTimeMillis()));
        transactionTo.setTransactionType("Transfer To");
        transactionTo.setAmount(amount);
        transactionTo.setAccountId(toAccountId); // Receiver's account ID

        // Save both transactions
        transactionDetailRepository.save(transactionFrom);
        transactionDetailRepository.save(transactionTo);

        // Return the first transaction for confirmation or further processing
        return transactionFrom;
    }
}
