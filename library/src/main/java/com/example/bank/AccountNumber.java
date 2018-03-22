package com.example.bank;

public class AccountNumber {
  private String accountNumber;

  public AccountNumber(String number) throws InvalidAccountNumber {
    isValidAccountNumber(number);
    this.accountNumber=number;
  }
  private void isValidAccountNumber(String accountNumber) throws InvalidAccountNumber {
    if(!accountNumber.matches("^\\d{4}-\\d{4}$")){
      throw new InvalidAccountNumber();
    }
  }

  public String getNumber() {
    return accountNumber;
  }
}
