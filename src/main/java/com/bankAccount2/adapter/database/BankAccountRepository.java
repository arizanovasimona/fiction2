package com.bankAccount2.adapter.database;

import com.bankAccount2.port.BankAccountRepositoryPort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends BankAccountRepositoryPort {

   /* @Query("SELECT id FROM BankAccount WHERE id = ?1")
    Object findBalanceById (@Param("id") Long id);*/

    Optional<BankAccount> findById (Long id);
}
