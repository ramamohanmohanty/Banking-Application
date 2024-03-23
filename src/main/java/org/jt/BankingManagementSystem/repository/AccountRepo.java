package org.jt.BankingManagementSystem.repository;

import jakarta.websocket.server.PathParam;
import org.jt.BankingManagementSystem.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer> {
//    Optional<Account> findByCredentialAccountEmail(String email);

    @Query("SELECT a FROM Account a WHERE a.credential.accountEmail = :email")
    Optional<Account> findByEmail(@PathParam("email") String email);
    Optional<Account> findByCredentialAccountEmailAndCredentialAccountPassword(String email, String password);
    @Query(nativeQuery = true,value = "SELECT * FROM banking WHERE a/c_Holder_Name = ?")
    Optional<Account> findByNumber(Long accountNumber);
    //this is native query
    @Query("UPDATE Account a SET a.accountBalance = a.accountBalance + :balance WHERE a.accountNumber = :accountNumber")
    @Modifying
    @Transactional
    int addBalance(@Param("accountNumber") Long accountNumber, @Param("balance") double balance);

    @Query("UPDATE Account a SET a.accountBalance = a.accountBalance - :balance WHERE a.accountNumber = :accountNumber")
    @Modifying
    @Transactional
    int deductBalance(@Param("accountNumber") Long accountNumber,@Param("balance") double accountBalance);

    Optional<Account> findByAccountNumber(long accountNumber);
    @Transactional
    void deleteByAccountNumber(long accountNumber); //named Query

    boolean existsByAccountNumber(long accountNumber);
}
