package com.example.bank;


import java.util.ArrayList;

import static java.lang.String.format;

public class Account {
  private static final double MIN_BALANCE_REQUIRED = 500.00;
  private final AccountNumber accountNumber;
  private final String name;
  private Transactions transactions;

  private Account(AccountNumber accountNumber,String name,Money balance) {
    this.accountNumber = accountNumber;
    this.name = name;
    this.transactions = new Transactions();
    transactions.credit(balance.getAmount(),name);
  }

  public static Account createAccount(AccountNumber accountNumber,String name,Money balance) throws MinimumAccountBalance {
    CheckMinimumBalanceRequirement(balance.getAmount());
    return new Account(accountNumber,name,balance);
  }

  private static void CheckMinimumBalanceRequirement(double amount) throws MinimumAccountBalance {
    if(amount<MIN_BALANCE_REQUIRED)
      throw new MinimumAccountBalance();
  }

  public double getBalance() {
    return transactions.getCurrentBalance();
  }

  public void credit(Money amountTobeCredited, String to) {
    transactions.credit(amountTobeCredited.getAmount(),to);
  }

  public void debit(Money amountTobeDebited, String from) throws MinimumAccountBalance {
    double amount = amountTobeDebited.getAmount();
    CheckMinimumBalanceRequirement(getBalance()-amount);
    transactions.debit(amount,from);
  }

  public String getSummary() {
    return format("Your Account Number:- %s \nYour name:- %s \nYour balance:- %g",accountNumber.getNumber(),name,getBalance());
  }

  public String getName() {
    return name;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions.getTransactions();
  }
}
