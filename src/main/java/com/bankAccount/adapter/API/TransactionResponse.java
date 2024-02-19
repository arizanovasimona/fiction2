package com.bankAccount.adapter.API;

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
class TransactionResponse {
    private Long id;
    private BigDecimal amount;
}
