package com.bankAccount2.adapter.API;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public
class BankAccountResponse {

    private Long id;
    private BigDecimal balance;

}
