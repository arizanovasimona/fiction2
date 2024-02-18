package com.bankAccount2.adapter.database;

import com.bankAccount2.port.BankAccountRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>, BankAccountRepositoryPort {

    @Query("SELECT balance FROM BankAccount WHERE bankaccount_id = ?1")
    BigDecimal findBalance(@Param("id") Long id);

    Optional<BankAccount> findById(Long id);

    @Override
    <S extends BankAccount> S save(BankAccount entity);
}
