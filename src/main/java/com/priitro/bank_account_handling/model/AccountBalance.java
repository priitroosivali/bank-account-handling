package com.priitro.bank_account_handling.model;

import com.priitro.bank_account_handling.enums.CurrencyEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="account_balance")
public class AccountBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyEnum currency;
    @Min(0L)
    private Long amountInCents;
}
