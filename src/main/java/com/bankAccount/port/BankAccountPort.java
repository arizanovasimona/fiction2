package com.bankAccount.port;

import com.bankAccount.adapter.API.BankAccountResponse;
import com.bankAccount.adapter.API.TransactionResponse;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountPort {

    void deposit(Long bankAccountId, BigDecimal amount) throws Exception;
    boolean withdraw(Long bankAccountId, BigDecimal amount) throws Exception;
    BigDecimal listBalance(Long bankAccountId);
    List<TransactionResponse> listTransactions(Long bankAccountId);
    List<BankAccountResponse> findAll();

}