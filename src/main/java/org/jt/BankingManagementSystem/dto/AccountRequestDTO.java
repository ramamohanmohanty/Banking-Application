package org.jt.BankingManagementSystem.dto;


import jakarta.validation.constraints.NotNull;
import org.jt.BankingManagementSystem.constant.AccountType;
import org.jt.BankingManagementSystem.domain.Credential;

public record AccountRequestDTO(
        @NotNull(message = "Account holder name should not be Null")
        String accountHolderName,
        @NotNull(message = "About customer should not be Null")
        String aboutCustomer,
        @NotNull(message = "Account phone should not be Null")
        String accountPhone,
        @NotNull(message = "Account email should not be Null")
        String accountEmail,
        @NotNull(message = "Account password should not be Null")
        String accountPassword,
        @NotNull(message = "Account type should not be Null")
        AccountType accountType
) {
}
