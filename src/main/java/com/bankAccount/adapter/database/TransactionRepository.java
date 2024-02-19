package com.bankAccount.adapter.database;

import com.bankAccount.port.TransactionRepositoryPort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends TransactionRepositoryPort {

    @Query("SELECT t FROM Transaction t WHERE t.bankAccount = ?1")
    List<Transaction> findAllByBankAccount(@Param("bankAccount") BankAccount bankAccount);

}

