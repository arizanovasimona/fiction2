package com.bankAccount2.port;

import com.bankAccount2.adapter.database.BankAccount;
import com.bankAccount2.adapter.database.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepositoryPort extends JpaRepository<Transaction,Long>{

    List<Transaction> findAllByBankAccount(BankAccount bankAccount);

}
