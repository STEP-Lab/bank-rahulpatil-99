package com.example.bank;

public class Account {
  private final String accountNumber;
  private double balance;

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

  public String getAccountNumber() {
    return accountNumber;
  }

  public void credit(double amountTobeCredited) throws InvalidAmount {
    if(amountTobeCredited<0) throw new InvalidAmount();
    balance+=amountTobeCredited;
  }
}
