package org.jt.BankingManagementSystem.repository;

import org.jt.BankingManagementSystem.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, String> {
}
