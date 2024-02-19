package com.bankAccount.domain.mapper;

import com.bankAccount.adapter.API.BankAccountResponse;
import com.bankAccount.adapter.database.BankAccount;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class BankAccountMapper {

    public BankAccountResponse toBankAccountResponse (BankAccount bankAccount){
        return BankAccountResponse.builder()
                .id(bankAccount.getId())
                .balance(bankAccount.getBalance()).build();
    }
}
