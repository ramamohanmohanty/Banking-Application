package org.jt.BankingManagementSystem.utility;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class  RandomAccountNumber {
    public static long generate(){
        long randomNumber = (long)(Math.random()* 10_00_00_000);
        long result = 1_00_00_00_000l+randomNumber;
        return result;
    }
}
