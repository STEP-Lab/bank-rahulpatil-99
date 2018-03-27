package com.example.bank;

import java.util.ArrayList;
import java.util.Date;

public class Transactions {
  private ArrayList<Transaction> transactions;

  public Transactions() {
    this.transactions = new ArrayList<>();
  }

  protected void credit(Date date,double amount, String to) {
    transactions.add(new CreditTransaction(date,amount,to));
  }

  public void credit(double amount, String to) {
    transactions.add(new CreditTransaction(amount,to));
  }

  protected void debit(Date date,double amount, String from) {
    transactions.add(new DebitTransaction(date,amount,from));
  }

  public void debit(double amount, String from) {
    transactions.add(new DebitTransaction(amount,from));
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public Transactions getTransactionsAbove(double amount) {
    Transactions result = new Transactions();
    for (Transaction transaction: transactions ) {
      if(transaction.getAmount()>=amount)
        result.transactions.add(transaction);
    }
    return result;
  }

  public Transactions getTransactionsBelow(double amount) {
    Transactions result = new Transactions();
    for (Transaction transaction: transactions ) {
      if(transaction.getAmount()<=amount)
        result.transactions.add(transaction);
    }
    return result;
  }

  public Transactions getCreditTransactions() {
    Transactions result = new Transactions();
    for (Transaction transaction:transactions) {
      if(transaction instanceof CreditTransaction)
        result.transactions.add(transaction);
    }
    return result;
  }

  public Transactions getDebitTransactions() {
    Transactions result = new Transactions();
    for (Transaction transaction:transactions) {
      if(transaction instanceof DebitTransaction)
        result.transactions.add(transaction);
    }
    return result;
  }

  public Transactions getTransactionsHappenedOn(Date when) {
    Transactions result = new Transactions();
    for (Transaction transaction:transactions) {
      if(transaction.getDate().equals(when))
        result.transactions.add(transaction);
    }
    return result;
  }

  public Transactions getTransactionsBefore(Date when) {
    Transactions result = new Transactions();
    for (Transaction transaction:transactions) {
      if(transaction.getDate().before(when))
        result.transactions.add(transaction);
    }
    return result;
  }

  public Transactions getTransactionsAfter(Date when) {
    Transactions result = new Transactions();
    for (Transaction transaction:transactions) {
      if(transaction.getDate().after(when))
        result.transactions.add(transaction);
    }
    return result;
  }
}
