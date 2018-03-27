package com.example.bank;


import java.util.ArrayList;

import static java.lang.String.format;

public class Account {
  private static final double MIN_BALANCE_REQUIRED = 500.00;
  private final AccountNumber accountNumber;
  private final String name;
  private double balance;
  private Transactions transactions;

  public Account(AccountNumber accountNumber,String name,Money balance) throws MinimumAccountBalance {
    CheckMinimumBalanceRequirement(balance.getAmount());
    this.accountNumber = accountNumber;
    this.name = name;
    this.balance = balance.getAmount();
    transactions = new Transactions();
  }

  private void CheckMinimumBalanceRequirement(double amount) throws MinimumAccountBalance {
    if(amount<MIN_BALANCE_REQUIRED){
      throw new MinimumAccountBalance();
    }
  }

  public double getBalance() {
    return balance;
  }

  public void credit(Money amountTobeCredited, String to) {
    double amount = amountTobeCredited.getAmount();
    balance+=amount;
    transactions.credit(amount,to);
  }

  public void debit(Money amountTobeDebited, String from) throws MinimumAccountBalance {
    double amount = amountTobeDebited.getAmount();
    CheckMinimumBalanceRequirement(balance-amount);
    balance-=amount;
    transactions.debit(amount,from);
  }

  public String getSummary() {
    return format("Your Account Number:- %s \nYour name:- %s \nYour balance:- %g",accountNumber.getNumber(),name,balance);
  }

  public String getName() {
    return name;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions.getTransactions();
  }
}
