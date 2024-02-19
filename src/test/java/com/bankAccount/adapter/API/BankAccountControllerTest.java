package com.bankAccount.adapter.API;

import com.bankAccount.domain.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BankAccountControllerTest {

    @Mock
    private BankAccountService bankAccountPort;

    @InjectMocks
    private BankAccountController bankAccountController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {

        var bankAccountResponse1 = new BankAccountResponse();
        var bankAccountResponse2 = new BankAccountResponse();
        List<BankAccountResponse> bankAccountResponses = Arrays.asList(bankAccountResponse1, bankAccountResponse2);

        when(bankAccountPort.findAll()).thenReturn(bankAccountResponses);

        List<BankAccountResponse> result = bankAccountController.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testListTransactions() {

        Long bankAccountId = 1L;
        var transactionResponse1 = new TransactionResponse();
        var transactionResponse2 = new TransactionResponse();
        List<TransactionResponse> transactionResponses = Arrays.asList(transactionResponse1, transactionResponse2);

        when(bankAccountPort.listTransactions(bankAccountId)).thenReturn(transactionResponses);

        List<TransactionResponse> result = bankAccountController.listTransactions(bankAccountId);

        assertEquals(2, result.size());
    }

    @Test
    public void testListBalance() {

        Long bankAccountId = 1L;
        BigDecimal balance = new BigDecimal("500");

        when(bankAccountPort.listBalance(bankAccountId)).thenReturn(balance);

        BigDecimal result = bankAccountController.listBalance(bankAccountId);

        assertEquals(balance, result);
    }

    @Test
    public void testDepositDecentAmountOfMoney() throws Exception {

        Long bankAccountId = 1L;
        BigDecimal amount = new BigDecimal("100000000.85");

        ResponseEntity result = bankAccountController.deposit(bankAccountId, amount);

        verify(bankAccountPort, times(1)).deposit(bankAccountId, amount);

        assertEquals(ResponseEntity.noContent().build(), result); //Person must be quite happy, so bad it's a mock
    }

    @Test
    public void testWithdraw() throws Exception {
        Long bankAccountId = 1L;
        BigDecimal amount = new BigDecimal("50");

        ResponseEntity result = bankAccountController.withdraw(bankAccountId, amount);

        verify(bankAccountPort, times(1)).withdraw(bankAccountId, amount);

        assertEquals(ResponseEntity.noContent().build(), result);
    }
}