package com.bankAccount2.adapter.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TRANSACTION_DATA")
public class Transaction implements Serializable {

    @Id
    @Column (name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "bankaccount_id", nullable=false)
    private BankAccount bankAccount;

}
