package org.npci.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class TransferRequest {

    @NotBlank(message = "From account number is required")
    @Pattern(regexp = "\\d{10,18}", message = "Invalid from account number")
    private String fromAccountNumber;

    @NotBlank(message = "To account number is required")
    @Pattern(regexp = "\\d{10,18}", message = "Invalid to account number")
    private String toAccountNumber;

    @Positive(message = "Transfer amount must be greater than 0")
    private double amount;
}
