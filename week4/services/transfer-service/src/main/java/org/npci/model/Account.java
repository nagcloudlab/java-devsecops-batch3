package org.npci.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "account_number", nullable = false, unique = true)
    @Pattern(regexp = "\\d{10,18}", message = "Invalid account number")
    private String accountNumber;

    @Column(name = "account_holder_name", nullable = false)
    @NotBlank(message = "Account holder name is required")
    private String accountHolderName;

    @Column(name = "balance", nullable = false)
    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance;
}
