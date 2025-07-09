package org.npci.service;

public interface TransferService {
    void initiateTransfer(String fromAccountNumber, String toAccountNumber, double amount);
}
