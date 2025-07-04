package org.npci.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.npci.exception.AccountBalanceException;
import org.npci.exception.AccountNotFoundException;
import org.npci.model.Account;
import org.npci.repository.AccountRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpiTransferServiceUnitTest {


    UpiTransferService upiTransferService;
    // Mock dependencies if needed, e.g., AccountRepository
    AccountRepository accountRepositoryMock;

    @BeforeEach
    public void setUp() {
        accountRepositoryMock = mock(AccountRepository.class);
        upiTransferService = new UpiTransferService(accountRepositoryMock);
    }

    // Invalid account number test
    @Test
    public void testInitiateTransfer_InvalidAccountNumber() {
        String fromAccountNumber = "invalidFromAccount";
        String toAccountNumber = "0987654321";
        double amount = 100.0;
        when(accountRepositoryMock.findByAccountNumber(fromAccountNumber)).thenReturn(Optional.empty());
        Account toAccount = new Account(toAccountNumber, "Jane", 200.0);

        when(accountRepositoryMock.findByAccountNumber(toAccountNumber)).thenReturn(Optional.empty());
        when(accountRepositoryMock.findByAccountNumber(toAccountNumber)).thenReturn(Optional.of(toAccount));

        AccountNotFoundException e = assertThrows(AccountNotFoundException.class, () -> {
            upiTransferService.initiateTransfer(fromAccountNumber, toAccountNumber, amount);
        });
        assertEquals("From account not found - " + fromAccountNumber, e.getMessage());

    }

    // Insufficient balance test
    @Test
    public void testInitiateTransfer_InsufficientBalance() {
        String fromAccountNumber = "fromAccount";
        String toAccountNumber = "toAccount";
        double amount = 100.0;

        Account fromAccount = new Account(fromAccountNumber, "John", 50.0); // Balance is less than amount
        Account toAccount = new Account(toAccountNumber, "Jane", 200.0);

        when(accountRepositoryMock.findByAccountNumber(fromAccountNumber)).thenReturn(Optional.of(fromAccount));
        when(accountRepositoryMock.findByAccountNumber(toAccountNumber)).thenReturn(Optional.of(toAccount));

        AccountBalanceException e = assertThrows(AccountBalanceException.class, () -> {
            upiTransferService.initiateTransfer(fromAccountNumber, toAccountNumber, amount);
        });

        assertEquals("Insufficient balance in from account - " + fromAccountNumber, e.getMessage());
    }

    // Successful transfer test
    @Test
    public void testInitiateTransfer_SuccessfulTransfer() {
        String fromAccountNumber = "1234567890";
        String toAccountNumber = "0987654321";
        double amount = 100.0;

        Account fromAccount = new Account(fromAccountNumber, "John", 200.0); // Sufficient balance
        Account toAccount = new Account(toAccountNumber, "Jane", 300.0);

        when(accountRepositoryMock.findByAccountNumber(fromAccountNumber)).thenReturn(Optional.of(fromAccount));
        when(accountRepositoryMock.findByAccountNumber(toAccountNumber)).thenReturn(Optional.of(toAccount));

        upiTransferService.initiateTransfer(fromAccountNumber, toAccountNumber, amount);

        assertEquals(100.0, fromAccount.getBalance());
        assertEquals(400.0, toAccount.getBalance());

        verify(accountRepositoryMock, times(1)).update(fromAccount);
        verify(accountRepositoryMock, times(1)).update(toAccount);

    }


}
