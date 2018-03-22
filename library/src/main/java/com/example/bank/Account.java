package com.example.bank;


import java.util.ArrayList;

import static java.lang.String.format;

public class Account {
  private static final double MIN_BALANCE_REQUIRED = 500.00;
  private final String accountNumber;
  private final String name;
  private double balance;
  private ArrayList<Transaction> allTransactions= new ArrayList<>();

  public Account(String accountNumber,String name,double balance) throws InvalidAccountNumber, MinimumAccountBalance {
    isValidAccountNumber(accountNumber);
    CheckMinimumBalanceRequirement(balance);
    this.accountNumber = accountNumber;
    this.name = name;
    this.balance = balance;
  }

  private void CheckMinimumBalanceRequirement(double amount) throws MinimumAccountBalance {
    if(amount<MIN_BALANCE_REQUIRED){
      throw new MinimumAccountBalance();
    }
  }

  private void isValidAccountNumber(String accountNumber) throws InvalidAccountNumber {
    if(!accountNumber.matches("^\\d{4}-\\d{4}$")){
      throw new InvalidAccountNumber();
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

  public String getAccountNumber() {
    return accountNumber;
  }

  public void credit(double amountTobeCredited) throws InvalidAmount {
    checkInvalidAmount(amountTobeCredited);
    balance+=amountTobeCredited;
    allTransactions.add(new Transaction(amountTobeCredited,true));
  }

  public void debit(double amountTobeDebited) throws InvalidAmount, MinimumAccountBalance {
    checkInvalidAmount(amountTobeDebited);
    CheckMinimumBalanceRequirement(balance-amountTobeDebited);
    balance-=amountTobeDebited;
    allTransactions.add(new Transaction(amountTobeDebited,false));
  }

  public String getSummary() {
    return format("Your Account Number:- %s \nYour name:- %s \nYour balance:- %g",accountNumber,name,balance);
  }

  public String getName() {
    return name;
  }

  public ArrayList<Transaction> getAllTransactions() {
    System.out.println(allTransactions);
    return allTransactions;
  }

}
