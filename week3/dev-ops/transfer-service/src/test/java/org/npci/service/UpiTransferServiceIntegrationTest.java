package org.npci.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.npci.model.Account;
import org.npci.repository.AccountRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpiTransferServiceTest {

    AccountRepository accountRepositoryMock;
    UpiTransferService upiTransferService;
    Account fromAccount;
    Account toAccount;

    @BeforeEach
    public void setUp() {

        accountRepositoryMock = mock(AccountRepository.class);
        upiTransferService = new UpiTransferService(accountRepositoryMock);

        // Mock the behavior of the accountRepository to return dummy accounts
        fromAccount = new Account("1234567890", "John Doe", 500.0);
        toAccount = new Account("0987654321", "Jane Doe", 300.0);
        when(accountRepositoryMock.findById("1234567890")).thenReturn(Optional.of(fromAccount));
        when(accountRepositoryMock.findById("0987654321")).thenReturn(Optional.of(toAccount));
    }


    @Test
    public void testInitiateTransfer() {
        upiTransferService
                .initiateTransfer("1234567890", "0987654321", 100.0);
        assertEquals(400.0, fromAccount.getBalance(), 0.01);
        assertEquals(400.0, toAccount.getBalance(), 0.01);

        verify(accountRepositoryMock, times(1)).save(fromAccount);
        verify(accountRepositoryMock, times(1)).save(toAccount);
    }


    @Test
    public void testInitiateTransferInsufficientBalance() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            upiTransferService.initiateTransfer("1234567890", "0987654321", 600.0);
        });
        assertEquals("Insufficient balance in from account - 1234567890", exception.getMessage());
    }

    @Test
    public void testInitiateTransferAccountNotFound() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            upiTransferService.initiateTransfer("12121212", "0987654321", 100.0);
        });
        assertEquals("From account not found - 12121212", exception.getMessage());
    }

}
