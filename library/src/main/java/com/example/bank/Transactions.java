package com.example.bank;

import java.util.ArrayList;

public class Transactions {
  private ArrayList<Transaction> transactions;
  public Transactions() {
    this.transactions = new ArrayList<>();
  }

  public void credit(double amount, String from) {
    transactions.add(new CreditTransaction(amount,from));
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public void debit(double amount, String to) {
    transactions.add(new DebitTransaction(amount,to));
  }
}
