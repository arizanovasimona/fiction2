package com.bankAccount2.port;

import com.bankAccount2.adapter.database.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BankAccountRepositoryPort extends JpaRepository<BankAccount,Long> {

}
