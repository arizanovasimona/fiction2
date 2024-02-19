package com.bankAccount.port;

import com.bankAccount.adapter.database.BankAccount;
import com.bankAccount.adapter.database.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepositoryPort extends JpaRepository<Transaction,Long>{

    List<Transaction> findAllByBankAccount(BankAccount bankAccount);

}
