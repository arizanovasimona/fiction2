package com.bankAccount2.domain.service;

import com.bankAccount2.adapter.API.BankAccountResponse;
import com.bankAccount2.adapter.API.TransactionResponse;
import com.bankAccount2.adapter.database.BankAccountRepository;
import com.bankAccount2.adapter.database.Transaction;
import com.bankAccount2.adapter.database.TransactionRepository;
import com.bankAccount2.domain.mapper.BankAccountMapper;
import com.bankAccount2.domain.mapper.TransactionMapper;
import com.bankAccount2.port.BankAccountPort;
import com.bankAccount2.port.BankAccountRepositoryPort;
import com.bankAccount2.port.TransactionRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService implements BankAccountPort {

    @Autowired
    private BankAccountRepositoryPort bankAccountRepositoryPort;

    @Autowired
    private TransactionRepositoryPort transactionRepositoryPort;

    private final BankAccountMapper bankAccountMapper;

    private final TransactionMapper transactionMapper;

    public BankAccountService(BankAccountMapper bankAccountMapper, TransactionMapper transactionMapper) {
        this.bankAccountMapper = bankAccountMapper;
        this.transactionMapper = transactionMapper;
    }

    public void deposit(Long bankAccountId, BigDecimal amount) throws Exception {
        var bankAccount = bankAccountRepositoryPort.findById(bankAccountId)
                .orElseThrow(() -> new Exception());
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        var transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setBankAccount(bankAccount);
        bankAccountRepositoryPort.save(bankAccount);
        transactionRepositoryPort.save(transaction);
    }

    public boolean withdraw(Long bankAccountId, BigDecimal amount) throws Exception {
        if(bankAccountRepositoryPort.findById(bankAccountId).orElseThrow().getBalance().compareTo(amount) >0) {
            var bankAccount = bankAccountRepositoryPort.findById(bankAccountId)
                    .orElseThrow(() -> new Exception());
            bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
            bankAccountRepositoryPort.save(bankAccount);
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal listBalance(Long bankAccountId) {
        return bankAccountRepositoryPort.findById(bankAccountId).orElseThrow().getBalance();
    }

    public List<TransactionResponse> listTransactions(Long bankAccountId) {
        var bankAccount = bankAccountRepositoryPort.findById(bankAccountId).orElseThrow();
        return transactionRepositoryPort.findAllByBankAccount(bankAccount).stream().map(transactionMapper::toTransactionResponse).collect(Collectors.toList());
    }

    public List<BankAccountResponse> findAll() {
        return bankAccountRepositoryPort.findAll().stream()
                .map(bankAccountMapper::toBankAccountResponse).collect(Collectors.toList());
    }
}