package com.example.bank;

public class Account {
  private final String accountNumber;
  private final double balance;

  public Account(String accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  public double getBalance() {
    return balance;
  }
}
