package com.example.bank;

import java.util.Date;

public abstract class Transaction {
  private final double amount;
  private final String source;
  private final Date date;

  public double getAmount() {
    return amount;
  }

  public String getSource() {
    return source;
  }


  public Transaction( Date date,double amount, String source) {
    this.date = date;
    this.amount = amount;
    this.source = source;
  }

  public Date getDate() {
    return date;
  }
}
