package org.jt.BankingManagementSystem.domain;

import jakarta.persistence.*;
import lombok.*;
import org.jt.BankingManagementSystem.constant.AccountType;
import org.jt.BankingManagementSystem.constant.AccountType;
import org.jt.BankingManagementSystem.constant.TransactionMode;
import org.jt.BankingManagementSystem.domain.helper.Auditing;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "account_details")
public class Account extends Auditing{ //Inheritance case
    @Id                               //Multiple inheritance cannot support in java
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int accountSerialNo;
    @Column(unique = true,nullable = false)
    private long accountNumber;
    @Column(name = "a/cHolderName",nullable = false)
    private String accountHolderName;
    private String accountPhone;
    private String profilePicture;
    @Lob
    private String aboutCustomer;
    @Embedded //Specifies a persistent field or property of an entity whose value is an instance of an embeddable class. The embeddable class must be annotated as Embeddable.
    private Credential credential;
    private double accountBalance;
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //this is the database query is one query to excute the database
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
//    @Embedded
//    private Auditing showDateTime; Embedded object in hinernate through

}
