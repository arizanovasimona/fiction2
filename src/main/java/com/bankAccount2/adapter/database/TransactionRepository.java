package com.bankAccount2.adapter.database;

import com.bankAccount2.port.TransactionRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>, TransactionRepositoryPort {

    @Query("SELECT t FROM Transaction t WHERE t.bankAccount = ?1")
    List<Transaction> findAllByBalanceAmountId(@Param("bankAccount") BankAccount bankAccount);

}

