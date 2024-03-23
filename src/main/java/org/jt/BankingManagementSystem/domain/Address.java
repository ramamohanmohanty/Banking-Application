package org.jt.BankingManagementSystem.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jt.BankingManagementSystem.domain.helper.Auditing;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table(name = "address_details")
public class Address extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String addressId;
    String city;
    String state;
    String country;
    String zipcode;
    @OneToOne(mappedBy = "address")
    Account account;
}
