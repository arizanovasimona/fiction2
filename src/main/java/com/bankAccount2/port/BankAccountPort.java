package com.bankAccount2.port;

import com.bankAccount2.adapter.API.BankAccountResponse;
import com.bankAccount2.adapter.API.TransactionResponse;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountPort {

    void deposit(Long bankAccountId, BigDecimal amount) throws Exception;
    boolean withdraw(Long bankAccountId, BigDecimal amount) throws Exception;
    BigDecimal listBalance(Long bankAccountId);
    List<TransactionResponse> listTransactions(Long bankAccountId);
    List<BankAccountResponse> findAll();

}