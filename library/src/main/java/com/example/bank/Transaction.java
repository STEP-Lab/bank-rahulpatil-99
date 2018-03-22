package com.example.bank;

import java.util.Date;

public abstract class Transaction {
  protected final double amount;
  protected final String to;
  protected final Date date;

  public Transaction(double amount, String to, Date date) {
    this.amount = amount;
    this.to = to;
    this.date = date;
  }

  public Date getDate() {
    return date;
  }
}
