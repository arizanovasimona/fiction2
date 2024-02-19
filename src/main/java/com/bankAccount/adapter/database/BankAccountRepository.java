package com.bankAccount.adapter.database;

import com.bankAccount.port.BankAccountRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends BankAccountRepositoryPort {

   /* @Query("SELECT id FROM BankAccount WHERE id = ?1")
    Object findBalanceById (@Param("id") Long id);*/

    Optional<BankAccount> findById (Long id);
}
