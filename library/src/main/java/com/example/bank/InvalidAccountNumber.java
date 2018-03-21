package com.example.bank;

public class InvalidAccountNumber extends Exception {
  public InvalidAccountNumber() {
    super("Requested account number is invalid. Account Number Format is :'4 digit - 4 digit'");
  }
}
