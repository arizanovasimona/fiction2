package com.bankAccount.domain.mapper;

import com.bankAccount.adapter.API.TransactionResponse;
import com.bankAccount.adapter.database.Transaction;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class TransactionMapper {

    public TransactionResponse toTransactionResponse (Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount()).build();
    }
}
