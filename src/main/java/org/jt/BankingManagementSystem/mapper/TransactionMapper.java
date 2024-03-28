package org.jt.BankingManagementSystem.mapper;

import lombok.NoArgsConstructor;
import org.jt.BankingManagementSystem.domain.Transaction;
import org.jt.BankingManagementSystem.dto.TransactionDTO;

@NoArgsConstructor
public class TransactionMapper {
    public static TransactionDTO dtoMapper(Transaction transaction){
        return new TransactionDTO(
            transaction.getTimestamp(),
            transaction.getMode(),
            transaction.getAmount()
        );
    }
}
