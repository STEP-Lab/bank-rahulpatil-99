package com.example.bank;

import java.util.ArrayList;

public class Transactions {
  private ArrayList<Transaction> transactions;

  public Transactions() {
    this.transactions = new ArrayList<>();
  }

  public void credit(double amount, String to) {
    transactions.add(new CreditTransaction(amount,to));
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public void debit(double amount, String from) {
    transactions.add(new DebitTransaction(amount,from));
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
}
