package com.example.bank;

public class Transaction {
  private final double amount;
  private final boolean isCredit;

  public Transaction(double amount, boolean isCredit) {
    this.amount = amount;
    this.isCredit = isCredit;
  }

  public double getAmount() {
    return amount;
  }

  public boolean isCreditTransaction() {
    return isCredit;
  }
}
