package com.example.bank;


import java.util.ArrayList;

import static java.lang.String.format;

public class Account {
  private static final double MIN_BALANCE_REQUIRED = 500.00;
  private final AccountNumber accountNumber;
  private final String name;
  private double balance;
  private Transactions transactions;

  public Account(AccountNumber accountNumber,String name,double balance) throws MinimumAccountBalance {
    CheckMinimumBalanceRequirement(balance);
    this.accountNumber = accountNumber;
    this.name = name;
    this.balance = balance;
    transactions = new Transactions();
  }

  private void CheckMinimumBalanceRequirement(double amount) throws MinimumAccountBalance {
    if(amount<MIN_BALANCE_REQUIRED){
      throw new MinimumAccountBalance();
    }
  }

  private void checkInvalidAmount(double amount) throws InvalidAmount {
    if(amount<=0){
      throw new InvalidAmount();
    }
  }

  public double getBalance() {
    return balance;
  }

  public void credit(double amountTobeCredited,String to) throws InvalidAmount {
    checkInvalidAmount(amountTobeCredited);
    balance+=amountTobeCredited;
    transactions.credit(amountTobeCredited,to);
  }

  public void debit(double amountTobeDebited,String from) throws InvalidAmount, MinimumAccountBalance {
    checkInvalidAmount(amountTobeDebited);
    CheckMinimumBalanceRequirement(balance-amountTobeDebited);
    balance-=amountTobeDebited;
    transactions.debit(amountTobeDebited,from);
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
