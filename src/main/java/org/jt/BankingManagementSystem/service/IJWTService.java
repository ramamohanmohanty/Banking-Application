package org.jt.BankingManagementSystem.service;

public interface IJWTService {
    String generateToken(String accountNumber);
    String validateToken(String token);
}
