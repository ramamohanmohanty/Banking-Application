package org.jt.BankingManagementSystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable //embedded object is mapped to the database table for the entity
public class Credential {
    @Column(unique = true,length = 50,nullable = false)
    private String accountEmail;

    @Column(nullable = false)
    private String accountPassword;
}
