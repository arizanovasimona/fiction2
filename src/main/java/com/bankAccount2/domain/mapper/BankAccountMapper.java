package com.bankAccount2.domain.mapper;

import com.bankAccount2.adapter.API.BankAccountResponse;
import com.bankAccount2.adapter.database.BankAccount;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class BankAccountMapper {

    public BankAccountResponse toBankAccountResponse (BankAccount bankAccount){
        return BankAccountResponse.builder()
                .id(bankAccount.getBankaccount_id())
                .balance(bankAccount.getBalance()).build();
    }
}
