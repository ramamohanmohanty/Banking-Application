package org.jt.BankingManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import org.jt.BankingManagementSystem.constant.AccountType;

public record AccountUpdateDTO (
        @NotNull(message = "Account holder name should not be Null")
        String accountHolderName,
        @NotNull(message = "Abount customer should not be Null")
        String aboutCustomer,
        @NotNull(message = "Phone Number should not be Null")
        String accountPhone,
        @NotNull(message = "AccountType should not be Null")
        AccountType accountType,
        @NotNull(message = "City name should not be Null")
        String city,
        @NotNull(message = "State name should not be Null")
        String state,
        @NotNull(message = "Country name should not be Null")
        String country,
        @NotNull(message = "Zipcode should not be Null")
        String zipcode
){
}
