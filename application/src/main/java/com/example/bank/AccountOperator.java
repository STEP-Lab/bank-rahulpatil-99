package com.example.bank;

public class AccountOperator{
  public static void main(String args[]) throws InvalidAccountNumber, InvalidAmount, MinimumAccountBalance {
    Account account = new Account("1111-1111",1000.00);
    System.out.println(account.getBalance());
    account.credit(500.50);
    System.out.println(account.getBalance());
    account.debit(200.50);
    System.out.println(account.getBalance());
  }
}