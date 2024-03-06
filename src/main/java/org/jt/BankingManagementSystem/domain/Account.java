package org.jt.BankingManagementSystem.domain;

import jakarta.persistence.*;
import lombok.*;
import org.jt.BankingManagementSystem.domain.helper.Auditing;

@Getter
@Setter
@Builder
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account extends Auditing{ //Inheritance case
    @Id                               //Multiple inheritance cannot support in java
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int accountSerialNo;
    @Column(unique = true,nullable = false)
    private long accountNumber;
    @Column(name = "a/cHolderName",nullable = false)
    private String accountHolderName;
    @Column(unique = true,length = 50,nullable = false)
    private String accountEmail;
    @Column(nullable = false)
    private String accountPassword;
    @Column(nullable = false)
    private String accountPhone;
    @Lob
    private String aboutCustomer;
    @Enumerated(value = EnumType.STRING)
    private AccountEnum accountType;
//    @Embedded
//    private Auditing showDateTime; Embedded object in hinernate through

}
