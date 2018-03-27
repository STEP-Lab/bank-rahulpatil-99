package com.example.bank;

import java.io.FileWriter;
import java.io.IOException;
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

  public Transactions getTransactionsBetween(Date initial , Date last) {
    Transactions result = new Transactions();
    for (Transaction transaction:transactions) {
      Date transactDate = transaction.getDate();
      if(transactDate.after(initial) && transactDate.before(last))
        result.transactions.add(transaction);
    }
    return result;
  }

  private static double updateBalance(Transaction transaction,double balance){
    if(transaction instanceof CreditTransaction){
      return balance + transaction.getAmount();
    }
    return balance - transaction.getAmount();
  }

  public double getCurrentBalance() {
    double currentBalance = 0;
    for (Transaction transaction : transactions) {
      currentBalance = updateBalance(transaction,currentBalance);
    }
    return currentBalance;
  }

  public void printToCsv(FileWriter fileWriter) throws IOException {
    double currentBalance=0;
    fileWriter.write("date,type,amount,source,currentbalance\n");
    for (Transaction transaction: transactions) {
      currentBalance = updateBalance(transaction,currentBalance);
      fileWriter.write(transaction.toCsv()+","+currentBalance+"\n");
    }
  }
}
