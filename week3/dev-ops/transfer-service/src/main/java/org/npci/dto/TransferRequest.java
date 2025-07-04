package org.npci.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private double amount;

}
