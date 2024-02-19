package com.bankAccount.domain;

import com.bankAccount.adapter.API.BankAccountResponse;
import com.bankAccount.adapter.API.TransactionResponse;
import com.bankAccount.adapter.database.BankAccount;
import com.bankAccount.adapter.database.Transaction;
import com.bankAccount.domain.mapper.BankAccountMapper;
import com.bankAccount.domain.mapper.TransactionMapper;
import com.bankAccount.domain.service.BankAccountService;
import com.bankAccount.port.BankAccountRepositoryPort;
import com.bankAccount.port.TransactionRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankAccountServiceTest {

    @Mock
    private BankAccountRepositoryPort bankAccountRepositoryPort;

    @Mock
    private TransactionRepositoryPort transactionRepositoryPort;

    @Mock
    private BankAccountMapper bankAccountMapper;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private BankAccountService bankAccountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(bankAccountService, "bankAccountRepositoryPort", bankAccountRepositoryPort);
        ReflectionTestUtils.setField(bankAccountService, "transactionRepositoryPort", transactionRepositoryPort);
        ReflectionTestUtils.setField(bankAccountService, "bankAccountMapper", bankAccountMapper);
        ReflectionTestUtils.setField(bankAccountService, "transactionMapper", transactionMapper);
    }

    @Test
    public void testDeposit() throws Exception {
        // Mock data
        Long bankAccountId = 1L;
        BigDecimal amount = new BigDecimal("100");
        var bankAccount = new BankAccount();
        bankAccount.setBalance(new BigDecimal("500"));

        // Mock behavior
        when(bankAccountRepositoryPort.findById(bankAccountId)).thenReturn(java.util.Optional.of(bankAccount));

        // Perform the deposit
        bankAccountService.deposit(bankAccountId, amount);

        // Verify that the balance is updated
        assertEquals(new BigDecimal("600"), bankAccount.getBalance());

        // Verify that the save methods are called
        verify(bankAccountRepositoryPort, times(1)).save(bankAccount);
        verify(transactionRepositoryPort, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testWithdrawEnoughBalance() throws Exception {
        Long bankAccountId = 1L;
        BigDecimal amount = new BigDecimal("50");
        var bankAccount = new BankAccount();
        bankAccount.setBalance(new BigDecimal("100"));

        when(bankAccountRepositoryPort.findById(bankAccountId)).thenReturn(java.util.Optional.of(bankAccount));

        boolean result = bankAccountService.withdraw(bankAccountId, amount);

        assertEquals(new BigDecimal("50"), bankAccount.getBalance());

        verify(bankAccountRepositoryPort, times(1)).save(bankAccount);

        assertTrue(result);
    }

    @Test
    public void testWithdrawNotEnoughBalance() throws Exception {

        Long bankAccountId = 1L;
        BigDecimal amount = new BigDecimal("200");
        var bankAccount = new BankAccount(new BigDecimal("100"));

        when(bankAccountRepositoryPort.findById(bankAccountId)).thenReturn(java.util.Optional.of(bankAccount));

        boolean result = bankAccountService.withdraw(bankAccountId, amount);

        assertEquals(new BigDecimal("100"), bankAccount.getBalance());

        verify(bankAccountRepositoryPort, never()).save(any(BankAccount.class));

        assertFalse(result);
    }

    @Test
    public void testListBalance() {
        Long bankAccountId = 1L;
        var bankAccount = new BankAccount(new BigDecimal("500"));

        when(bankAccountRepositoryPort.findById(bankAccountId)).thenReturn(java.util.Optional.of(bankAccount));

        BigDecimal balance = bankAccountService.listBalance(bankAccountId);

        assertEquals(new BigDecimal("500"), balance);
    }

    @Test
    public void testListTransactions() {

        Long bankAccountId = 1L;
        var bankAccount = new BankAccount();
        var transaction1 = new Transaction();
        var transaction2 = new Transaction();

        List<Transaction> transactions =  new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(bankAccountRepositoryPort.findById(bankAccountId)).thenReturn(java.util.Optional.of(bankAccount));
        when(transactionRepositoryPort.findAllByBankAccount(bankAccount)).thenReturn(transactions);
        when(transactionMapper.toTransactionResponse(transaction1)).thenReturn(new TransactionResponse());
        when(transactionMapper.toTransactionResponse(transaction2)).thenReturn(new TransactionResponse());

        List<TransactionResponse> transactionResponses = bankAccountService.listTransactions(bankAccountId);

        assertEquals(2, transactionResponses.size());
    }

    @Test
    public void testFindAll() {
        var bankAccount1 = new BankAccount();
        var bankAccount2 = new BankAccount();
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);

        when(bankAccountRepositoryPort.findAll()).thenReturn(bankAccounts);
        when(bankAccountMapper.toBankAccountResponse(bankAccount1)).thenReturn(new BankAccountResponse());
        when(bankAccountMapper.toBankAccountResponse(bankAccount2)).thenReturn(new BankAccountResponse());

        List<BankAccountResponse> bankAccountResponses = bankAccountService.findAll();

        assertEquals(2, bankAccountResponses.size());
    }
}
