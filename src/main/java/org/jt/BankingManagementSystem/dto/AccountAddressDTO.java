package org.jt.BankingManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import org.jt.BankingManagementSystem.constant.AccountType;

public record AccountAddressDTO(
        @NotNull(message = "Account holder name should not be Null")
        String accountHolderName,
        @NotNull(message = "About customer should not be Null")
        String aboutCustomer,
        @NotNull(message = "Phone number should not be Null")
        String accountPhone,
        @NotNull(message = "Account Email should not be Null")
        String accountEmail,
        @NotNull(message = "Account password should not be Null")
        String accountPassword,
        @NotNull(message = "Account type should not be Null")
        AccountType accountType,
        @NotNull(message = "City name should not be Null")
        String city,
        @NotNull(message = "State name should not be Null")
        String state,
        @NotNull(message = "Country name should not be Null")
        String country,
        @NotNull(message = "Zipcode should not be Null")
        String zipcode
) {
}
