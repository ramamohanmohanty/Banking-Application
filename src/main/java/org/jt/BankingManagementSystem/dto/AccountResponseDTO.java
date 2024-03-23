package org.jt.BankingManagementSystem.dto;

import org.jt.BankingManagementSystem.constant.AccountType;

public record AccountResponseDTO( //this is row data and first create object then use record class
                                  long accountNumber,
                                  String accountHolderName,
                                  String aboutCustomer,
                                  String accountPhone,
                                  String accountEmail,
                                  double accountBalance,
                                  AccountType accountType,
                                  String city,
                                  String state,
                                  String country,
                                  String zipcode
){
}
