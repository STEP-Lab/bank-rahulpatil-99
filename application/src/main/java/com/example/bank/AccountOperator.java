package com.example.bank;

public class AccountOperator{
  public static void main(String args[]) throws InvalidAccountNumber{
    Account account = new Account("1111-1111",1000.00);
    System.out.println(account.getBalance());
  }
}