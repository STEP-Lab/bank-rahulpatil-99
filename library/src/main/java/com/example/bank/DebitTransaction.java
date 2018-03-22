package com.example.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {

  protected DebitTransaction(Date date, double amount, String to) {
    super(amount, to, date);
  }

  public DebitTransaction(Double amount, String to) {
    this(new Date(),amount,to);
  }

}
