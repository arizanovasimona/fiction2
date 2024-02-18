package com.bankAccount2.port;

import com.bankAccount2.adapter.database.BankAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepositoryPort {

    BigDecimal findBalance(Long id);
    Optional<BankAccount> findById(Long id);
    <S extends BankAccount> S save(BankAccount bankAccount);
    List<BankAccount> findAll();
}
