package org.jt.BankingManagementSystem.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.jt.BankingManagementSystem.constant.TransactionMode;
import org.jt.BankingManagementSystem.domain.helper.Auditing;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionMode mode;
    private LocalDateTime timestamp;
//    @Enumerated(EnumType.STRING)
//    private TransactionStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_sl_no")
    private Account account;
}
