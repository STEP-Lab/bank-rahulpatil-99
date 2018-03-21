package com.example.bank;

public class Account {
  private final String accountNumber;
  private final double balance;

  public Account(String accountNumber, double balance) throws InvalidAccountNumber {
    isValidAccountNumber(accountNumber);
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  private void isValidAccountNumber(String accountNumber) throws InvalidAccountNumber {
    if(!accountNumber.matches("^\\d{4}-\\d{4}$")){
      throw new InvalidAccountNumber();
    }
  }

  public double getBalance() {
    return balance;
  }

}
