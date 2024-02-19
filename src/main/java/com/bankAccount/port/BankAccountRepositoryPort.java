package com.bankAccount.port;

import com.bankAccount.adapter.database.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepositoryPort extends JpaRepository<BankAccount,Long> {

}
