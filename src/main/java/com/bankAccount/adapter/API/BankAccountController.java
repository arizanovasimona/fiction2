package com.bankAccount.adapter.API;

import com.bankAccount.domain.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountPort;

    /**
     * @return List of transactions for specific account
     */

    @GetMapping("/find")
    List<BankAccountResponse> findAll(){
        return bankAccountPort.findAll();
    }

    @GetMapping("/transactions/{bankAccountId}")
    List<TransactionResponse> listTransactions(@PathVariable("bankAccountId") Long id) {
        return bankAccountPort.listTransactions(id);
    }

    @GetMapping("/balance/{bankAccountId}")
    BigDecimal listBalance(@PathVariable("bankAccountId") Long id) {
        return bankAccountPort.listBalance(id);
    }

    @PostMapping("/deposit/{bankAccountId}/{amount}")
    public ResponseEntity deposit(@PathVariable("bankAccountId") Long bankAccountId, @PathVariable("amount") BigDecimal amount) throws Exception {
        bankAccountPort.deposit(bankAccountId,amount);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/withdraw/{bankAccountId}/{amount}")
    public ResponseEntity withdraw(@PathVariable("bankAccountId") Long bankAccountId, @PathVariable("amount") BigDecimal amount) throws Exception {
        bankAccountPort.withdraw(bankAccountId,amount);
        return ResponseEntity.noContent().build();
    }

}