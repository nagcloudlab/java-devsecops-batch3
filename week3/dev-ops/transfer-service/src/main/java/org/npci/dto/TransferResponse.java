package org.npci.dto;

import lombok.Data;

@Data
public class TransferResponse {
    private String transactionId;
    private String status;
    private String fromAccountNumber;
    private String toAccountNumber;
    private double amount;
}
