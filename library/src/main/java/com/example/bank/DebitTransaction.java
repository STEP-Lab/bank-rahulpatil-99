package com.example.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {

  protected DebitTransaction(Date date, double amount, String from) {
    super(date,amount,from);
  }

  public DebitTransaction(Double amount, String from) {
    this(new Date(),amount,from);
  }

}
