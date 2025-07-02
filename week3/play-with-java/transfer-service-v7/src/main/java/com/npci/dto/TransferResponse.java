package com.npci.dto;

import lombok.Data;

@Data
public class TransferResponse {
    private String status;
    private String message;
    private String transactionId;
}
