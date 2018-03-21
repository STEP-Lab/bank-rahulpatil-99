package com.example.bank;

public class MinimumAccountBalance extends Throwable {
  public MinimumAccountBalance() {
    super("Requested debit transaction can not be processed due to minimum balance requirement.");
  }
}
